package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
//import demo.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VisitRepository visitRepository;

    @RequestMapping("/")
    public String index(){ return "mentcare"; }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        //user.setVisits2();
        if (user != null) {
            for (int i=0; i<user.getVisits().size(); i++) {
                Visit visita = user.getVisits().get(i);
                visitRepository.save(visita);
                System.out.println("ho salvato la visita: " + visita);
            }
            System.out.println("ho trovato user: " + user.getUsername()+ " e visite: " + user.getVisits());
        }

        if (user != null) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("user", user);
            System.out.println("aggiungo nel model le visite: "+ user.getVisits());
            model.addAttribute("visits", user.getVisits());
            return "welcome";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "mentcare";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "mentcare";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/editVisit/{id}")
    public String editVisit(@PathVariable Long id,  Model model) {
        // Utilizza l'id per ottenere la visita dal repository
        Optional<Visit> optionalVisit = visitRepository.findById(id);

        if (optionalVisit.isPresent()) {
            Visit visit = optionalVisit.get();
            //model.addAttribute("username", username);
            model.addAttribute("visit", visit);
            System.out.println("/editVisit: l'id è: " + visit.getId());
            return "editVisit"; // Sostituisci con il nome della tua pagina di modifica visita
        } else {
            return "visitNotFound"; // Sostituisci con il nome della tua pagina di visita non trovata
        }
    }

    @PostMapping("/saveVisit")
    public String saveVisit(@RequestParam Long visitId, @RequestParam String date, @RequestParam String time, @RequestParam String motivation, Model model) {
        Optional<Visit> optionalVisit = visitRepository.findById(visitId);

        if (optionalVisit.isPresent()) {
            Visit visit = optionalVisit.get();
            visit.setDate(date);
            visit.setTime(time);
            visit.setMotivation(motivation);

            // Salva la visita aggiornata nel repository delle visite solo se necessario
            // visitRepository.save(visit);

            // Aggiorna la visita nelle visite dell'utente
            User user = visit.getUser();
            List<Visit> userVisits = user.getVisits();
            int index = userVisits.indexOf(visit);

            if (index != -1) {
                userVisits.set(index, visit);
            }

            // Aggiorna l'utente nel repository degli utenti
            userRepository.save(user);
            System.out.println("l'utente " + user.getUsername() + " ha ora le visite: " + user.getVisits());
            model.addAttribute("visits", user.getVisits());
            model.addAttribute("username", user.getUsername());
        } else {
            model.addAttribute("error", "La visita non è stata trovata.");
        }
        return "welcome";
    }

    @GetMapping("/addVisit/{username}")
    public String showAddVisitPage(@PathVariable String username, Model model) {
        model.addAttribute("visit", new Visit());  // Creazione di un oggetto Visit per il form
        model.addAttribute("username", username);
        return "addVisit";
    }

    @PostMapping("/saveAddedVisit")
    public String saveAddedVisit(@RequestParam String username, @RequestParam String date,
                                 @RequestParam String time, @RequestParam String motivation,
                                 Model model) {
        // 1. Trova l'utente dal repository utilizzando l'username
        User user = userRepository.findByUsername(username);

        // 2. Crea una nuova visita con i dati forniti
        Visit visita = new Visit();
        visita.setDate(date);
        visita.setTime(time);
        visita.setMotivation(motivation);

        // 3. Salva la nuova visita nel repository delle visite
        visitRepository.save(visita);

        // 4. Aggiungi la visita all'utente corrente
        user.addVisit(visita);

        // 5. Salva l'utente aggiornato nel repository degli utenti
        userRepository.save(user);

        model.addAttribute("visits", user.getVisits());
        model.addAttribute("username", user.getUsername());
        System.out.println("le visite di " + user.getUsername() + " ora sono: " + user.getVisits());
        return "welcome";
    }

    @GetMapping("/deleteVisit/{visitId}")
    public String deleteVisit(@PathVariable Long visitId, Model model) {
        // 1. Trova la visita dal repository delle visite
        Visit visitToDelete = visitRepository.findById(visitId).orElse(null);

        // 2. Controlla se la visita esiste
        if (visitToDelete != null) {
            // 3. Trova l'utente dal repository utilizzando l'username
            User user = userRepository.findByUsername(visitToDelete.getUser().getUsername());

            // 4. Rimuovi la visita dalle visite dell'utente
            user.removeVisit(visitToDelete);  // Assumi che la classe User abbia un metodo removeVisit(Visit visit)

            // 5. Salva l'utente aggiornato nel repository degli utenti
            userRepository.save(user);

            // 6. Elimina la visita dal repository delle visite
            visitRepository.delete(visitToDelete);
            model.addAttribute("visits", user.getVisits());
            model.addAttribute("username", user.getUsername());
        }
        // 7. Redirect alla pagina di benvenuto
        return "welcome";
    }


    @RequestMapping("/list")
    public String list(Model model){
        List<Person> data = new LinkedList<>();
        for (Person p: repository.findAll()){
            data.add(p);
        }
        model.addAttribute("people", data);
        return "list";
    }

    @RequestMapping("/input")
    public String input(){
        return "input";
    }

    @RequestMapping("/read")
    public String read(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()){
            Person person = result.get();
            model.addAttribute("person", person);
            return "read";
        }
        else
            return "notfound";
    }

    @RequestMapping("/create")
    public String create(
            @RequestParam(name="firstname", required=true) String firstname,
            @RequestParam(name="lastname", required=true) String lastname) {
        repository.save(new Person(firstname,lastname));
        return "redirect:/list";
    }

    @RequestMapping("/edit")
    public String edit(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            Person person = result.get();
            model.addAttribute("person", person);
            return "edit";
        }
        else
            return "notfound";
    }

    @RequestMapping("/update")
    public String update(
            @RequestParam(name="id", required=true) Long id,
            @RequestParam(name="firstname", required=true) String firstname,
            @RequestParam(name="lastname", required=true) String lastname,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            repository.delete(result.get());
            Person person = new Person(firstname,lastname);
            repository.save(person);
            return "redirect:/list";
        }
        else
            return "notfound";
    }

    @RequestMapping("/delete")
    public String delete(
            @RequestParam(name="id", required=true) Long id) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()){
            repository.delete(result.get());
            return "redirect:/list";
        }
        else
            return "notfound";
    }


    /*@RequestMapping("/show1")
    public String show1(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()){
            Person person = result.get();
            model.addAttribute("person", person);
            return "show1";
        }
        else
            return "notfound";
    }*/

}