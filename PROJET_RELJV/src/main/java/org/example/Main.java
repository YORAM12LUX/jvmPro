package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.Scanner;


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

            Scanner scanner = new Scanner(System.in);
            boolean continuer=true;
            while (continuer) {
                System.out.println("BIENVENUE DANS NOTRE SIMULATION DE CHAMPIONNAT EN POULE UNIQUE *SIMULCHIP* ");
                System.out.println();
                System.out.println("******** Veuillez chosir une option ******** :");
                System.out.println("1. Afficher la liste des équipes");
                System.out.println("2. Afficher le programme complet du championnat");
                System.out.println("3. Afficher le programme d'une journée");
                System.out.println("4. Afficher les résultats des matchs");
                System.out.println("5. Afficher les statistiques des équipes");
                System.out.println("6. Afficher le classement du championnat");
                System.out.println("7. Afficher l'équipe qui a eu le plus gros score");
                System.out.println("8. Afficher pourcentage match nuls");
                System.out.println("9. Afficher pourcentage defaites");
                System.out.println("10. Afficher pourcentage victoires");
                System.out.println("11. Sauvegarder sous format JSON");
                System.out.println("0. Quitter");
                System.out.print("Choisir une option pour founir des statisiqtues sur la simulation du championnat : ");

                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        System.out.println("LISTE DES EQUIPES DU CHAMPIONNAT");
                        System.out.println("--------------*---------------");
                        championnat.afficherListeEquipes();
                        break;
                    case 2:
                        System.out.println("PROGRAMME COMPLET DU CHAMPIONNAT");
                        System.out.println("--------------*---------------");
                        championnat.afficherProgrammeComplet();
                        break;
                    case 3:
                        System.out.println("PROGRAMME DE LA JOURNEE");
                        System.out.println("--------------*---------------");
                        System.out.print("Entrez le numéro de la journée : ");
                        int journee = scanner.nextInt();
                        championnat.afficherProgrammeJournee(journee - 1);
                        break;
                    case 4:
                        System.out.println("RESULTAT DES MATCHS");
                        System.out.println("--------------*---------------");
                        championnat.afficherTousResultatsMatchs();
                        break;
                    case 5:
                        System.out.println("STATISTIQUES DES EQUIPES");
                        System.out.println("--------------*---------------");
                        championnat.afficherToutesStatistiquesEquipes();
                        break;
                    case 6:
                        System.out.println("CLASSEMENT DU CHAMPIONNAT");
                        System.out.println("--------------*---------------");
                        championnat.afficherClassement();
                        break;
                    case 7:
                        System.out.println("L'EQUIPE QUI A EU LE PLUS GROS SCORE");
                        System.out.println("--------------*---------------");
                        championnat.afficherEquipePlusGrosScore();
                        break;
                    case 8:
                        System.out.println("POURCENTAGE MATCHS NULS");
                        System.out.println("--------------*---------------");
                        championnat.calculerPourcentageMatchNul();
                        break;

                    case 9:
                        System.out.println("POURCENTAGE DEFAITES ");
                        System.out.println("--------------*---------------");
                        championnat.calculerPourcentageDefaite();
                        break;

                    case 10:
                        System.out.println("POURCENTAGE VICTOIRES ");
                        System.out.println("--------------*---------------");
                        championnat.calculerPourcentageVictoire();
                        break;

                    case 11:
                        System.out.print("Entrez le nom du fichier pour sauvegarder l'état : ");
                        String fichierJson = scanner.nextLine();
                        championnat.sauvegarderEtatJson(fichierJson);
                        break;
                    case 0:
                        continuer = false;
                        break;

                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }

                System.out.println();
            }

            scanner.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        }
    }

