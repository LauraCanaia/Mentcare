package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import demo.UserRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index(){
        return "mentcare";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        //System.out.println("ho trovato user: " + user.getUsername());

        if (user != null) {
            model.addAttribute("username", user.getUsername());
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