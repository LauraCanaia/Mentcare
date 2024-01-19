package demo.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;

    private String lastname;
    private String fiscalCode;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Visit> visits = new ArrayList<>();

    public User(String username, String lastname, String fiscalCode, String password) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.fiscalCode = fiscalCode;
        // Aggiungi una visita con valori fissi
        //Visit fixedVisit = createFixedVisit();
        Visit fixedVisit = new Visit();
        visits.add(fixedVisit);
        fixedVisit.setUser(this);
        System.out.println("le visite sono: " + this.visits);
    }

    public User() {
        this.username = "";
        this.password = "";
        this.lastname = "";
        this.fiscalCode = "";
        this.visits = new ArrayList<>();
        this.visits.add(new Visit());
        System.out.println("costruttore vuoto:" + this.visits);
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

    public List<Visit> getVisits () { return this.visits; }

    public void setUsername (String username) {
        this.username = username;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public void setLastname (String lastname) { this.lastname = lastname; }

    public void setFiscalCode (String fiscalCode) { this.fiscalCode = fiscalCode; }

    public void addVisit (Visit visit) {
        visits.add(visit);
        visit.setUser(this);
    }

    public void removeVisit (Visit visit) {
        int index = 0;
        for (int i=0; i<visits.size(); i++) {
            Visit v = visits.get(i);
            if (Objects.equals(visit.getId(), v.getId())) {
                index = i;
            }
        }
        visits.remove(index);
    }

    private Visit createFixedVisit() {
        Visit visit = new Visit();
        visit.setDate("01/01/2024");
        visit.setTime("12:30");
        visit.setMotivation("Visita di prova");
        return visit;
    }
}
