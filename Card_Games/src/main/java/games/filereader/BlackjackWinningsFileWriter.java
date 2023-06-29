package games.filereader;
import games.player.Player;

import java.io.*;
import java.util.*;

public class BlackjackWinningsFileWriter {

    private File reportFile;
    List<Player> playerList = new ArrayList<>();


    public BlackjackWinningsFileWriter() {
        reportFile = new File("BlackjackWinnings.txt");
    }

    public void writeTotalWinningsReport(List<Player> players){
        loadWinningsReport();
        clearFile();
        addPlayersToPlayerList(players);
        logToReport();
    }

    private void loadWinningsReport() {
        try(Scanner fileScanner = new Scanner(reportFile)){
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                if(line.equals("")) {
                    break;
                }
                String[] playerParts = line.split("\\|");
                if(playerParts.length > 1){
                    playerList.add(buildPlayer(line));
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Incorrect File");
        }
    }

    private void clearFile(){
        try{
            if(reportFile.delete()){
                reportFile.createNewFile();
            }
        } catch(IOException e){

        }
    }

    private void addPlayersToPlayerList(List<Player> players){
        for(int i = 1; i < players.size(); i++){
            playerList.add(players.get(i));
        }
    }

    private void logToReport(){
        int totalBalance = 0;
        for(Player player : playerList){
            String toPrint = player.getName() + "|" + player.getBalance();
            writeToFile(toPrint);
            totalBalance += player.getBalance();
        }
        writeTotalBalanceToReport(totalBalance);
    }

    private void writeToFile(String toPrint){
        try(FileWriter writer = new FileWriter(reportFile, true)){
            writer.write(toPrint);
            writer.write("\n");
        } catch (IOException e){

        }
    }

    private Player buildPlayer(String line) {
        String[] playerParts = line.split("\\|");
        String name = playerParts[0];
        int balance = Integer.parseInt(playerParts[1]);
        return new Player(name, balance);
    }

    private void writeTotalBalanceToReport(int totalBalance){
        writeToFile("------------------------------------");
        String toPrint = "";
        if(totalBalance > 0) {
            toPrint = "The House owes a total of $" + totalBalance;
        } else if (totalBalance <= 0){
            toPrint = "The Players owe the House a total of $" + (totalBalance * -1);
        }
        writeToFile(toPrint);
    }
}
