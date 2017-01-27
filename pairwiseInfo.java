// Alaska Survey Project
// Pairs of plots sets
// Celine Pastore
// January 25, 2017

// This class provides the framework for reading files
// and recording information related to all possible 
// pairs of sets. For example, pairwise Euclidean 
// distance would be recorded for each pair [i,j] (where
// order does not matter). 

import java.util.*;
import java.io.*;

public class pairwiseInfo {
   // fields
   Map<Set<String>, String> pairValues; // maps pair [i,j] -> to -> value
   Set<Set<String>> allPairs; // all pairs [i,j] excluding duplicates [i,j], [j,i]
   
   public pairwiseInfo(String fileName) throws FileNotFoundException{
      allPairs = new TreeSet<Set<String>>(); // is this the right implementation??
      pairValues = new TreeMap<Set<String>, String>();
      
      // fileName = "GIS_data/distance_output.txt";
      Scanner fileReader = new Scanner (new File (fileName)); 
      
      // read heading 
      String line = fileReader.nextLine();
      
      // read file
      while (fileReader.hasNextLine()) {
         line = fileReader.nextLine();
         String[] lineParts = line.split(",");
         addTo(lineParts[1],lineParts[2]);
         

         
         // record the pair's associated value
         String value = lineParts[3];
         
         // map the pair to the value
         
      }
   }
   
   private void addTo(+String i, String j) {
      // build pair (i,j); set implementation ensures that (i,j) == (j,i)
      Set<String> pair = new TreeSet<String>(); // plot pair [i,j]
      pair.add(i); // plot i
      pair.add(j); // plot j
      
      
      // add next pair to set
      allPairs.add(pair); // pair [i,j]
   } 
   
}  
