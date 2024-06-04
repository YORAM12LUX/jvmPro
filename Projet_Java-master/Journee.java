import java.io.*;

class Journee implements Serializable {

    private static final long serialVersionUID = 1L;
    private Match[] matches;

    public Journee(int nombreDeMatches) {
        matches = new Match[nombreDeMatches];
    }

    public void ajouterMatch(Match match, int index) {
        matches[index] = match;
    }

    public void jouerTousLesMatches() {
        for (Match match : matches) {
            match.jouer();
        }
    }

    public Match[] getMatches() {
        return matches;
    }
}
