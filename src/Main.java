import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
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
        char[] charArr = symbols.toCharArray();
        for (char c : charArr){
            if (c == '\n'){
                break;
            }
            else{colCounter++;}
        }

        symbolArr = new char[rowCounter][colCounter];
        int spaces = countSpaces(symbols);




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



    }
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