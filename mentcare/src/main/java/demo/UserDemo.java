package demo;
import org.springframework.beans.factory.annotation.Autowired;
import demo.UserRepository;
import demo.User;

import javax.annotation.PostConstruct;
public class UserDemo {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        // Carica due utenti predefiniti al momento dell'avvio
        User user1 = new User("lautaro", "inter");
        User user2 = new User("stefano", "sansiro");

        userRepository.save(user1);
        userRepository.save(user2);
    }

    public boolean addDemoData () {
        User user1 = new User("sofia", "modenese");
        User user2 = new User("davide", "baruh");

        userRepository.save(user1);
        userRepository.save(user2);

        return true;
    }
}
