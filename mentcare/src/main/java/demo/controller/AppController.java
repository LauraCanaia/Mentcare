package demo.controller;

import demo.model.User;
import demo.repository.UserRepository;
import demo.model.Visit;
import demo.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
//import demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VisitRepository visitRepository;

    @RequestMapping("/")
    public String index(){ return "mentcare"; }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        User user = userRepository.findByUsernameAndPassword(username, password);
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
        Optional<Visit> optionalVisit = visitRepository.findById(id);

        if (optionalVisit.isPresent()) {
            Visit visit = optionalVisit.get();
            model.addAttribute("visit", visit);
            model.addAttribute("username", visit.getUser().getUsername());
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

    @GetMapping("/welcome")
    public String welcome(@RequestParam String username, Model model) {
        System.out.println("il pulsante è stato premuto, after all");
        System.out.println("devo trovare lo user " + username);
        User user = userRepository.findByUsername(username);

        if (user != null) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("visits", user.getVisits());
            return "welcome";
        } else {
            // Utente non trovato, gestisci di conseguenza (ad esempio, reindirizzamento a una pagina di errore)
            return "redirect:/error";
        }
    }
}