import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PuzzleDay14P2 {

  Map<Integer, List<int[]>> robotPosition = new HashMap<>();
  int[][] grid;

  public PuzzleDay14P2(int rows, int columns) {

    grid = new int[rows][columns];

    for (int i = 0; i < grid.length; i++) {
      Arrays.fill(grid[i], 0);
    }

    String filePath = "robot.txt";

    final Pattern pattern = Pattern.compile("p=(-?\\d+),(-?\\d+)\\s+v=(-?\\d+),(-?\\d+)");

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

      String line;
      int robotNum = 0;
      while ((line = br.readLine()) != null) {

        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
          int px = Integer.parseInt(matcher.group(1));
          int py = Integer.parseInt(matcher.group(2));

          int vx = Integer.parseInt(matcher.group(3));
          int vy = Integer.parseInt(matcher.group(4));

          int[] position = new int[] { px, py };
          int[] velocity = new int[] { vx, vy };

          robotPosition.put(robotNum, new ArrayList<>());
          robotPosition.get(robotNum).add(position);
          robotPosition.get(robotNum).add(velocity);
          robotNum++;
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void moveRobots(int[][] grid, Map<Integer, List<int[]>> robotPosition) {

    String fileName = "grid_iterations.txt"; // Single output file

    int rowsLen = grid.length;
    int columnsLen = grid[0].length;

    try (FileWriter writer = new FileWriter(new File(fileName), false)) {
      for (int i = 0; i < 10403; i++) {

        // int px = 0, py = 0;
        for (Map.Entry<Integer, List<int[]>> entries : robotPosition.entrySet()) {
          int robotNum = entries.getKey();
          List<int[]> robotData = entries.getValue();

          int px = robotData.get(0)[0];
          int py = robotData.get(0)[1];
          int vx = robotData.get(1)[0];
          int vy = robotData.get(1)[1];

          if (grid[py][px] != 0) {
            grid[py][px]--;
          }

          px += vx;
          py += vy;
          if (px < 0) {
            px = columnsLen + px;
          }
          if (py < 0) {
            py = rowsLen + py;
          }
          if (px >= columnsLen) {
            px = px - columnsLen;
          }
          if (py >= rowsLen) {
            py = py - rowsLen;
          }

          robotPosition.get(robotNum).get(0)[0] = px;
          robotPosition.get(robotNum).get(0)[1] = py;
          grid[py][px]++;
        }

        // Convert the grid to char[][]
        char[][] convertedGrid = convertGridToChar(grid);

        // Append each iteration to the file
        writeIteration(writer, convertedGrid, i + 1);

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void writeIteration(FileWriter writer, char[][] grid, int iteration) throws IOException {
    writer.write("Iteration: " + iteration + "\n"); // Add iteration header
    for (char[] row : grid) {
      for (char cell : row) {
        writer.write(cell + "");
      }
      writer.write("\n"); // Newline after each row
    }
    writer.write("\n"); // Add extra newline between iterations
  }

  private int getSafetyFactor(int[][] grid) {

    int rowDelete = (grid.length - 1) / 2;
    int colDelete = (grid[0].length - 1) / 2;
    int quadOne = 0, quadTwo = 0, quadThree = 0, quadFour = 0;

    for (int row = 0; row < grid.length; row++) {
      if (row == rowDelete) {
        continue;
      }
      for (int col = 0; col < grid[0].length; col++) {
        if (col == colDelete) {
          continue;
        }

        // Quadrant 1
        if (row < rowDelete && col < colDelete && grid[row][col] > 0) {
          quadOne += grid[row][col];
        }

        // Quadrant 2
        if (row < rowDelete && col > colDelete && grid[row][col] > 0) {
          quadTwo += grid[row][col];
        }

        // Quadrant 3
        if (row > rowDelete && col < colDelete && grid[row][col] > 0) {
          quadThree += grid[row][col];
        }

        // Quadrant 4
        if (row > rowDelete && col > colDelete && grid[row][col] > 0) {
          quadFour += grid[row][col];
        }
      }
    }
    return (quadOne * quadTwo * quadThree * quadFour);
  }

  public static char[][] convertGridToChar(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    char[][] result = new char[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 0) {
          result[i][j] = '.'; // Replace 0 with '.'
        } else {
          result[i][j] = (char) ('0' + grid[i][j]); // Convert int to char (e.g., 1 -> '1')
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {

    PuzzleDay14P2 pd14 = new PuzzleDay14P2(103, 101);
    System.out.println();

    pd14.moveRobots(pd14.grid, pd14.robotPosition);
    System.out.println();

    System.out.println("The safety factor is " + pd14.getSafetyFactor(pd14.grid));

    // Convert the grid to char[][]
    // char[][] convertedGrid = convertGridToChar(pd14.grid);

    // // Print the converted grid
    // for (char[] row : convertedGrid) {
    // for (char cell : row) {
    // System.out.print(cell + " ");
    // }
    // System.out.println(); // Newline after each row
    // }

  }

}
