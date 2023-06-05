package games;

import java.util.*;

public class Deck {

    private List<Card> deckOfCards = new ArrayList<>();
    private String[] suitsArray = new String[]{"Spades", "Clubs", "Hearts", "Diamonds"};
    private String[] ranksArray = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    public Deck(){
    }
    public Deck(boolean isShuffled){
        this.deckOfCards = createDeck();

        if(isShuffled){
            this.deckOfCards = createDeck();
            shuffleDeck();
        }
    }
    public Deck(int numberOfDecks){
        List<Card> deckOfCards = new ArrayList<Card>();
        for (int i = 0; i < numberOfDecks; i++) {
            addDeck(deckOfCards);
        }
        this.deckOfCards = deckOfCards;
    }
    public Deck(int numberOfDecks, boolean isShuffled){
        List<Card> deckOfCards = new ArrayList<Card>();
        for (int i = 0; i < numberOfDecks; i++) {
            addDeck(deckOfCards);
        }
        this.deckOfCards = deckOfCards;
        if(isShuffled){
            shuffleDeck();
        }
    }
        public List<Card> createDeck(){
        List<Card> deckOfCards = new ArrayList<Card>();
        addDeck(deckOfCards);
        return deckOfCards;
    }
    public List<Card> addDeck(List<Card> deckOfCards){
        for(String suit : suitsArray){
            for(String rank : ranksArray){
                deckOfCards.add(new Card(rank, suit));
            }
        }
        return deckOfCards;
    }
    public void addCard(Card card){
        this.deckOfCards.add(card);
    }
    public void shuffleDeck(){
        Collections.shuffle(this.deckOfCards);
    }
    public Card dealCard(){
        if(this.deckOfCards.size() > 0) {
            Card card = this.deckOfCards.get(0);
            this.deckOfCards.remove(0);
            return card;
        }
        else{
            return null;
        }
    }
    @Override
    public String toString(){
        String hand = "";
        for (Card currentCard : this.deckOfCards){
            hand = hand + " : " +(currentCard.getRank() + " of " + currentCard.getSuit());
        }
        return hand;
    }
    public void printDeck(){
        for (Card currentCard : this.deckOfCards){
            currentCard.printCard();
        }
    }
    public void printDeckSize(){
        System.out.println(this.deckOfCards.size());
    }
    public List<Card> getDeckOfCards() {
        return deckOfCards;
    }
}
