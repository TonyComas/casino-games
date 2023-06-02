package games;

import java.util.HashMap;
import java.util.Map;

public class Card {
    private String suit;
    private String rank;



    public Card(String rank, String suit){
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }
    public int getValue(){
        Map<String, Integer> cardValueMap = new HashMap<String, Integer>();
        cardValueMap.put("2", 2);
        cardValueMap.put("3", 3);
        cardValueMap.put("4", 4);
        cardValueMap.put("5", 5);
        cardValueMap.put("6", 6);
        cardValueMap.put("7", 7);
        cardValueMap.put("8", 8);
        cardValueMap.put("9", 9);
        cardValueMap.put("10", 10);
        cardValueMap.put("Jack", 11);
        cardValueMap.put("Queen", 12);
        cardValueMap.put("King", 13);
        cardValueMap.put("Ace", 14);
        return cardValueMap.get(this.getRank());
    }
    public int getBlackJackValue(){
        Map<String, Integer> cardValueMap = new HashMap<String, Integer>();
        cardValueMap.put("2", 2);
        cardValueMap.put("3", 3);
        cardValueMap.put("4", 4);
        cardValueMap.put("5", 5);
        cardValueMap.put("6", 6);
        cardValueMap.put("7", 7);
        cardValueMap.put("8", 8);
        cardValueMap.put("9", 9);
        cardValueMap.put("10", 10);
        cardValueMap.put("Jack", 10);
        cardValueMap.put("Queen", 10);
        cardValueMap.put("King", 10);
        cardValueMap.put("Ace", 11);
        return cardValueMap.get(this.getRank());
    }

    public void printCard(){
        System.out.println(this.getRank() + " of " + this.getSuit());
    }
}
