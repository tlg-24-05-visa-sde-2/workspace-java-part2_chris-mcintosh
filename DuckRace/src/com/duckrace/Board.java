package com.duckrace;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/*
 * This is a lookup table of ids to student names.
 * When a duck wins for the first time, we need to find out who that is.
 * This lookup table could be hardcoded with the data, or we could read the data 
 * in from a file, so that no code changes would need to be made for each cohort.
 *
 * Map<Integer,String> studentIdMap;
 * 
 * Integer    String
 * =======    ======
 *    1       John
 *    2       Jane
 *    3       Danny
 *    4       Armando
 *    5       Sheila
 *    6       Tess
 * 
 *
 * We also need a data structure to hold the results of all winners.
 * This data structure should facilitate easy lookup, retrieval, and storage.
 *
 * Map<Integer,DuckRacer> racerMap;
 *
 * Integer    DuckRacer
 * =======    =========
 *            id    name     wins   rewards
 *            --    ----     ----   -------
 *    5        5    Sheila     2    PRIZES, PRIZES
 *    6        6    Tess       1    PRIZES
 *   13       13    Zed        3    PRIZES, DEBIT_CARD, DEBIT_CARD
 *   17       17    Dom        1    DEBIT_CARD
 */

public class Board implements Serializable {
    private static final String DATA_FILE_PATH = "data/board.dat" ;
    private static final String CONFIG_FILE_PATH = "conf/student-ids.csv" ;
    /*
     * If data/board.data exist, the application has been run before at least once.
     * Therefor, re-create the Board object from the binary file.
     *
     * If the file is not there, this is the very first time the app has been run.
     * Therefore, create and return new Board.
     */
    public static Board getInstance(){
        Board board = null;


        if(Files.exists(Path.of(DATA_FILE_PATH))){
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE_PATH))) {
                board = (Board) in.readObject();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            board = new Board();
        }
         return board;
    }

    private final Map<Integer,String> studentIdMap = loadStudentIdMap();
    private final Map<Integer,DuckRacer> racerMap  = new TreeMap<>();

    // private ctor - prevent instantiation outside - only getInstance() can do this
    private Board () {


    }

    /*
     * Updates the board (racerMap) by making a DuckRacer win.
     *
     * This could mean fetching an existing DuckRacer from racerMap,
     * or we might need to creat a new DuckRacer, put it in the map,
     * and then make it win.
     *
     * Either way, it needs to win().
     */
    public void update(int id, Reward reward){
        DuckRacer racer = null;


        if (racerMap.containsKey(id)){     //already in racerMap
            racer = racerMap.get(id);      //fetch it

        }
        else {

            racer = new DuckRacer(id, studentIdMap.get(id)); //not in map
            racerMap.put(id, racer);

        }
        racer.win(reward);

        save();
    }

    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE_PATH))) {
            out.writeObject(this); // write "me" to binary file ("I" am a Board object)

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
      * TODO: render the data as we see it in the "real" application
      */


    public void show(){
        //Maybe ian if-else
        // if (racerMap.isEmpty())
        //there are currently no results to show
        //else
        //do the work below

        Collection<DuckRacer> racers = racerMap.values();

        String header =
                "\n"+
                "  Duck Race Results\n" +
                "=====================\n" +
                        "\n"+
                "id    name     wins   rewards\n" +
                "--    ----     ----   -------\n";

        System.out.println(header);


        for (DuckRacer racer : racers) {
            System.out.printf("%s     %s    %s     %s\n",
                    racer.getId(), racer.getName(), racer.getWins(), racer.getRewards());
        }
    }


    //FOR TESTING ONLY
    void dumpRacerMap(){
        Collection<DuckRacer> racers = racerMap.values();

        for (DuckRacer racer : racers) {
            System.out.println(racer);
        }
    }


    // FOR TESTING ONLY
    void dumpStudentIdMap() {
        System.out.println(studentIdMap);
    }

    private Map<Integer, String> loadStudentIdMap() {
        Map<Integer, String> map = new HashMap<>();

        // Specify the relative path to the file

        try {
            List<String> lines = Files.readAllLines(Path.of(CONFIG_FILE_PATH));

            // Process each line to populate the map
            for (String line : lines) {
                String[] tokens = line.split(",");
                Integer id = Integer.valueOf(tokens[0].trim());
                String name = tokens[1].trim();
                map.put(id, name);
            }

        } catch (IOException e) {
            System.err.println("Error reading file " + CONFIG_FILE_PATH + ": " + e.getMessage());
            // Handle the exception (e.g., log it, throw a custom exception, etc.)
        }

        return map;
    }
}