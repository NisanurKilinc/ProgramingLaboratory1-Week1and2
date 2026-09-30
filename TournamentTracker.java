
package tournamenttracker;

import java.util.Scanner;

public class TournamentTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Teams
        String[] teams = {"Team A", "Team B", "Team C", "Team D"};

        // Match pairings (0 = Team A, 1 = Team B, 2 = Team C, 3 = Team D)
        int[] team1 = {0, 0, 0, 1, 1, 2};
        int[] team2 = {1, 2, 3, 2, 3, 3};

        // Scores array
        int[] score1 = new int[6];
        int[] score2 = new int[6];

        // Stats tracking
        int[] played = new int[4];
        int[] wins = new int[4];
        int[] draws = new int[4];
        int[] losses = new int[4];
        int[] points = new int[4];
        int[] gf = new int[4]; // Goals For
        int[] ga = new int[4]; // Goals Against

        // 1. Display Fixtures
        System.out.println("--- TOURNAMENT FIXTURES ---");
        for (int i = 0; i < 6; i++) {
            System.out.println("Match " + (i + 1) + ": " + teams[team1[i]] + " vs " + teams[team2[i]]);
        }

        // 2. Input Scores & Calculate Stats
        System.out.println("\n--- ENTER SCORES ---");
        for (int i = 0; i < 6; i++) {
            int t1 = team1[i];
            int t2 = team2[i];

            System.out.print("Match " + (i + 1) + ": " + teams[t1] + " goals: ");
            score1[i] = scanner.nextInt();
            System.out.print("Match " + (i + 1) + ": " + teams[t2] + " goals: ");
            score2[i] = scanner.nextInt();

            // Matches played
            played[t1]++;
            played[t2]++;

            // Goals
            gf[t1] += score1[i];
            ga[t1] += score2[i];
            gf[t2] += score2[i];
            ga[t2] += score1[i];

            // Points and Win/Draw/Loss
            if (score1[i] > score2[i]) {
                wins[t1]++;
                losses[t2]++;
                points[t1] += 3;
            } else if (score2[i] > score1[i]) {
                wins[t2]++;
                losses[t1]++;
                points[t2] += 3;
            } else {
                draws[t1]++;
                draws[t2]++;
                points[t1] += 1;
                points[t2] += 1;
            }
        }

        // 3. Print Match Results
        System.out.println("\n--- MATCH RESULTS ---");
        for (int i = 0; i < 6; i++) {
            System.out.println("Match " + (i + 1) + ": " + teams[team1[i]] + " " + score1[i] + " - " + score2[i] + " " + teams[team2[i]]);
        }

        // 4. Print Standings
        System.out.println("\n--- STANDINGS TABLE ---");
        System.out.println("Team\tMP\tW\tD\tL\tGF\tGA\tGD\tPTS");
        for (int i = 0; i < 4; i++) {
            int gd = gf[i] - ga[i];
            System.out.println(teams[i] + "\t" + played[i] + "\t" + wins[i] + "\t" + draws[i] + "\t" + losses[i] + "\t" + gf[i] + "\t" + ga[i] + "\t" + gd + "\t" + points[i]);
        }

        // 5. Find Champion
        int champ = 0;
        for (int i = 1; i < 4; i++) {
            int currentGD = gf[i] - ga[i];
            int champGD = gf[champ] - ga[champ];

            if (points[i] > points[champ]) {
                champ = i;
            } else if (points[i] == points[champ] && currentGD > champGD) {
                champ = i;
            }
        }

        System.out.println("\nTournament Champion: " + teams[champ]);

        scanner.close();
    }
}
