import java.io.Serializable;

public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nom; // Nom de l'équipe
    private int points; // Points gagnés par l'équipe
    private int butsMarques; // Buts marqués par l'équipe
    private int butsEncaisses; // Buts encaissés par l'équipe
    private int victoires; // Victoires de l'équipe
    private int defaites; // Défaites de l'équipe
    private int matchesNuls; // Matches nuls de l'équipe

    public Equipe(String nom) {
        this.nom = nom;
        this.points = 0;
        this.butsMarques = 0;
        this.butsEncaisses = 0;
        this.victoires = 0;
        this.defaites = 0;
        this.matchesNuls = 0;
    }

    // Getters et setters
    public String getNom() { return nom; }
    public int getPoints() { return points; }
    public int getButsMarques() { return butsMarques; }
    public int getButsEncaisses() { return butsEncaisses; }
    public int getVictoires() { return victoires; }
    public int getDefaites() { return defaites; }
    public int getMatchesNuls() { return matchesNuls; }
    public void ajouterPoints(int points) {
        this.points += points;
    }

    public void ajouterButsMarques(int buts) {
        this.butsMarques += buts;
    }

    public void ajouterButsEncaisses(int buts) {
        this.butsEncaisses += buts;
    }

    public void enregistrerVictoire() {
        this.victoires += 1;
    }

    public void enregistrerDefaite() {
        this.defaites += 1;
    }

    public void enregistrerMatchNul() {
        this.matchesNuls += 1;
    }
}
