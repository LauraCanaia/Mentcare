package demo.controller;

import demo.model.User;
import demo.repository.UserRepository;
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
    public String register(String username, String lastname, String fiscalcode, String password, Model model) {

        System.out.println(username + " " + lastname + " " + fiscalcode + " " + password);

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

        System.out.println(username + " " + lastname + " " + fiscalcode + " " + password);

        // Logica per la registrazione dell'utente (può includere il salvataggio in un database, ad esempio)
        User newUser = new User(username, lastname, fiscalcode, password);
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
