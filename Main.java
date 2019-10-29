/*
Name of student: Camryn Rogers
Class: CS 3345
Section: 003
Semester: Spring 2019
Project 3: LazyBinarySearchTree
 */
package lazybinarysearchtree;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        
        String command;
        int key;
        Scanner in = new Scanner(System.in);

        // Get file name for input and output
        /*System.out.println("Please enter file name for input (include .txt): ");
        inFileName = in.next();*/
        
        /*System.out.println("Please enter file name for output (include .txt): ");
        outFileName = in.next();*/
        
        
        
        Scanner infile = new Scanner(new File(args[0]));
        PrintWriter outfile = new PrintWriter(new File(args[1]));
        
        // Create tree!
        LazyBinarySearchTree tree = new LazyBinarySearchTree();
        
        // While the file has info, process file
        while(infile.hasNext()){
            // Get command
            command = infile.next();
            
            // Call function based on command
            if (command.contains("Insert")){
                if(command.contains(":")){
                    key = Integer.parseInt(command.substring(7));
                    try {
                        if(tree.insert(key))
                            outfile.println("True");
                        else
                            outfile.println("False");
                    } catch (Exception e) {
                        outfile.println("Error in insert: IllegalArgumentException raised");
                    }
                }
                else{
                    outfile.println("Error in line: Insert");
                }
            }
            // Delete's a number from tree
            else if(command.contains("Delete")){
                if(command.contains(":")){
                    key = Integer.parseInt(command.substring(7));
                    try{
                        if(tree.delete(key))
                            outfile.println("True");
                        else
                            outfile.println("False");
                    } catch(Exception e){
                        outfile.println("Error in delete: IllegalArgumentException raised");
                    }
                } 
                else{
                    outfile.println("Error in Line: Delete");
                }
            }
            // Checks to see if the tree contains an integer
            else if(command.contains("Contains")){
                if(command.contains(":")){
                    key = Integer.parseInt(command.substring(9));
                    try {
                        if(tree.contains(key))
                            outfile.println("True");
                        else 
                            outfile.println("False");
                    } catch(Exception e){
                        outfile.println("Error in contains: IllegalArgumentException raised");
                    }
                } 
                else{
                    outfile.println("Error in Line: Contains");
                }   
            }
            // Finds the minimum value (nondeleted)
            else if (command.equals("FindMin")){
                int min = tree.findMin();
                if(min != -1)
                    outfile.println(tree.findMin());
                else
                    outfile.println("Error: No minimum");
            }
            // Finds the maximum value (nondeleted)
            else if (command.equals("FindMax")){
                int max = tree.findMax();
                if(max != -1)
                    outfile.println(tree.findMax());
                else
                    outfile.println("Error: No maximum");
            }
            // Calls toString to print tree (* in front of deleted)
            else if (command.equals("PrintTree")){
                outfile.println(tree.toString());
            }
            // Gets height of tree (including deleted and root)
            else if (command.equals("Height")){
                outfile.println(tree.height());
            }
            // Gets size of tree (including deleted)
            else if (command.equals("Size")){
                outfile.println(tree.size());
            }
            // Else it's an error
            else{
                outfile.println("Error in Line: " + command);
            }
        }
        
        // Close scanners/printwriter
        infile.close();
        outfile.close();
        in.close();
    }
}
