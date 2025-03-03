package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c); //add Card object from the parameter to the hand
    }

    public String playHand(ArrayList<Card> communityCards){     //if statements to return what kind of hand it is  
        return "Nothing";
    }

    //if straight, if lowest is 10, and its a flush, it must be a royal flush (ex)
    //make methods from the worst hand to use while going up
    //if double and triple

    public void sortAllCards(){} // return last element to get the max
    // use sorted list to loop through it and check if the hand is a straight if the difference between each is 1

    public ArrayList<Integer> findRankingFrequency(){
        return new ArrayList<>(); 
    }

    public ArrayList<Integer> findSuitFrequency(){
        return new ArrayList<>(); 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }

    public static void main(String[] args) {
        // testing addCard
        Player player = new Player();
        player.addCard(new Card(Utility.getSuits()[0], Utility.getRanks()[1]));
        System.out.println(player.getHand());

        //
    }




}
