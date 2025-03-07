package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c); //add Card object from the parameter to the hand
    }

    public String playHand(ArrayList<Card> communityCards){     //if statements to return what kind of hand it is  
        allCards.clear();
        allCards.addAll(hand);
        allCards.addAll(communityCards); //combine all the cards
        sortAllCards();
        //check and return proper string for the type of hand
        if (isRoyalFlush()) {
            return "Royal Flush";
        } 
        if (isStraightFlush()) {
            return "Straight Flush";
        }
        if (isFourOfAKind()) {
            return "Four of a Kind";
        }
        if (isFullHouse()) {
            return "Full House";
        }
        if (isFlush()) {
            return "Flush";
        }
        if (isStraight()) {
            return "Straight";
        }
        if (isThreeOfAKind()) {
            return "Three of a Kind";
        }
        if (isTwoPair()) {
            return "Two Pair";
        }
        if (isPair()) {
            return "A Pair";
        }
        // If no hand is found, search for the highest card in the player's hand
        Card highCard = allCards.get(4);
        for (Card card : hand) {
            if (card.equals(highCard)) {
                return "High Card";
            }
        }
        // If the highest card is in the community, there is no hand
        return "Nothing";
    }

    //if straight, if lowest is 10, and its a flush, it must be a royal flush (ex)
    //make methods from the worst hand to use while going up
    //if double and triple

// Implementation of a sorting algorithm to sort all cards based on ascending order of a rank
    public void sortAllCards(){
        for (int i = 0; i < allCards.size(); i++) {
            int min = i;
            for (int j = i + 1; j < allCards.size(); j++) {
                if (Utility.getRankValue(allCards.get(j).getRank()) < Utility.getRankValue(allCards.get(min).getRank())) {
                    Card temp = allCards.get(j);
                    allCards.set(j, allCards.get(min));
                    allCards.set(min, temp);
                }
            }
        }
    }

// Organizing the two cards in a player's hand in ascending order
    public void sortHand() {
        int rankCard1 = Utility.getRankValue(hand.get(0).getRank());
        int rankCard2 = Utility.getRankValue(hand.get(1).getRank());
        if (rankCard1 > rankCard2) {
            Card temp = hand.get(0);
            hand.set(0, hand.get(1));
            hand.set(1, temp);
        }
    }
        // use sorted list to loop through it and check if the hand is a straight if the difference between each is 1
        // return last element to get the max

// Mariam helped me with this method
// Finding the frequency of the rank occurring in the hand
    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> freq = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            freq.add(0); //add a 0 slot for every rank
        }

        for (Card card : allCards) {
            int idx = Utility.getRankValue(card.getRank()) - 2; //move down 2 because rank values start at 2, not 0
            freq.set(idx, freq.get(idx) + 1); //increment one to count occurances in allCards 
        }
        return freq;
    }

// Finding the frequency of each suit occurring in a player's hand
    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> freq = new ArrayList<>();
        for (int j = 0; j < Utility.getSuits().length; j++) {
            freq.add(0); //add 0 for every slot
        }
        int i = 0;
        for (Card card : allCards) {
            if (card.getSuit().equals(suits[i])) {
                freq.set(i, freq.get(i) + 1); //if another suit is found, increment number of occurances
            }
        }
        return freq; 
    }
   
    @Override
    public String toString(){
        return hand.toString();
    }

// Searches for 2 occurrances of one rank in a hand
    public boolean isPair() {
        ArrayList<Integer> freq = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            freq.add(0);
        }
        freq = findRankingFrequency();
        for (int num : freq) {
            if (num == 2) {
                return true;
            }
        }
        return false;
    }

// Searches for 2 occurances of two different pairs
    public boolean isTwoPair() {
        if (isPair()) {
            int pairCount = 0;
            // find number of pairs
            for (int freq : findRankingFrequency()) {
                if (freq == 2) {
                    pairCount++;
                }
            }
            // if it is 2, then it is a two pair
            if (pairCount == 2) {
                return true;
            }
        }
        return false;
    }

// Similar to the Pair, searches for 3 of a rank in a hand
    public boolean isThreeOfAKind() {
        for (int num : findRankingFrequency()) {
            if (num == 3) {
                return true;
            }
        }
        return false;
    }

// Checks if the difference between each rank in the deck is 1. If it is, it means that the cards are incrementing in order.
    public boolean isStraight() {
        boolean bool = true;
        for (int i = 0; i < allCards.size() - 1; i++) {
            if (Utility.getRankValue(allCards.get(i+1).getRank()) - Utility.getRankValue(allCards.get(i).getRank()) != 1) {
                    bool = false;
            }
        }
        return bool;
    }

// Checks the hand to see if each card is of one suit. 
    public boolean isFlush() {
        for (int freq : findSuitFrequency()) {
            if (freq == 5) {
                return true;
            }
        }
        return false;
    }

// Checks the hand for one set of 3 cards of one rank, and a set of 2 cards of another rank
    public boolean isFullHouse() {
        boolean has3 = false;
        boolean has2 = false;
        for (int freq : findRankingFrequency()) {
            if (freq == 3) {
                has3 = true;
            }
            if (freq == 2) {
                has2 = true;
            }
        } 
        return has3 && has2;
    }

// Searches for 4 occurrances of one rank in the hand
    public boolean isFourOfAKind() {
        for (int freq : findRankingFrequency()) {
            if (freq == 4) {
                return true;
            }
        }
        return false;
    }

// Checks if the hand is a straight AND a flush. If it is, then it has to be a straight flush
    public boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

// Checks that the hand is a straight AND the smallest card is a 10. If it is, it must go 10, J, Q, etc., so it is a royal flush
    public boolean isRoyalFlush() {
        sortAllCards();
        return isStraightFlush() && (Utility.getRankValue(allCards.get(0).getRank()) == 10);
    }

//{"♠","♥","♣", "♦"};
// Test code for debugging
    public static void main(String[] args) {
        Player player = new Player();
        Card a = new Card("A", "♠");
        Card b = new Card("A", "♣");
        Card c = new Card("7", "♦");
        Card d = new Card("7", "♥");
        Card e = new Card("7", "♠");
        player.addCard(a);
        player.addCard(b);

        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(c);
        communityCards.add(d);
        communityCards.add(e);
        System.out.println("Unsorted deck: " + player.getHand());
        player.sortAllCards();
        System.out.println("Sorted deck: " + player.getHand());
        System.out.println("Sorted Full Deck: " + player.getAllCards());
        player.playHand(communityCards);
        System.out.println("Played hand: " + player.playHand(communityCards));
        System.out.println("Full Deck: " + player.getAllCards());

        System.out.println();
        System.out.println("Frequency List: " + player.findRankingFrequency());
        
        
    }
    /*
     * player1.addCard(new Card("A", "♠"));
        player1.addCard(new Card("A", "♣"));
        
        player2.addCard(new Card("8", "♠"));
        player2.addCard(new Card("8", "♣"));
        
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("7", "♦"));
        communityCards.add(new Card("7", "♥"));
        communityCards.add(new Card("7", "♠"));
     */
}

