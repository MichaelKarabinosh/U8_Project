import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


//     for (String[] array: maxe)
//     {
//         System.out.println(Arrays.toString(array));
//     }



         String[][] maze = getMaze("src/Input");
        System.out.println(partOne(maze, 0, 0));

        System.out.println("hello");
        MazeSolver parttwo = new MazeSolver(maze);
        System.out.println(parttwo.solve());

    }

    public static ArrayList<String> partOne(String [][] maxe, int x, int y){
        boolean mazeSolved = false;
        int curx = x;
        int cury = y;
        ArrayList<String> path = new ArrayList<>();
        path.add("(0,0)");
        while (!mazeSolved)
        {
               if (cury < maxe.length - 1 && maxe[cury + 1][curx].equals("."))
               {
                   maxe[cury][curx] = "#";
                   cury++;
               }
               else if (cury > 0 &&maxe[cury - 1][curx].equals(".")) {
                   maxe[cury][curx] = "#";
                   cury--;
               }
               else if (curx > 0 && maxe[cury][curx - 1].equals(".")) {

                   maxe[cury][curx] = "#";
                   curx--;
               }
               else if (curx < maxe[0].length - 1 &&maxe[cury][curx + 1].equals("."))
               {
                   maxe[cury][curx] = "#";
                   curx++;
               }

               String subPath = "(";
            subPath += cury + ",";
            subPath += curx + ")";
            path.add(subPath);
            subPath = "";
            if (cury == maxe.length - 1)
            {
                if (curx == maxe[0].length - 1)
                {
                    mazeSolved = true;
                }
            }
        }
        return path;
    }



    public static String[][] getMaze(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        String[][] maze = new String[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                maze[i][j] = d.charAt(j) + "";
            }
        }
        return maze;

    }
}