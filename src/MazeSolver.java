import java.util.ArrayList;

public class MazeSolver {
    int x = 0;
    int y = 0;
    boolean solved = false;
    ArrayList<String> path = new ArrayList<>();
    String [][] maze;
    public int direction = 0;
    // 1 = up
    // 2= right
    // 3= down
    // 4 = left


    public MazeSolver(String [] [] maze)
    {
        direction = 2;
        this.maze = maze;
    }


    public ArrayList<String> solve()
    {
        int reps = 0;
        while (!solved || reps < 10000)
        {
            superMove();
            reps++;
            System.out.println(reps);
        }
        return path;
    }




    public void superMove()
    {
         if (isWallLeft())
         {
             if (canMoveForward())
             {
                 simpleMove();
             }
             else {
                 while (!canMoveForward())
                 {
                     directionChange();
                 }
                 simpleMove();
             }
         }
         else {
            direction--;
            simpleMove();
         }
    }



    public void directionChange()
    {
        if (direction == 1)
        {
            direction = 2;
        }
        if (direction == 2)
        {
            direction = 3;
        }
        if (direction == 3)
        {
            direction = 4;
        }
        if (direction == 4)
        {
            direction = 1;
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
            if (x - 1 < 0 || maze[y][x-1].equals("#"))
            {
                return true;
            }
            else {
                return false;
            }
        }
        if (direction == 2)
        {
            if (y - 1 < 0 || maze[y - 1][x].equals("#"))
            {
                return true;
            }
            else {
                return false;
            }
        }
        if (direction == 3)
        {
            if (x + 1 > maze[0].length || maze[y][x+1].equals("#"))
            {
                return true;
            }
            else {
                return false;
            }
        }
        if (direction == 4)
        {
            if (y + 1 > maze.length || maze[y+1][x].equals("#"))
            {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean canMoveForward()
    {
        if (direction == 1 && y - 1 > 0)
        {
            if (maze[y - 1][x].equals("."))
            {
                return  true;
            }
            return false;
        }
        if (direction == 2 && x + 1 < maze[0].length)
        {
            if (maze[y][x + 1].equals("."))
            {
                return  true;
            }
            return false;
        }
        if (direction == 3 && y + 1 < maze.length)
        {
            if (maze[y + 1][x].equals("."))
            {
                return  true;
            }
            return false;
        }
        if (direction == 4 && x - 1 > 0)
        {
            if (maze[y][x - 1].equals("."))
            {
            return  true;
            }
            return false;
        }
        return false;
    }









}
