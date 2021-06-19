package com.quiz.entities;

import javax.persistence.*;

/**
 * Hibernate Entity klasa bazirana na players tabeli u bazi podataka
 */
@Entity
@Table(name = "players")
public class Player {
    /**
     * ID pitanja, autogenerisana vrednost u bazi podataka
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    int id;

    @Column(name = "username")
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "total_points")
    int totalPoints;

    @Column(name = "finished_games")
    int finishedGames;

    @Column(name = "is_admin")
    boolean isAdmin;

    /**
     * Podrazumevani konstruktor
     */
    public Player(){}

    /**
     * @param username korisničko ime igrača
     * @param email email igrača
     * @param password sifra igrača
     * @param isAdmin da li je igrač admin ili ne?
     */
    public Player(String username, String email, String password, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * @param username korisničko ime igrača
     * @param email email igrača
     * @param password sifra igrača
     * @param totalPoints broj poena koje igrač ima
     * @param finishedGames broj završenih igara igrača
     * @param isAdmin da li je igrač admin ili ne?
     */
    public Player(String username, String email, String password, int totalPoints, int finishedGames, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.totalPoints = totalPoints;
        this.finishedGames = finishedGames;
        this.isAdmin = isAdmin;
    }

    /**
     * @return id igrača
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id igrača
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return korisničko ime igrača
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username korisničko ime igrača
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return email igrača
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email email igrača
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return šifra igrača
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password šifra igrača
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return broj poena koje igrač ima
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * @param totalPoints broj poena igrača
     */
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    /**
     * @return broj završenih igara igrača
     */
    public int getFinishedGames() {
        return finishedGames;
    }

    /**
     * @param finishedGames broj završenih igara igrača
     */
    public void setFinishedGames(int finishedGames) {
        this.finishedGames = finishedGames;
    }

    /**
     * @return admin status igrača
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * @param admin admin status igrača
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", totalPoints=" + totalPoints +
                ", finishedGames=" + finishedGames +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
