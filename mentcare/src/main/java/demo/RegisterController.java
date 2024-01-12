package demo;

import org.springframework.stereotype.Controller;
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
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
    @PostMapping("/register")
    public String register(String username, String password, Model model) {
        // Logica per la registrazione dell'utente (può includere il salvataggio in un database, ad esempio)
        User newUser = new User(username, password);
        userRepository.save(newUser);
        // Dopo la registrazione, reindirizza l'utente alla pagina di login
        return "redirect:/login";
    }
}
