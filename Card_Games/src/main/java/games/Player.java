package games;

import java.util.ArrayList;
import java.util.List;

public class Player{
    //private List<Card> hand = new ArrayList<Card>();
    private Deck hand = new Deck();
    private String name;

    public Player(){}
    public Player(String name){
        this.name = name;

    }
    public Player(Deck hand){
        this.hand = hand;
    }

    public int numberOfAces(){
        int numberOfAces = 0;
        for(Card card : this.hand.getDeckOfCards()){
            if(card.getRank().equals("Ace")){
                numberOfAces++;
            }
        }
        return numberOfAces;
    }
    public void addCard(Card card){
        this.hand.addCard(card);
    }
    public void printHand(){
        for(Card card : this.hand.getDeckOfCards()){
            card.printCard();
        }
    }
    public Deck getHand() {
        return hand;
    }
    public String getName(){
        return name;
    }
}
