package com.example.project;
import java.util.ArrayList;
import java.util.Scanner;


public class Game{
// Simulates the game based on the hand entered as a parameter
// Compares the numerical rank of each hand. If they are equal, the highest card is compared
// The player with the higher ranking card wins the game.
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        int p1Rank = Utility.getHandRanking(p1Hand);
        int p2Rank = Utility.getHandRanking(p2Hand);

        if (p1Rank > p2Rank) {
            return "Player 1 wins!";
        }
        if (p1Rank < p2Rank) {
            return "Player 2 wins!";
        }
        else {
                // locates the highest card that is in the hand-- not the community
                p1.sortHand();
                p2.sortHand();
                Card p1HighCard = p1.getHand().get(1);
                Card p2HighCard = p2.getHand().get(1);
                int p1HighRank = Utility.getRankValue(p1HighCard.getRank());
                int p2HighRank = Utility.getRankValue(p2HighCard.getRank());

                if (p1HighRank > p2HighRank) {
                    return "Player 1 wins!";
                }
                if (p1HighRank < p2HighRank) {
                    return "Player 2 wins!";
                }
                else {
                    return "Tie!";
                }
        }
    }

// Simulates the playing of the game, with a user interface that can be interacted with
    public static void play() { 
        Scanner scan = new Scanner(System.in); // new scanner object
        System.out.println("--------------------------------");
        System.out.println("Welcome to (Simplified) Poker!!");
        System.out.println("--------------------------------");
        System.out.println("** YOU ARE PLAYER 1 **");
        System.out.print("1 - Start the Game\n2 - Quit.\nPlease select an option: ");
        int choice = scan.nextInt();

        //initializes a new deck, two players, community cards, and sets score to 0
        Deck deck = new Deck();
        Player current = new Player();
        Player opponent = new Player();
        int currentScore = 0;
        int opponentScore = 0;
        ArrayList<Card> communityCards = new ArrayList<>();
        
        
        while (choice != 2) { // runs through rounds until the user decides to exit out
            if (deck.getCards().size() < 5) { // stop playing if the deck has less than 5 cards to pull
                System.out.println();
                System.out.println("The deck is empty. Please play again");
                System.out.print("Thank you for playing! The final score was ");
                System.out.println(currentScore + " - " + opponentScore);
                break;
            }

            if (choice == 1) {
                // shuffles the deck, pulls cards for each player and community
                deck.shuffleDeck();
                current.getHand().clear();
                opponent.getHand().clear();
                communityCards.clear();

                current.addCard(deck.drawCard());
                current.addCard(deck.drawCard());
                opponent.addCard(deck.drawCard());
                opponent.addCard(deck.drawCard());
                communityCards.add(deck.drawCard());
                communityCards.add(deck.drawCard());
                communityCards.add(deck.drawCard());

                //play both player's hands
                String p1Result = current.playHand(communityCards);
                String p2Result = opponent.playHand(communityCards);
                
                //print out player info
                System.out.println("Your cards: " + current.getAllCards());
                System.out.println("Opponent's cards: " + opponent.getAllCards());
                System.out.println("Community Cards: " + communityCards);

                //increment score based on who won the round
                String winner = determineWinner(current, opponent, p1Result, p2Result, communityCards);
                if (winner.equals("Player 1 wins!")) {
                    currentScore++;
                }
                if (winner.equals("Player 2 wins!")) {
                    opponentScore++;
                }
                // graphic game simulation
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("            " + communityCards);
                System.out.println();
                System.out.println(current.getHand() + "             " + opponent.getHand());
                System.out.println("    " + p1Result + "                       " + p2Result);
                System.out.println();
                System.out.println("                " + winner);
                System.out.println("                    " + currentScore + " - " + opponentScore);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                
                System.out.println();
                System.out.println("Play another round?\n1 - Yes\n2 - Quit");
                System.out.print("Please select an option: ");
                choice = scan.nextInt();
            }
           
        }
        // display final score once the deck has been used
        if (deck.isEmpty()) {
            System.out.println();
            System.out.println("Sorry. The deck is empty. Play again!");
        }

        // display final score once user chooses to exit out of the game
        if (choice == 2) {
            System.out.println();
            System.out.print("Thank you for playing! The final score was ");
            System.out.println(currentScore + " - " + opponentScore);
        }
        scan.close();
    }

