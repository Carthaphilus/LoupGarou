package loup.garou;

import loup.garou.Models.Voyante;
import loup.garou.Models.Sorciere;
import loup.garou.Models.Joueur;
import loup.garou.Models.Villageois;
import loup.garou.Models.Loup_Garou;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FirstMain {

    private static Scanner sc;

    public static void main(String[] args) {
        //Initialisation du jeu
        System.out.println("Combien voulez vous de joueur ?");
        sc = new Scanner(System.in);
        List<Joueur> tabJoueur = new LinkedList<>();
        ArrayList<Joueur> tabJoueurMort = new ArrayList<>();
        int nbJoueur = sc.nextInt();
        Integer i = 0;
        int tour = 0;

        tabJoueur = initialisationGame(nbJoueur, tabJoueur);

        //Debut de la partie
        do {
            System.out.println("C'est la nuit le village s'endort, les joueurs ferment leurs yeux");
            pressAnyKeyToContinue();

            /* 
		 * TOUR DE PREPARATION
		 * 		1: Voleur
		 * 		2: Cupidon
		 * 		3: Amoureux
             */
            //Tour de la voyante
            if (verifRoleJoueur("Voyante", tabJoueur) == true) {
                System.out.println("Que la voyante se reveille.");
                pressAnyKeyToContinue();
                System.out.println("La voyante se rendort.");
                pressAnyKeyToContinue();
            }

            //Tour des loups
            System.out.println("Que les loups se reveille et designe une proie savoureuse");
            pressAnyKeyToContinue();
            System.out.println("Les loups se rendort.");
            pressAnyKeyToContinue();

            //Tour de la sorcière
            if (verifRoleJoueur("Sorciere", tabJoueur) == true) {
                System.out.println("Que la sorcière se reveille, vas t-elle user de la potion de guérison et d'empoisonnement ?");
                System.out.println("Que la sorcière se rendort");
                pressAnyKeyToContinue();
            }

            System.out.println("MAITRE DE JEU: Combien de joueur sont mort cette nuit ?");
            int nbkill = sc.nextInt();
            for (i = 0; i < nbkill; i++) {
                System.out.println("MAITRE DE JEU: Saisir le joueur a éliminé ");
                afficheJoueur(tabJoueur);
                int indexJoueur = sc.nextInt();
                tabJoueurMort.add(killJoueur(indexJoueur, tour, tabJoueur));
            }
            
            afficheJoueurMort(tabJoueurMort, tour);

            if (verifStatutGameVillageois(tabJoueur) == true) {
                System.out.println("Les villageois sont vainqueur");
                System.exit(0);
            } else if (verifStatutGameLoup_Garou(tabJoueur) == true) {
                System.out.println("Les loups-Garou sont vainqueur");
                System.exit(0);
            }

            //Le village se reveil
            System.out.println("Le village se reveille !");
            System.out.println("Nous avons :" + nbkill + " mort aujourd'hui");
            afficheJoueurMort(tabJoueurMort, tour);

            System.out.println("Village concerté vous et choissisez un suspect !");
            System.out.println("MAITRE DE JEU: Saisir le joueur a éliminé ");
            afficheJoueur(tabJoueur);
            int indexJoueur = sc.nextInt();
            tabJoueurMort.add(killJoueur(indexJoueur, tour, tabJoueur));

            afficheJoueurMort(tabJoueurMort, tour);

            if (verifStatutGameVillageois(tabJoueur) == true) {
                System.out.println("Les villageois sont vainqueur");
                System.exit(0);
            } else if (verifStatutGameLoup_Garou(tabJoueur) == true) {
                System.out.println("Les loups-Garou sont vainqueur");
                System.exit(0);
            }

            tour++;

        } while (verifStatutGameVillageois(tabJoueur) == false || verifStatutGameLoup_Garou(tabJoueur) == false);
        if (verifStatutGameVillageois(tabJoueur) == true) {
            System.out.println("Les villageois sont vainqueur");
        } else if (verifStatutGameLoup_Garou(tabJoueur) == true) {
            System.out.println("Les loups-Garou sont vainqueur");
        }
    }

    private static List<Joueur> initialisationGame(Integer nbJoueur, List<Joueur> tabJoueur) {
        Integer i = 0;
        String unNom = "";
        System.out.println("Voulez vous entrer le nom du joueur ?");
        System.out.println("Y = oui et N = non");
        sc = new Scanner(System.in);
        String choix = sc.nextLine();
        for (i = 0; i < nbJoueur; i++) {
            Joueur Joueur = new Joueur();
            if (choix.equals("Y")) {
                System.out.println("Entre le nom du joueur:");
                unNom = sc.nextLine();
            } else {
                unNom = "Joueur" + i;
            }

            Joueur.setNom(unNom);
            tabJoueur.add(Joueur);
        }

        Random r = new Random();
        List<Joueur> roleAttribuer = new LinkedList<>();

        Integer j = 0;
        Long nbLoup_Garou = Math.round(0.3333333333333333 * nbJoueur);
        for (j = 0; j < nbLoup_Garou; j++) {
            int n = r.nextInt(tabJoueur.size());
            Loup_Garou unLoup = new Loup_Garou();
            tabJoueur.get(n).setRole(unLoup);
            roleAttribuer.add(tabJoueur.get(n));
            tabJoueur.remove(n);
        }

        boolean nextStep = false;
        Sorciere uneSorciere = new Sorciere();
        while (nextStep == false) {
            int n = r.nextInt(tabJoueur.size());
            if (tabJoueur.get(n).getRole() instanceof Villageois) {
                tabJoueur.get(n).setRole(uneSorciere);
                roleAttribuer.add(tabJoueur.get(n));
                tabJoueur.remove(n);
                nextStep = true;
            }
        }

        nextStep = false;
        Voyante uneVoyante = new Voyante();
        while (nextStep == false) {
            int n = r.nextInt(tabJoueur.size());
            if (tabJoueur.get(n).getRole() instanceof Villageois) {
                tabJoueur.get(n).setRole(uneVoyante);
                roleAttribuer.add(tabJoueur.get(n));
                tabJoueur.remove(n);
                nextStep = true;
            }
        }
        
        for (Joueur unJoueur : tabJoueur) {
            roleAttribuer.add(unJoueur);
        }

        return roleAttribuer;
    }

    private static void pressAnyKeyToContinue() {
        System.out.println("Appuyer sur entrer pour continuer...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private static Joueur killJoueur(int index, int unTour, List<Joueur> tabJoueur) {
        Joueur joueurMort = tabJoueur.get(index);
        joueurMort.setTourMort(unTour);
        tabJoueur.remove(index);
        return joueurMort;
    }

    private static void afficheJoueur(List<Joueur> tabJoueur) {
        int i = 0;
        for (Joueur unJoueur : tabJoueur) {
            System.out.println("Index: " + i + " Joueur: " + unJoueur.getNom() + " Role:" + unJoueur.getRole().getNom());
            i++;
        }
    }

    private static void afficheJoueurMort(List<Joueur> tabJoueurMort, int unTour) {
        for (Joueur unJoueurMort : tabJoueurMort) {
            if (unJoueurMort.getTourMort() == unTour) {
                System.out.println(unJoueurMort.getNom());
                System.out.println(unJoueurMort.getRole().getNom());
            }
        }
    }

    private static boolean verifStatutGameVillageois(List<Joueur> tabJoueur) {
        boolean fin = true;
        for (Joueur unJoueur : tabJoueur) {
            //Test si parmis les villageois il reste des loup
            if (unJoueur.getRole() instanceof Loup_Garou) {
                fin = false;
            }
        }
        return fin;
    }

    private static boolean verifStatutGameLoup_Garou(List<Joueur> tabJoueur) {
        boolean fin = true;
        for (Joueur unJoueur : tabJoueur) {
            //Test si parmis les villageois si il y reste que des loups
            if (unJoueur.getRole() instanceof Villageois) {
                fin = false;
            }
        }
        return fin;
    }

    private static boolean verifRoleJoueur(String unRole, List<Joueur> tabJoueur) {
        boolean fin = false;
        for (Joueur unJoueur : tabJoueur) {
            //Test si parmis les villageois si il y reste que des loups
            if (unJoueur.getRole().getNom() == unRole) {
                return fin = true;
            } else {
                fin = false;
            }
        }
        return fin;
    }
}
