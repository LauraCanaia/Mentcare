package demo.controller;

import demo.model.User;
import demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        // Inizializza il repository con un utente
        User user = new User("stefano", "stefano", "modenese", "MDNSFN00B13E512N", "1234");
        User user2 = new User("laura", "laura", "canaia", "CNALRA99C42L781O", "5678");

        System.out.println("ho creato "+user.toString() + " e visite: " + user.getVisits());


        userRepository.save(user);
        userRepository.save(user2);
    }
}

