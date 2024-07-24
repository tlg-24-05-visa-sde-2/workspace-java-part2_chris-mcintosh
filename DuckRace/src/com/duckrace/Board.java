package com.duckrace;

import java.io.IOException;
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

public class Board {

    private final Map<Integer,String> studentIdMap = loadStudentIdMap();
    private final Map<Integer,DuckRacer> racerMap  = new TreeMap<>();

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
        String filePath = "conf/student-ids.csv";

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            // Process each line to populate the map
            for (String line : lines) {
                String[] tokens = line.split(",");
                Integer id = Integer.valueOf(tokens[0].trim());
                String name = tokens[1].trim();
                map.put(id, name);
            }

        } catch (IOException e) {
            System.err.println("Error reading file " + filePath + ": " + e.getMessage());
            // Handle the exception (e.g., log it, throw a custom exception, etc.)
        }

        return map;
    }
}