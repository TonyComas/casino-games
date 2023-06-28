package games;

import games.blackjack.Blackjack;
import games.menu.MainMenu;
import games.war.War;

public class GamesCLI {

    private static final int QUIT_GAME = 0;
    private static final int WAR_SELECTION = 1;
    private static final int BLACKJACK_SELECTION = 2;
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.welcomeMessage();
        while(true){
            int selection = menu.showMenu();
            if(selection == QUIT_GAME) {
                break;
            } else if(selection == WAR_SELECTION) {
                War war = new War();
                war.run();
            } else if(selection == BLACKJACK_SELECTION){
                Blackjack blackjack = new Blackjack();
                blackjack.run();
            } else {
                menu.invalidSelection();
            }
        }
    }

}
