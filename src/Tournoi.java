import java.util.Random;

class Equipe1 {
    String nom;
    int but ;
    int score;
    int nbMatchsJoues;
    int nbVictoires;
    int nbMatchsNuls;
    int nbDefaites;

    Equipe1(String nom) {
        this.nom = nom;
        this.score = 0;
        this.but=0;
        this.nbMatchsJoues = 0;
        this.nbVictoires = 0;
        this.nbMatchsNuls = 0;
        this.nbDefaites = 0;
    }

    void afficher() {
        System.out.println("Nom : " + nom);
        System.out.println("Score : " + score);
        System.out.println("Nombre de matchs joués : " + nbMatchsJoues);
        System.out.println("Nombre de victoires : " + nbVictoires);
        System.out.println("Nombre de matchs nuls : " + nbMatchsNuls);
        System.out.println("Nombre de défaites : " + nbDefaites);
    }
}

class Resultat {
    Equipe vainqueur;
    Equipe perdant;
    int score;
    int but;

    Resultat(Equipe vainqueur, Equipe perdant, int score,int but) {
        this.vainqueur = vainqueur;
        this.perdant = perdant;
        this.score = score;
        this.but=but;
    }
}

public class Tournoi {
    public static int genererScore() {
        Random rand = new Random();
        return rand.nextInt(6) ; // Génère un nombre aléatoire entre 0 et 4, puis ajoute 1 pour obtenir un score entre 1 et 5
    }

    public static void main(String[] args) {
        // Création des équipes
        Equipe1[] equipes = {
                new Equipe1("Equipe 1"),
                new Equipe1("Equipe 2"),
                new Equipe1("Equipe 3"),
                new Equipe1("Equipe 4")
        };

        // Simulation des matchs
        Random rand = new Random();
        for (int i = 0; i < equipes.length; i++) {
            for (int j = i + 1; j < equipes.length; j++) {
                // Simulation d'un score aléatoire entre 1 et 5 pour chaque équipe
                 equipes[i].but= genererScore();
                 equipes[j].but = genererScore();

                // Détermination du vainqueur
                if (equipes[i].but> equipes[j].but) {
                    // L'équipe i gagne
                    equipes[i].score += 3;
                    equipes[j].score += 0;
                    equipes[i].nbVictoires++;
                    equipes[j].nbDefaites++;
                } else if (equipes[i].but < equipes[j].but) {
                    // L'équipe j gagne
                    equipes[j].score += 3;
                    equipes[i].score+=0;
                    equipes[j].nbVictoires++;
                    equipes[i].nbDefaites++;
                } else {
                    // Match nul
                    equipes[i].score += 1;
                    equipes[j].score += 1;
                    equipes[i].nbMatchsNuls++;
                    equipes[j].nbMatchsNuls++;
                }

                // Mise à jour du nombre de matchs joués
                equipes[i].nbMatchsJoues++;
                equipes[j].nbMatchsJoues++;
            }
        }

        // Affichage des résultats
        for (Equipe1 equipe : equipes) {
            equipe.afficher();
            System.out.println();
        }
    }
}
