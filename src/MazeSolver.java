import java.util.ArrayList;
import java.util.LinkedHashSet;

public class MazeSolver {
    int x = 0;
    int y = 0;
    boolean solved = false;
    ArrayList<String> path = new ArrayList<>();
    String [][] maze;
    public int direction;
    // 1 = up
    // 2= right
    // 3= down
    // 4 = left


    public MazeSolver(String [] [] maze)
    {
        path.add("(0,0)");
        direction = 2;
        this.maze = maze;
    }


    public ArrayList<String> solve()
    {
        int reps = 0;
        while (solved || reps < 100000000)
        {
            complexMove();
            reps++;
            if (y == maze.length - 1 && x == maze[0].length - 1)
            {
              solved = true;
              break;
            }
//            System.out.println(reps);
//            System.out.println(reps);
//            System.out.println(path);
        }
        return removeDupes(path);
    }


    public ArrayList<String> removeDupes(ArrayList<String> path) {
       return new ArrayList<String>(new LinkedHashSet<String>(path));
    }

    public void effecientComplexMove()
    {
        if (!isWallLeft())
        {
            turnCCW();
            simpleMove();
        }
        else if (canMoveForward())
        {
            simpleMove();
        }
        else {
            turnCCW();
            turnCCW();
        }
    }





    public void complexMove() // first check if a wall is left, if not turn ccw and move forward
    // if a wall is left, move forward
            // if no wall, then check if you can move forward at all
            // if you can't keep rotating until you can
    {
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println(direction);
//        System.out.println(isWallLeft());
//        System.out.println(canMoveForward());
//        System.out.println(canMoveForward());
         if (isWallLeft())
         {
             if (canMoveForward())
             {
                 simpleMove();
             }
             else {
                 do {
                     directionBruteForce();
                 } while (!canMoveForward());
                 simpleMove();
             }
         }
         else {
            turnCCW();
            simpleMove();
         }
//        System.out.println(path.getLast());
    }



    public void directionBruteForce()
    {
        direction = (direction % 4) + 1;
//        if (direction == 1)
//        {
//            direction = 2;
//        }
//        else if (direction == 2)
//        {
//            direction = 3;
////            System.out.println(canMoveForward());
//        }
//        else if (direction == 3)
//        {
//            direction = 4;
//        }
//        else if (direction == 4)
//        {
//            direction = 1;
//        }
    }

    public void turnCCW()
    {
        if (direction == 1)
        {
            direction = 4;
        }
        else {
            direction--;
        }
    }



    public void simpleMove()
    {
        if (direction == 1)
        {
            y--;
        }
        if (direction == 2)
        {
            x++;
        }
        if (direction == 3)
        {
            y++;
        }
        if (direction == 4)
        {
            x--;
        }
        path.add("(" + y + "," + x + ")");
    }




    public boolean isWallLeft()
    {
        if (direction == 1)
        {
            return x - 1 < 0 || maze[y][x - 1].equals("#");
        }
       else  if (direction == 2)
        {
            return y - 1 < 0 || maze[y - 1][x].equals("#");
        }
        else if (direction == 3)
        {
            return x + 1 > maze[0].length || maze[y][x + 1].equals("#");
        }
        else if (direction == 4)
        {
            return y + 1 > maze.length || maze[y + 1][x].equals("#");
        }
        return false;
    }

    public boolean canMoveForward()
    {
        if (direction == 1 && y - 1 > -1) // if looking up and moving 1 up is ok
        {
           return maze[y - 1][x].equals(".");
        }
        if (direction == 2 && x + 1 < maze[0].length) // if looking right and moving 1 right is ok
        {
            return maze[y][x + 1].equals(".");
        }
        if (direction == 3 && y + 1 < maze.length) // if looking down and moving 1 down is ok
        {
            return  maze[y + 1][x].equals(".");

        }
        if (direction == 4 && x - 1 > -1) // if looking left and moving left 1 is ok
        {
            return maze[y][x - 1].equals(".");
        }
        return false;
    }









}
