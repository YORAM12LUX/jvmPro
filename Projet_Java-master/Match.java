import java.io.Serializable;
import java.util.Random;

public class Match implements Serializable {

    private static final long serialVersionUID = 1L;

    private Equipe equipe1;
    private Equipe equipe2;
    private int scoreEquipe1;
    private int scoreEquipe2;
    private boolean jouer;

    public Match(Equipe equipe1, Equipe equipe2) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.jouer = false;
    }

    public void jouer() {
        if (!jouer) {
            Random rand = new Random();
            scoreEquipe1 = rand.nextInt(6); // Scores entre 0 et 5
            scoreEquipe2 = rand.nextInt(6);

            equipe1.ajouterButsMarques(scoreEquipe1);
            equipe1.ajouterButsEncaisses(scoreEquipe2);
            equipe2.ajouterButsMarques(scoreEquipe2);
            equipe2.ajouterButsEncaisses(scoreEquipe1);

            if (scoreEquipe1 > scoreEquipe2) {
                equipe1.ajouterPoints(3);
                equipe1.enregistrerVictoire();
                equipe2.enregistrerDefaite();
            } else if (scoreEquipe1 < scoreEquipe2) {
                equipe2.ajouterPoints(3);
                equipe2.enregistrerVictoire();
                equipe1.enregistrerDefaite();
            } else {
                equipe1.ajouterPoints(1);
                equipe2.ajouterPoints(1);
                equipe1.enregistrerMatchNul();
                equipe2.enregistrerMatchNul();
            }

            jouer = true;
        }
    }

    public String getResultat() {
        if (jouer) {
            return equipe1.getNom() + " " + scoreEquipe1 + " - " + scoreEquipe2 + " " + equipe2.getNom();
        } else {
            return "Match non joué";
        }
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public int getScoreEquipe1() {
        return scoreEquipe1;
    }

    public int getScoreEquipe2() {
        return scoreEquipe2;
    }

    public boolean isJoue() {
        return jouer;
    }
}
