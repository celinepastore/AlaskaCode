// Alaska Survey Project
// Euclidean Distance
// Celine Pastore
// January 23, 2017

// This program reads the distance_output.txt file.
// The file contains pairwise Euclidean distances 
// between all hexagongal plot centers i,j in the 
// set of all plots. The file does include duplicate 
// pairs (e.g. [0,34] and [34,0]).

import java.io.*;
import java.util.*;

public class ReadDistance {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner fileReader = new Scanner (new File ("GIS_data/distance_output.txt"));
      //ArrayList<String> dist_ij = new ArrayList<String>();
      Map<Set<String>, String> pairDist = new HashMap<Set<String>, String>(); // pairwise distance between all i,j in I
      
      // read headings
      int numLines = 0;
      String line = fileReader.nextLine();
      System.out.println(line);
      
      // count number of lines in file      
      while (fileReader.hasNextLine()) {
         Set<String> pair = new HashSet<String>();
         line = fileReader.nextLine();
         String[] lineParts = line.split(",");
         // [1] = INPUT_FID
         // [2] = NEAR_FID
         // [3] = DISTANCE
         if (lineParts[1].equals("34") && lineParts[2].equals("0")) {
            System.out.println("Duplicates [0,34] and [34,0] are present");   
         }
         numLines++;
         pair.add(lineParts[1]);
         pair.add(lineParts[2]);
         pairDist.put(pair, lineParts[3]);
         
         
      }
      System.out.println("Number of lines: " + numLines);
   }
}