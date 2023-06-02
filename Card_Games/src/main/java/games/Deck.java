package games;

import java.util.*;

public class Deck {

    private List<Card> deckOfCards;
    private String[] suitsArray = new String[]{"Spades", "Clubs", "Hearts", "Diamonds"};
    private String[] ranksArray = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    public Deck(){
        this.deckOfCards = buildDeck();
    }
    public Deck(boolean isShuffled){
        this.deckOfCards = buildDeck();

        if(isShuffled){
            this.deckOfCards = buildDeck();
            shuffleDeck();
        }
    }

    public List<Card> buildDeck(){
        List<Card> deckOfCards = new ArrayList<Card>();
        for(String suit : suitsArray){
            for(String rank : ranksArray){
                deckOfCards.add(new Card(rank, suit));
            }
        }
        return deckOfCards;
    }
    public void shuffleDeck(){
        Collections.shuffle(this.deckOfCards);
    }
    public void printDeck(){
        for (Card currentCard : this.deckOfCards){
            currentCard.printCard();
        }
    }

    public List<Card> getDeckOfCards() {
        return deckOfCards;
    }
}
