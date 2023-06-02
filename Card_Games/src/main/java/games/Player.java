package games;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand = new ArrayList<Card>();

    public Player(){

    }
    public Player(List<Card> hand){
        this.hand = hand;
    }

    public void dealtCard(Card card){
        this.hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }
    public int numberOfAces(){
        int numberOfAces = 0;
        for(Card card : this.hand){
            if(card.getRank().equals("Ace")){
                numberOfAces++;
            }
        }
        return numberOfAces;
    }
    public void printHand(){
        for(Card card : this.hand){
            card.printCard();
        }
    }
}
