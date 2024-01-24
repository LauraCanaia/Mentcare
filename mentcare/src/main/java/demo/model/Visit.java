package demo.model;

import demo.model.User;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Visit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String date;
    private String time;
    private String motivation;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Visit (String date, String time, String motivation) {
        this.date = date;
        this.time = time;
        this.motivation = motivation;
        System.out.println("ho creato una visita");
    }

    public Visit () {
        // Inizializza la data a '1 gennaio 2024'
        //this.date = LocalDate.of(2024, 1, 1);
        this.date = "01/01/2024";

        // Inizializza l'orario a '12:30'
        //this.time = LocalTime.of(12, 30);
        this.time = "12:30";

        // Inizializza la motivazione con un testo di prova
        this.motivation = "visita di prova";
        System.out.println("ho creato una visita standard");
    }

    /*@Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", date=" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", time=" + time.format(DateTimeFormatter.ofPattern("HH:mm")) +
                ", motivation='" + motivation + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "Visit{" + "id=" + id + ", date=" + date + ", time=" + time + ", motivation=" + motivation;
    }

    public String getDate () {
        return this.date;
    }

    public String getTime () {
        return this.time;
    }

    public String getMotivation () {
        return this.motivation;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public void setTime (String time) {
        this.time = time;
    }

    public void setMotivation (String motivation) {
        this.motivation = motivation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId () { return this.id; }
}
