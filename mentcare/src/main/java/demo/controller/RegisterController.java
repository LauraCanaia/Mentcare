package demo.controller;

import demo.model.User;
import demo.repository.UserRepository;
import demo.repository.VisitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import demo.repository.UserRepository;


@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
    @PostMapping("/register")
    public String register(String username, String name, String lastname, String fiscalcode, String password, Model model) {

        System.out.println(username + " " + name + " " + lastname + " " + fiscalcode + " " + password);

        // Verifica se l'username è già presente nel repository
        if (userRepository.existsByUsername(username)) {
            System.out.println("Username già in uso");
            model.addAttribute("errorUsername", "Username già in uso");
            return "register";
        }

        if (!isValidFiscalCode(fiscalcode)) {
            System.out.println("il codice non è valido");
            model.addAttribute("errorFiscalCode", "Codice fiscale non valido");
            return "register";
        }

        if (!isValidPassword(password)) {
            System.out.println("la password non è valida");
            model.addAttribute("errorPassword", "Password non valida");
            return "register";
        }

        System.out.println(username + " " + name + " " + lastname + " " + fiscalcode + " " + password);

        // Logica per la registrazione dell'utente (può includere il salvataggio in un database, ad esempio)
        User newUser = new User(username, name, lastname, fiscalcode, password);
        userRepository.save(newUser);
        // Dopo la registrazione, reindirizza l'utente alla pagina di login
        return "redirect:/login";
    }

    private boolean isValidFiscalCode (String fiscalcode) {
        return fiscalcode.length() == 16;
    }

    private boolean isValidPassword (String password) {
        return password.length() >= 8;
    }
}
