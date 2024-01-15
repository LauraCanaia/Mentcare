package demo;
import org.springframework.beans.factory.annotation.Autowired;
import demo.UserRepository;
import demo.User;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import java.util.ArrayList;

public class UserDemo {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        // Carica due utenti predefiniti al momento dell'avvio
        User user1 = new User("lautaro", "martinez", "MRTLTR90A01A539U", "inter");
        User user2 = new User("stefano", "modenese", "MRTLTR90A01A539U", "sansiro");

        userRepository.save(user1);
        userRepository.save(user2);
    }

    public boolean addDemoData () {

        User user1 = new User("sofia", "modenese", "MDNSFO00B53E512S", "bubi");
        User user2 = new User("davide", "baruh", "BRHDVD00L25L359K", "sofi");

        userRepository.save(user1);
        userRepository.save(user2);

        return true;
    }
}
