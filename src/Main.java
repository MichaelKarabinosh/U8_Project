import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    public static void main(String[] args) {




//        System.out.println("hello");
         String [][] maze = getMaze("src/Input");
        MazeSolver parttwo = new MazeSolver(maze);
        ArrayList<String> pathWithoutDupes = parttwo.solve();
//        System.out.println(pathWithoutDupes);
        ArrayList<String> pathWithoutDupesFixed = fixPathPartTwo(pathWithoutDupes);
        PrintPartTwo(pathWithoutDupesFixed);


    }


    public static void PrintPartTwo(ArrayList<String> path) // formats answer so I can ctrl f in document
    {
        for (int i = 0; i < path.size(); i++)
        {

            if (i != path.size() - 1)
            {
                System.out.print("(" + path.get(i).replace(",", ", ") + ") ---> ");
            }
            else {
                System.out.println("(" + path.get(i).replace(",", ", ") + ")");
            }
        }
    }





    public static ArrayList<String> fixPathPartTwo(ArrayList<String> path)
    {
        // replace all parentheses with nothing, allows me to parse pairs easier in the form x,y
        path.replaceAll(s -> s.replaceAll("\\(", "").replaceAll("\\)", ""));


        for (int i = 0; i < path.size() - 1; i++)
        {
            String[] pair1 = path.get(i).split(","); // get pair 1 and pair 2 numbers
            String[] pair2 = path.get(i + 1).split(",");
            int p1y = Integer.parseInt(pair1[0]);
            int p1x = Integer.parseInt(pair1[1]);
            int p2y = Integer.parseInt(pair2[0]);
            int p2x = Integer.parseInt(pair2[1]);
            if (!(Math.abs(p2y - p1y) <= 1 && Math.abs(p2x - p1x) == 0 ||
                    Math.abs(p2y - p1y) == 0 && Math.abs(p2x - p1x) <= 1))
                // check if two coordinate pairs are not more than 1 movement away from each other.
                /* (0,0) (2,0) returns false because abs p2x - p1x = 0
                /* (0,0) (1,1) also returns false because abs p2y - p1y = 0, but p2x - p1x = 1
                //

                 */
            {

//                System.out.println("hy");
                String correctPair = path.get(i + 1);
//                System.out.println(correctPair);
                String [] correctPairC = path.get(i + 1).split(",");
                int correctY = Integer.parseInt(correctPairC[0]);
                int correctX = Integer.parseInt(correctPairC[1]);
                int indexEnd = i + 1; // indexEnd is index of the number that we know must be in the list
                int backwardsCount = 1; // b
//                System.out.println(indexEnd);

                while (true)
                {
                    if (indexEnd - backwardsCount >= 0) {
                        pair1 = path.get(indexEnd - backwardsCount).split(",");
                        p1y = Integer.parseInt(pair1[0]);
                        p1x = Integer.parseInt(pair1[1]);
//                        System.out.println(backwardsCount);
//                        System.out.println(p1y + "p");
//                        System.out.println(p1x);
//                        System.out.println(backwardsCount);
//                        if (backwardsCount == 6 && correctX == 35)
//                        {
//                            System.out.println(Arrays.toString(pair1));
//                        }
                        if ((Math.abs(correctY - p1y) <= 1 && Math.abs(correctX - p1x) == 0
                        || Math.abs(correctY - p1y) == 0 && Math.abs(correctX - p1x) <= 1))
                        {
                            break;
                        }
                        backwardsCount++;
                    }
                }
//                System.out.println(indexEnd - backwardsCount);
//                System.out.println(path.size() + "lk");
//                if (correctX == 35)
//                {
//                    System.out.println(indexEnd - backwardsCount);
//                    System.out.println(path.get(indexEnd));
//                    System.out.println(backwardsCount);
//                    System.out.println(path.get(66));
//                }
                for (int j = indexEnd; j > indexEnd - backwardsCount; j--)
                {
//                    if (correctX == 35)
//                    {
//                        System.out.println(path.get(j));
//                    }
                    path.remove(j);

                }
                path.add(indexEnd - backwardsCount + 1, correctPair);


                i -= backwardsCount;
//                System.out.println(path);
            }


        }
        return path;
    }




//    public static ArrayList<String> partOne(String [][] maxe, int x, int y){
//        boolean mazeSolved = false;
//        int curx = x;
//        int cury = y;
//        ArrayList<String> path = new ArrayList<>();
//        path.add("(0,0)");
//        while (!mazeSolved)
//        {
//            if (cury < maxe.length - 1 && maxe[cury + 1][curx].equals("."))
//            {
//                maxe[cury][curx] = "#";
//                cury++;
//            }
//            else if (cury > 0 &&maxe[cury - 1][curx].equals(".")) {
//                maxe[cury][curx] = "#";
//                cury--;
//            }
//            else if (curx > 0 && maxe[cury][curx - 1].equals(".")) {
//
//                maxe[cury][curx] = "#";
//                curx--;
//            }
//            else if (curx < maxe[0].length - 1 &&maxe[cury][curx + 1].equals("."))
//            {
//                maxe[cury][curx] = "#";
//                curx++;
//            }
//
//            String subPath = "(";
//            subPath += cury + ",";
//            subPath += curx + ")";
//            path.add(subPath);
//            subPath = "";
//            if (cury == maxe.length - 1)
//            {
//                if (curx == maxe[0].length - 1)
//                {
//                    mazeSolved = true;
//                }
//            }
//        }
//        return path;
//    }




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
        int cols = fileData.getFirst().length();

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