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

    private String lastname;
    private String fiscalCode;

    private String password;

    public User(String username, String lastname, String fiscalCode, String password) {
        this.username = username;
        this.lastname = lastname;
        this.fiscalCode = fiscalCode;
        this.password = password;
    }

    public User() {
        this.username = "";
        this.lastname = "";
        this.fiscalCode = "";
        this.password = "";
    }

    @Override
    public String toString () {
        return String.format(
                "User[username='%s', lastname='%s', fiscalCode='%s' password='%s']",
                username, lastname, fiscalCode, password);
    }

    public String getUsername () {
        return this.username;
    }

    public String getPassword () {
        return this.password;
    }

    public String getLastname () { return this.lastname; }

    public String getFiscalCode () { return this.fiscalCode; }

    public void setUsername (String username) {
        this.username = username;
    }

    public void setPassword (String password) {
        this.password = password;
    }
}
