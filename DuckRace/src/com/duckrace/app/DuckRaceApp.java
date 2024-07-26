package com.duckrace.app;

import com.duckrace.Board;
import com.duckrace.Reward;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class DuckRaceApp {
    private Scanner scanner = new Scanner(System.in);
    private Board board = Board.getInstance();

    public void execute(){
        welcome();
        showBoard();
        int id = promptForId();
        Reward reward = promptForReward();
        updateBoard(id, reward);
        showBoard();

    }

    private void updateBoard(int id, Reward reward) {
        board.update(id, reward);
    }

    private Reward promptForReward() {
        Reward reward = null;

        boolean validInput = false;
        while(!validInput){
            System.out.print("Please enter [D]ebit card or [P]rizes: ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.matches("D|P")){
                reward = (input.equals("D")) ? Reward.DEBIT_CARD: Reward.PRIZES;
                validInput = true;
            }
        }
        return reward;    }

    private int getMaxIdFromFile() {
        int maxId = 0;
        String filePath = "conf/student-ids.csv";

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            for (String line : lines) {
                String[] tokens = line.split(",");
                int id = Integer.parseInt(tokens[0].trim());
                if (id > maxId) {
                    maxId = id;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + filePath + ": " + e.getMessage());
            // Handle the exception (e.g., log it, throw a custom exception, etc.)
        }

        return maxId;
    }


    private int promptForId() {
        int id = 0;
        int maxID = getMaxIdFromFile();

        boolean validInput = false;
        while(!validInput) {
            System.out.println("Please enter id of the winner [1-" + maxID + "]: ");  //TODO: dont hardcode the 11
            String input = scanner.nextLine().trim(); //BLOCKS for [Enter]
            if (input.matches("\\d{1,2}")){ //now you can safely parseInt
                id = Integer.parseInt(input);
                if (id >= 1 && id <+ maxID) {
                    validInput = true;
                }
            }
        }
        return id;
    }

    private void showBoard() {
        board.show();
    }

    private void welcome() {
        System.out.println("\n");
        System.out.println("- - - - - - -    - -    - - -    - - - -    - - - - -");
        System.out.println("W E L C O M E    T O    T H E    D U C K    R A C E!");
        System.out.println("- - - - - - -    - -    - - -    - - - -    - - - - -");
        System.out.println("\n");
    }
}