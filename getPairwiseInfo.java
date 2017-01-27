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
   String[] pair; // plots [i,j]
   String value; // pair-associated data 
   
   getPairwiseInfo(String fileName) throws FileNotFoundException{
      // fileName = "GIS_data/distance_output.txt";
      Scanner fileReader = new Scanner (new File (fileName)); 
      
        
      
   }
   
}