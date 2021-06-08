package quiz.entities;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {
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

    public Player(){}

    public Player(String username, String email, String password, int totalPoints, int finishedGames, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.totalPoints = totalPoints;
        this.finishedGames = finishedGames;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getFinishedGames() {
        return finishedGames;
    }

    public void setFinishedGames(int finishedGames) {
        this.finishedGames = finishedGames;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

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
