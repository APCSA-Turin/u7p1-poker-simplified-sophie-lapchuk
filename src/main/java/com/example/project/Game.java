package com.example.project;
import java.util.ArrayList;


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

    public static void play() { //simulate card playing
        // Will be finished for EC.
    }

// Test code for debugging
    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        
        player1.addCard(new Card("A", "♠"));
        player1.addCard(new Card("A", "♣"));
        
        player2.addCard(new Card("8", "♠"));
        player2.addCard(new Card("8", "♣"));
        
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("7", "♦"));
        communityCards.add(new Card("7", "♥"));
        communityCards.add(new Card("7", "♠"));
        
        String p1Result = player1.playHand(communityCards);
        String p2Result = player2.playHand(communityCards);
        
        String winner = Game.determineWinner(player1, player2, p1Result, p2Result, communityCards);

        System.out.println("Player 1 hand: " +  player1.getAllCards());
        System.out.println("Player 2 hand: " + player2.getAllCards());
        System.out.println("Player 1 hand NO COMMUNITY CARDS: " +  player1.getHand());
        System.out.println("Player 2 hand NO COMMUNITY CARDS: " + player2.getHand());
        System.out.println("Value of player 1 cards: " + Utility.getRankValue(player1.getHand().get(1).getRank()));
        System.out.println("Value of player 2 cards: " + Utility.getRankValue(player2.getHand().get(1).getRank()));
        boolean bool = (Utility.getRankValue(player1.getHand().get(1).getRank())) > Utility.getRankValue(player2.getHand().get(1).getRank());
        System.out.println("Is player 1 > player 2?: " + bool);
        System.out.println("Community Cards: " + communityCards.toString());
        System.out.println("Winner: " + winner);
        System.out.println();
        System.out.println(p1Result);
        System.out.println(p2Result);
    }
}