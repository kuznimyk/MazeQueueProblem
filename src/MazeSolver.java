import java.util.Stack;
public class MazeSolver {
    /* maze that has traversed paths  */
    char[][] maze;
    /* maze that has only straight path to the exit */
    char[][] mazeRes;
    /* number of rows */
    int rows;
    /* number of columns */
    int cols;
    /* number of spaces in the maze */
    int spaceCounter;

    /* boolen variable that defines whether the maze is solved */
    boolean solved;
    /* starting point of the maze */
    int[] start;

    /* counts the amount of spaces filled */
    int spaceFilled ;
    /* stack */
    Stack<Integer[]> stack;

    /* constructor that initializes variables */
    public MazeSolver(char[][] maze, int rows, int cols, int spaceCounter, int[] start){
        this.maze = maze;
        this.rows = rows;
        this.cols = cols;
        this.mazeRes = new char[rows][cols];
        stack = new Stack<>();
        this.spaceCounter = spaceCounter;
        this.start = start;
        solved = false;
        spaceFilled = 1;
        for(int i = 0;i < rows;i++){
            for (int j = 0;j < cols;j++){
                mazeRes[i][j] = maze[i][j];
            }

        }
        stack.push(new Integer[]{start[0],start[1]});

    }

    /* recursion algorithm that solves the maze */
    public void solveMaze(int i, int j){

        /* checks if the maze is solved or if the stack is empty then end the recursion */
        if ((i == 0||j == cols - 1 || i == rows - 1  || solved == true) && stack.empty() == false) {
            if (j == cols- 1){
                stack.push(new Integer[] {i, cols - 1});
                maze[i][cols-1] = '.';
            }
            else if (i == 0){
                stack.push(new Integer[] {0, j});
                maze[0][j] = '.';
            }
            else if (i == rows - 1)
                stack.push(new Integer[] {i, j});
                maze[i][j] = '.';
            solved = true;
            return;
        }
        else if(spaceFilled < spaceCounter - 1)
        {
            /* checks for the free space on the right then on top then bottom and then left.
            * if algorithm gets stuck then last value from the stack is popped and the function calls itself again
            * but now the code runs from the point where the recursion at that point ended up.
            *also checks if the algorithm is solved, if yes, the returns. Also checks if the algorithm ended up at the starting point
            * if yes, then algorithm could not be solved.
            * each correct cordinate in the maze is storied in the stack.
            *  */
            if (maze[i][j] == ' ')
                maze[i][j] = '.';
            if (maze[i][j + 1] == ' ') {
                if (solved == true || stack.isEmpty()){return;}
                spaceFilled += 1;
                stack.push(new Integer[]{i, j + 1});
                solveMaze(i, j+1);
            }
            if (solved == true || stack.isEmpty()){return;}
            if (maze[i - 1][j] == ' ' ) {
                if (solved == true){return;}
                spaceFilled += 1;
                stack.push(new Integer[]{i - 1, j});
                solveMaze(i-1,j);
            }
            if (solved == true || stack.isEmpty()){return;}
            if (maze[i + 1][j] == ' ') {

                spaceFilled += 1;
                stack.push(new Integer[]{i, j + 1});
                solveMaze(i + 1,j);
            }
            if (solved == true || stack.isEmpty()){return;}
            if ( j>0 && maze[i][j - 1] == ' ') {

                spaceFilled += 1;
                stack.push(new Integer[]{i, j - 1});
                solveMaze(i, j - 1);
            }
            else if(j == 0){stack.pop(); return;}
            if (solved == true || stack.isEmpty()){return;}




            if (maze[i][j + 1] != ' ' && maze[i- 1][j] != ' ' && maze[i+1][j] != ' ' && maze[i][j- 1] != ' '){
                Integer[] del = stack.pop();
                return;
            }


        }
        else{
            solved = false;
            return;
        }

    }


    public void getResult(){
        if (solved){
            /* if the algorithm is solved then fill all the spaces in the unsolved maze with the dots in the cordinates located in the stack */
            Integer[] cords = new Integer[2];
            while(!stack.isEmpty()){
                cords = stack.pop();
                mazeRes[cords[0]][cords[1]] = '.';
            }

            System.out.println("\n\n");
            for(int k = 0;k < rows;k++){
                for (int n = 0;n < cols;n++){
                    System.out.print(mazeRes[k][n]);
                }
                System.out.println();
            }
            System.out.println("Maze is solved");
        }
        else{
            /* if the maze is unsolvable, then print out the path that algorithm traversed through the maze */
            System.out.println("\n\n");
            for(int k = 0;k < rows;k++){
                for (int n = 0;n < cols;n++){
                    System.out.print(maze[k][n]);
                }
                System.out.println();
            }

            System.out.println("The maze could not be solved");
        }
    }
}


