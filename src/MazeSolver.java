import java.util.Stack;
public class MazeSolver {

    char[][] maze;
    char[][] mazeRes;
    int rows;
    int cols;
    int spaceCounter;

    boolean solved;
    int[] start;
    int spaceFilled ;
    Stack<Integer[]> stack;
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

    public void solveMaze(int i, int j){

        System.out.println("\n\n");
        for(int k = 0;k < rows;k++){
            for (int n = 0;n < cols;n++){
                System.out.print(maze[k][n]);
            }
            System.out.println();
        }

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


    private void charToString(){
        if (solved){
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
    public void getResult(){
        charToString();
    }
}


