/*
<<<<<<< HEAD

                                                Ý Æ Ã-Ø Œ ¿

Code description:
    Find the way out of the maze using algorithm. Through the recursion in my case.

Authors:
    * Mykyta Kuznietsov

Class:
    * AUCSC 112 LAB 1H04

ID numbers:
    * 1796900

Date:
    * 26 March, 2024

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /* maze in form of char array */
        char[][] symbolArr;
        String symbols = "";
        int rowCounter = 0;
        int colCounter = 0;


        File myFile = new File("maze1.txt"); //filename defined at top
        try{ //file opens should be in try block to catch
            //exceptions, such as when file is not found
            Scanner input = new Scanner(myFile);
            while (input.hasNextLine()) {
                symbols += input.nextLine() + "\n";
                rowCounter += 1;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR - File not found");
        }

        /* counts the columns and rows*/
        char[] charArr = symbols.toCharArray();
        for (char c : charArr){
            if (c == '\n'){
                break;
            }
            else{colCounter++;}
        }

        /* initializes char array*/
        symbolArr = new char[rowCounter][colCounter];
        /* counts the amount of spaces */
        int spaces = countSpaces(symbols);




        /* turns 1d array of characters into 2d array of characetrs by splitting by \n  */
        int counter = 0;
        for(int i =0; i < rowCounter;i++){
            for(int j = 0; j < colCounter;j++){
                if (charArr[counter] == '\n'){
                    counter += 1;
                }
                symbolArr[i][j] = charArr[counter];
                counter++;
            }
        }

        /* finds the startgin point */
        int[] start = new int[2];
        for (int i =0; i < rowCounter;i++){
            if (symbolArr[i][0] == ' '){
                start[0] = i;
                start[1] = 0;
                break;
            }
        }
        /* class for solving the maze */
        MazeSolver solve = new MazeSolver(symbolArr,rowCounter,colCounter,spaces,start);
        /* method that solved the maze */
        solve.solveMaze(start[0],start[1]);
        /* method that prints out the path for exit, or path which algorithm went through if the maze is not solvable */
        solve.getResult();

    }

    /* function that counts spaces in the maze */
    public static int countSpaces(String maze){
        char[] mazeArr = maze.toCharArray();
        int spaceCounter = 0;
        for(char c : mazeArr){
            if (c == ' '){
                spaceCounter++;
            }

        }
        return spaceCounter;
    }

}