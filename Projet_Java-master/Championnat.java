import java.io.*;

class Championnat implements Serializable {

    private static final long serialVersionUID = 1L;
    public Equipe[] equipes;
    public Journee[] journees;

    public Championnat(Equipe[] equipes) {
        this.equipes = equipes;
        int nombreDeJournees = equipes.length - 1;
        journees = new Journee[nombreDeJournees];
        genererCalendrier();
    }

    private void genererCalendrier() {
        int nombreEquipes = equipes.length;
        Equipe[] rotationEquipes = new Equipe[nombreEquipes];
        System.arraycopy(equipes, 0, rotationEquipes, 0, nombreEquipes);

        for (int i = 0; i < nombreEquipes - 1; i++) {
            journees[i] = new Journee(nombreEquipes / 2);
            for (int j = 0; j < nombreEquipes / 2; j++) {
                Equipe equipe1 = rotationEquipes[j];
                Equipe equipe2 = rotationEquipes[nombreEquipes - 1 - j];
                journees[i].ajouterMatch(new Match(equipe1, equipe2), j);
            }

            // Rotation des équipes pour le prochain tour
            Equipe temp = rotationEquipes[nombreEquipes - 1];
            for (int k = nombreEquipes - 1; k > 1; k--) {
                rotationEquipes[k] = rotationEquipes[k - 1];
            }
            rotationEquipes[1] = temp;
        }
    }

    public void jouerChampionnat() {
        for (Journee journee : journees) {
            journee.jouerTousLesMatches();
        }
    }

    public void afficherClassement() {
        for (int i = 0; i < equipes.length - 1; i++) {
            for (int j = 0; j < equipes.length - 1 - i; j++) {
                if (equipes[j].getPoints() < equipes[j + 1].getPoints() ||
                        (equipes[j].getPoints() == equipes[j + 1].getPoints() &&
                        (equipes[j].getButsMarques() - equipes[j].getButsEncaisses()) < 
                        (equipes[j + 1].getButsMarques() - equipes[j + 1].getButsEncaisses()))) {
                    Equipe temp = equipes[j];
                    equipes[j] = equipes[j + 1];
                    equipes[j + 1] = temp;
                }
            }
        }

        for (Equipe equipe : equipes) {
            System.out.println(equipe.getNom() + " - Points: " + equipe.getPoints() + " - Goal Average: " + 
                               (equipe.getButsMarques() - equipe.getButsEncaisses()));
        }
    }

    public void afficherProgrammeJournee(int journeeIndex) {
        if (journeeIndex < 0 || journeeIndex >= journees.length) {
            System.out.println("Numéro de journée invalide.");
            return;
        }
        Journee journee = journees[journeeIndex];
        System.out.println("Programme de la journée " + (journeeIndex + 1) + ":");
        for (Match match : journee.getMatches()) {
            System.out.println(match.getEquipe1().getNom() + " vs " + match.getEquipe2().getNom());
        }
    }

    public void afficherProgrammeComplet() {
        for (int i = 0; i < journees.length; i++) {
            afficherProgrammeJournee(i);
        }
    }

    public void afficherListeEquipes() {
        for (Equipe equipe : equipes) {
            System.out.println(equipe.getNom());
        }
    }

    public void afficherToutesStatistiquesEquipes() {
        for (Equipe equipe : equipes) {
            System.out.println("Équipe: " + equipe.getNom());
            System.out.println("Victoires: " + equipe.getVictoires());
            System.out.println("Défaites: " + equipe.getDefaites());
            System.out.println("Matches Nuls: " + equipe.getMatchesNuls());
            System.out.println();
        }
    }

    public void afficherTousResultatsMatchs() {
        for (int i = 0; i < journees.length; i++) {
            System.out.println("Résultats de la journée " + (i + 1) + ":");
            for (Match match : journees[i].getMatches()) {
                if (match.isJoue()) {
                    System.out.println(match.getResultat());
                } else {
                    System.out.println("Match entre " + match.getEquipe1().getNom() + " et " + match.getEquipe2().getNom() + " non joué.");
                }
            }
            System.out.println(); // Ajouter une ligne vide entre chaque journée
        }
    }
    
    public void afficherEquipePlusGrosScore() {
        Equipe maxEquipe = null;
        int maxButs = 0;
        for (Journee journee : journees) {
            for (Match match : journee.getMatches()) {
                if (match.isJoue()) {
                    if (match.getScoreEquipe1() > maxButs) {
                        maxButs = match.getScoreEquipe1();
                        maxEquipe = match.getEquipe1();
                    }
                    if (match.getScoreEquipe2() > maxButs) {
                        maxButs = match.getScoreEquipe2();
                        maxEquipe = match.getEquipe2();
                    }
                }
            }
        }
        if (maxEquipe != null) {
            System.out.println("L'équipe avec le plus gros score est " + maxEquipe.getNom() + " avec " + maxButs + " buts.");
        } else {
            System.out.println("Aucun match joué.");
        }
    }

    public void sauvegarderEtat(String fichier) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fichier))) {
            out.writeObject(this);
        }
    }

    public static Championnat chargerEtat(String fichier) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fichier))) {
            return (Championnat) in.readObject();
        }
    }
}
