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
        User user1 = new User("lautaro", "martinez", "MRTLTR80A01E512E", "inter");
        User user2 = new User("stefano", "modenese", "MDNSFN80A01E512Y", "sansiro");

        userRepository.save(user1);
        userRepository.save(user2);
    }

    public boolean addDemoData () {
        User user1 = new User("sofia", "modenese", "MDNSFN80A01E512Y", "bubi");
        User user2 = new User("davide", "baruh", "MDNSFN80A01E512Y", "sofi");

        userRepository.save(user1);
        userRepository.save(user2);

        return true;
    }
}
