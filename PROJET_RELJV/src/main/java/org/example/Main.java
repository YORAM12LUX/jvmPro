package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;



public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/HP/Desktop/equipes.txt"));
            String line;
            int nombreEquipes = 0;

            // Compter le nombre de lignes non vides
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    nombreEquipes++;
                }
            }
            reader.close();


            if (nombreEquipes < 4 || nombreEquipes >10 || nombreEquipes%2 !=0) {
                System.out.println("Il faut au moins 4 équipes pour créer un championnat et un nombre pair d'equipes qui nn'exède pas 10");
                return;
            }

            Equipe[] equipes = new Equipe[nombreEquipes];

            // Lire les équipes à nouveau et les stocker dans le tableau
            reader = new BufferedReader(new FileReader("C:/Users/HP/Desktop/equipes.txt"));
            int index = 0;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    equipes[index++] = new Equipe(line.trim());
                }
            }
            reader.close();

            Championnat championnat = new Championnat(equipes);
            championnat.jouerChampionnat();


            System.out.println("LISTE DES EQUIPES DU CHAMPIONNAT");
            System.out.println("--------------*---------------");
            // Afficher la liste des équipes
            championnat.afficherListeEquipes();

            System.out.println("");

            System.out.println("PROGRAMME COMPLET DU CHAMPIONNAT");
            System.out.println("--------------*---------------");
            // Afficher le programme complet du championnat
            championnat.afficherProgrammeComplet();

            System.out.println("");

            // Afficher le programme d'une journée
            System.out.println("PROGRAMME DE LA JOURNEE");
            System.out.println("--------------*---------------");
            championnat.afficherProgrammeJournee(0);

            System.out.println("RESULTAT DES MATCHS");
            System.out.println("--------------*---------------");

            // Afficher le résultat d'un match
            championnat.afficherTousResultatsMatchs();

            System.out.println("");

            System.out.println("STATISTIQUES DES EQUIPES");
            System.out.println("--------------*---------------");
            // Afficher les statistiques d'une équipe
            championnat.afficherToutesStatistiquesEquipes();

            System.out.println("");

            System.out.println("CLASSEMENT DU CHAMPIONNAT");
            System.out.println("--------------*---------------");
            // Afficher le classement du championnat
            championnat.afficherClassement();

            System.out.println("");

            System.out.println("L'EQUIPE QUI A EU LE PLUS GROS SCORE");
            System.out.println("--------------*---------------");
            // Afficher l'équipe ayant fait le plus gros score
            championnat.afficherEquipePlusGrosScore();

            // Sauvegarder l'état du championnat
            championnat.sauvegarderEtat("championnat.dat");
            //Sauvegarder sous format JSON
            championnat.sauvegarderEtatJson("Resume_Championnat.json");
            //Championnat chargerEtatJson = Championnat.chargerEtatJson("Resume_Championnat.json");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
