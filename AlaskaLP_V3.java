// Alaska Survey Linear Program
// Formulated by Sandor Toth
// Coded here by Celine Pastore
// January 20, 2017

import java.io.*;
import java.util.*;

// PURPOSE: This program produces a CPLEX ready text 
// file containing the linear programming formulation
// of the Alaska survey optimization problem. 

// INPUT DATA: heterogeneity index (h_i), 
// length of flight segments (l_n), the fixed cost of 
// building/using helibases (c_m), the cost of flying a 
// crew from a particular helibase to plot (c_im), and 
// the euclidean distance between each plot pair (d_ij). 
  
import java.util.*;

public class AlaskaLP_V3 {
   public static final int I = 5743; // number of plots
   public static void main(String[] args) throws FileNotFoundException {
      
      // Sets:
      // (1) Plots, i \in I
      
      // (2) Existing heli-bases, m \in B 
      //     (Eventaully can be expanded to include potential sites)
      
      //existing base locations plot indicies:
      String[] bases =  {};
      Set<String> heliBases = new HashSet<String>();
      
      
      
      
      
      // (3) Potential flight segments, n \in N
      // (4) Flight segments that contain plot i, P_i
      // (5) Plots in a flight segment n, S_n
      // (6) All pairs of plots (i,j) \in E
      
      
      // LP parameters:
      //   (1)
      //   heterogeneity index for each plot i
      //   "<hetero_val>"
      ArrayList<String> hetero_i = new ArrayList<String>();
      
      //   (2)
      //   Euclidean distance between each pair of plots i,j
      ArrayList<String> dist_ij = new ArrayList<String>();
      
      //   (3)
      //   Distance from each plot i to the nearest airfield m
      //   "<airfield_index> <distance>" 
      ArrayList<String> c_im = new ArrayList<String>();
      
      // fill parameters (1) & (3) with plot data from hexagon.shp file
      // Function input: LP parameters to fill, followed by associated 
      //    data column headings
      String[] headings = {"Heterogen", "HeliBase", "HeliDist"};
      gatherData(hetero_i, c_im, headings); 
      
      // fill parameter (2) with pairwise distance data
      //gatherPairwise(dist_ij);
   
   }
   
   // gathers and returns data from GIS attribute table text file 
   // parameters: keyword string of desired data heading
   // pre: key heading must be present in first line of text file,
   //      else throws IllegalArgumentException
   public static void gatherData(ArrayList<String> hetero_i, ArrayList<String> c_im, 
               String[] headings) throws FileNotFoundException {
      // client can change file to read
      Scanner input = new Scanner(new File("GIS_data/hexagon_attributes.txt"));
      
      // find column indicies for multiple keywords
      Map<String, Integer> headingIndex = findHeadingIndex(headings, input); 
      for (String head : headingIndex.keySet()) {
         if (headingIndex.get(head) == -1) {
            throw new IllegalArgumentException("Keyword \"" + headingIndex.get(head) + "\" is not found.");
         }   
      }   

      while (input.hasNextLine()) {
         String line = input.nextLine();
         String lineParts[] = line.split(",");
         // filled with 1st keyword values (E.g. values in "Heterogen" column)
         hetero_i.add(lineParts[headingIndex.get("Heterogen")]); // first index tracked = hetero
         
         // filled with 2nd and 3rd keyword values (E.g. values in HeliBase & HeliDist) 
         c_im.add(lineParts[headingIndex.get("HeliBase")] + " " + lineParts[headingIndex.get("HeliDist")]);
         
         // OPTIONAL: output results to file
         // PrintStream output = new PrintStream(new File("GIS_data/heterogeneity.txt"));
         // output.println(lineParts[headingIndex.get("Heterogen")]); 
      }
      
      /*
      // OPTIONAL:
      // find index of single key heading (if exists)
      int keyIndex = findKeyIndex(keyHeading, input);
      if (keyIndex == -1) {
            throw new IllegalArgumentException("Keyword \"" + keyHeading + "\" is not found.");
      }
      */  
      
   }
   
   // find the key index of desired data column
   // pre: the input file must have column heading line
   //      and the key heading must be in the line 
   // parameter: key heading String, e.g.: 
   // return: heading name and associated column number
   //       ; returns -1 if name not found
   public static Map<String, Integer> findHeadingIndex(String[] headings, Scanner input) {
      Map<String, Integer> headingIndex = new TreeMap<String, Integer>();
      String line = input.nextLine();
      String[] colNames = line.split(",");
      for (int i = 0; i < colNames.length; i++) { // for each column in file
         String title = colNames[i];
         for (String head : headings) { // for each key heading 
            // add to map with default -1 value
            if (!headingIndex.containsKey(head)) {
                  headingIndex.put(head, -1);
            }
            // if found in file, update column value 
            if (title.equals(head)) {  
               headingIndex.put(head, i);
            }
         }       
      }
      return headingIndex;
   } 
 
   
   // find the key index of desired data column
   // pre: the input file must have column heading line
   //      and the key heading must be in the line 
   // parameter: key heading String
   // return: index integer; returns -1 if not found
   public static int findKeyIndex(String keyHeading, Scanner input) {
      String line = input.nextLine();
      String[] colNames = line.split(",");
      for (int i = 0; i < colNames.length; i++) {
         String next = colNames[i];
         //System.out.println(next);
         if (next.equals(keyHeading)) {
            return i;
         } 
      }
      return -1;
   }
   
   // find key indicies for multiple keywords
   // parameters: collection of keywords, file scanner
   // returns list of associated column indicies
   // Ex: {"Heterogen", "HeliBase", "HeliDist"} => [20, 23, 24]; 
   public static int[] findKeyIndex(String heading[], Scanner input) {
      int[] headIndex = new int[heading.length];
      Arrays.fill(headIndex, -1);
      String line = input.nextLine();
      String[] colNames = line.split(",");
      for (int i = 0; i < colNames.length; i++) {
         String next = colNames[i];
         //System.out.println(next);
         for (int j = 0; j < heading.length; j++){
            if (next.equals(heading[j])) {
               headIndex[j] = i;
            }
         } 
      }
      return headIndex;
   }
   

}