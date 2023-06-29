package games.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static games.deck.Card.RANKS_ARRAY;
import static games.deck.Card.SUITS_ARRAY;

public class Deck {
    private List<Card> mainDeck = new ArrayList<>();
    private List<Card> discardDeck = new ArrayList<>();

    public Deck(){}
    public Deck(boolean isShuffled){
        addDeck(mainDeck);
        if(isShuffled){
            shuffleDeck();
        }
    }

    public Deck(int numberOfDecks){
        for(int i = 0; i < numberOfDecks; i++) {
            addDeck(mainDeck);
        }
    }

    public Deck(int numberOfDecks, boolean isShuffled) {
        for(int i = 0; i < numberOfDecks; i++) {
            addDeck(mainDeck);
        }
        if(isShuffled){
            shuffleDeck();
        }
    }

    public void addDeck(List<Card> deckOfCards){
        for(String suit : SUITS_ARRAY){
            for(String rank : RANKS_ARRAY){
                deckOfCards.add(new Card(rank, suit));
            }
        }
    }
    public void shuffleDeck(){
        Collections.shuffle(this.mainDeck);
    }

    public Card dealCard(){
        Card card = new Card();
        if(mainDeck.size() > 0){
            card = mainDeck.get(0);
            mainDeck.remove(0);
        }
        return card;
    }
    public void discardCard(Card card){
        discardDeck.add(card);
    }
    public void discardHand(List<Card> hand){
        while(hand.size() > 0){
            discardCard(hand.remove(0));
        }
    }

    @Override
    public String toString(){
        String hand = "";
        for (Card currentCard : this.mainDeck){
            hand = hand + " : " +(currentCard.getRank() + " of " + currentCard.getSuit());
        }
        return hand;
    }

    public List<Card> getMainDeck() {
        return mainDeck;
    }

    public List<Card> getDiscardDeck() {
        return discardDeck;
    }
}
