// Author: Zeus Polanco Salgado
// Assignment 1 - CS 203
// May 2021

import java.time.Instant;
import java.time.Duration;
import java.io.FileWriter;
import java.io.IOException;

public class Timer{
  static private NQueensExhaustiveSearch queensES;
  static private NQueensIterativeRepair queensIR;

  public static void main(String[] args){
    final String csvTitle = "algorithm,n,nanoseconds\n";
    String fileTitle = args[0];
    queensES = new NQueensExhaustiveSearch();
    queensIR = new NQueensIterativeRepair();

    // Test speed of algorithms
    try{
      FileWriter results = new FileWriter(fileTitle);
      results.write(csvTitle);
      long iterativeTime = 0, exhaustiveTime = 0;
      System.out.println("\nTesting...");

      for(int j=0; j<5; j++)
      for(int i=4; i<151; i++){
       iterativeTime = testIterativeRepair(i);
       System.out.format("Iterative Repair %d x %d : %d 10e-9 seconds\n"
         , i, i, iterativeTime);
       results.write(String.format("IterativeRepair,%d,%d\n",i,iterativeTime));
      }

      // for(int j=0; j<10; j++)
      // for(int i=4; i<11; i++){
      //   exhaustiveTime = testExhaustiveSearch(i);
      //   System.out.format("Exhaustive Search %d x %d : %d 10e-9 seconds\n"
      //     , i, i, exhaustiveTime);
      //   results.write(String.format("ExhaustiveSearch,%d,%d\n",i,exhaustiveTime));
      // }

      results.close();
      System.out.println("Done.");
    }catch(IOException e){System.out.println("Error writting results!");}
  }

  private static long testExhaustiveSearch(int n){
    Instant start, stop;
    long ns;
    start = Instant.now();
    queensES.ExhaustiveSearch(n);
    stop = Instant.now();
    ns = Duration.between(start, stop).toNanos();
    return ns;
  }

  private static long testIterativeRepair(int n){
    Instant start, stop;
    long ns;
    start = Instant.now();
    queensIR.IterativeRepair(n);
    stop = Instant.now();
    ns = Duration.between(start, stop).toNanos();
    return ns;
  }
}
