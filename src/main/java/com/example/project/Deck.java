package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void initializeDeck() {
        //initialize ranks and suits list
        String[] ranks = Utility.getRanks();
        String[] suits = Utility.getSuits();
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit)); //iterate to make a full deck of each rank for each suit
            }
        }
    }

    public  void shuffleDeck(){ 
        Collections.shuffle(cards); //using the shuffle method from Collections (https://stackoverflow.com/questions/16112515/how-to-shuffle-an-arraylist)
    }

    public Card drawCard(){
        Card card = cards.get(0);
        cards.remove(0); //removing drawn card from list to avoid duplicates
        return card;
    }

    public boolean isEmpty(){
        if (cards.size() == 0) { //checking if the deck list size is 0
            return true;
        }
        return false;  //if not, it is not empty
    }

    public static void main(String[] args) {
        //testing new deck
        Deck deck = new Deck();
        System.out.println(deck.getCards());

        //testing draw card
        System.out.println(deck.drawCard());
        System.out.println(deck.drawCard());
        System.out.println(deck.drawCard());
        System.out.println(deck.drawCard());
        System.out.println(deck.getCards());

        //testing isEmpty
        System.out.println(deck.isEmpty());
        while (!deck.isEmpty()) {
            deck.drawCard();
        }
        System.out.println(deck.getCards());
        System.out.println(deck.isEmpty());
    }
}


