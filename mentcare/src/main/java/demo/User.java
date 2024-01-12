package demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;

    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
        this.username = "";
        this.password = "";
    }

    @Override
    public String toString () {
        return String.format(
                "User[username='%s', lastname='%s', code='%s' password='%s']",
                username, password);
    }

    public String getUsername () {
        return this.username;
    }

    public String getPassword () {
        return this.password;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public void setPassword (String password) {
        this.password = password;
    }
}