// Test code for debugging
    public static void main(String[] args) {
        // Player player1 = new Player();
        // Player player2 = new Player();
        
        // player1.addCard(new Card("A", "♠"));
        // player1.addCard(new Card("A", "♣"));
        
        // player2.addCard(new Card("8", "♠"));
        // player2.addCard(new Card("8", "♣"));
        
        // ArrayList<Card> communityCards = new ArrayList<>();
        // communityCards.add(new Card("7", "♦"));
        // communityCards.add(new Card("7", "♥"));
        // communityCards.add(new Card("7", "♠"));
        
        // String p1Result = player1.playHand(communityCards);
        // String p2Result = player2.playHand(communityCards);
        
        // String winner = Game.determineWinner(player1, player2, p1Result, p2Result, communityCards);

        // System.out.println("Player 1 hand: " +  player1.getAllCards());
        // System.out.println("Player 2 hand: " + player2.getAllCards());
        // System.out.println("Player 1 hand NO COMMUNITY CARDS: " +  player1.getHand());
        // System.out.println("Player 2 hand NO COMMUNITY CARDS: " + player2.getHand());
        // System.out.println("Value of player 1 cards: " + Utility.getRankValue(player1.getHand().get(1).getRank()));
        // System.out.println("Value of player 2 cards: " + Utility.getRankValue(player2.getHand().get(1).getRank()));
        // boolean bool = (Utility.getRankValue(player1.getHand().get(1).getRank())) > Utility.getRankValue(player2.getHand().get(1).getRank());
        // System.out.println("Is player 1 > player 2?: " + bool);
        // System.out.println("Community Cards: " + communityCards.toString());
        // System.out.println("Winner: " + winner);
        // System.out.println();
        // System.out.println(p1Result);
        // System.out.println(p2Result);
        // Deck deck = new Deck();
        // Player current = new Player();
        // Player opponent = new Player();
        // ArrayList<Card> communityCards = new ArrayList<>();
        // int currentScore = 0;
        // int opponentScore = 0;

        // deck.shuffleDeck();

        //         current.addCard(deck.drawCard());
        //         current.addCard(deck.drawCard());
        //         opponent.addCard(deck.drawCard());
        //         opponent.addCard(deck.drawCard());
        //         communityCards.add(deck.drawCard());
        //         communityCards.add(deck.drawCard());
        //         communityCards.add(deck.drawCard());

        //         String p1Result = current.playHand(communityCards);
        //         String p2Result = opponent.playHand(communityCards);


                
        //         System.out.println("Your cards: " + current.getAllCards());
        //         System.out.println("Opponent's cards: " + opponent.getAllCards());
        //         System.out.println("Community Cards: " + communityCards);

        //         String winner = determineWinner(current, opponent, p1Result, p2Result, communityCards);
        //         if (winner.equals("Player 1 wins!")) {
        //             currentScore++;
        //         }
        //         if (winner.equals("Player 2 wins!")) {
        //             opponentScore++;
        //         }
        //         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //         System.out.println("            " + communityCards);
        //         System.out.println();
        //         System.out.println(current.getHand() + "             " + opponent.getHand());
        //         System.out.println("    " + p1Result + "                       " + p2Result);
        //         System.out.println();
        //         System.out.println("                " + winner);
        //         System.out.println("                    " + currentScore + " - " + opponentScore);
        //         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //         System.out.println();
        //         System.out.println("Play another round?\n1 - Yes\n2 - Quit");
        //         System.out.print("Please select an option: ");
        play();        
    }
}