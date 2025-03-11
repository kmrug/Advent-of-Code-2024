import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PuzzleDay6 {

  List<char[]> mainGrid = new ArrayList<>();
  final int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // right down left up

  private void extract() {

    String filePath = "grid.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;

      while ((line = br.readLine()) != null) {

        char[] charArr = line.toCharArray();
        mainGrid.add(charArr);
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());	
    }
  }

  private int[] findStart(List<char[]> array) {

    for (int row = 0; row < array.size(); row++) {

      for (int col = 0; col < array.get(row).length; col++) {

        if (array.get(row)[col] == '^') {
          return new int[] { row, col };
        }
      }

    }
    return null;
  }

  private int findPath(List<char[]> grid, int[] startPosition, boolean loopCheck) {

    Set<List<Integer>> set = new HashSet<>();

    boolean up = true, left = false, down = false, right = false, stopNow = false;
    int rowSize = grid.size();
    int colSize = grid.get(0).length;
    int gridSize = rowSize * colSize;
    int posX = startPosition[0];
    int posY = startPosition[1];
    int loopCounter = 0;
    set.add(Arrays.asList(posX, posY));

    while (!stopNow) {

      if (loopCheck) {
        loopCounter++;
        if (loopCounter > gridSize) {
          return -1; // loop detected
        }
      }

      if (up) {

        posX += directions[3][0];
        posY += directions[3][1];

        if (posX < 0 || posY < 0 || posX >= colSize || posY >= rowSize) {
          stopNow = true;
        }

        else if (grid.get(posX)[posY] == '#') { // turn right
          posX -= directions[3][0];
          posY -= directions[3][1];
          up = false;
          right = true;
        } else {
          set.add(Arrays.asList(posX, posY));
        }

      } else if (right) {

        posX += directions[0][0];
        posY += directions[0][1];

        if (posX < 0 || posY < 0 || posX >= colSize || posY >= rowSize) {
          stopNow = true;
        }

        else if (grid.get(posX)[posY] == '#') { // turn right
          posX -= directions[0][0];
          posY -= directions[0][1];
          right = false;
          down = true;
        } else {
          set.add(Arrays.asList(posX, posY));
        }

      } else if (down) {

        posX += directions[1][0];
        posY += directions[1][1];

        if (posX < 0 || posY < 0 || posX >= colSize || posY >= rowSize) {
          stopNow = true;
        }

        else if (grid.get(posX)[posY] == '#') { // turn right
          posX -= directions[1][0];
          posY -= directions[1][1];
          down = false;
          left = true;
        } else {
          set.add(Arrays.asList(posX, posY));
        }

      } else if (left) {

        posX += directions[2][0];
        posY += directions[2][1];

        if (posX < 0 || posY < 0 || posX >= colSize || posY >= rowSize) {
          stopNow = true;
        }

        else if (grid.get(posX)[posY] == '#') { // turn right
          posX -= directions[2][0];
          posY -= directions[2][1];
          left = false;
          up = true;
        } else {
          set.add(Arrays.asList(posX, posY));
        }
      }
    }
    return set.size();
  }

  private int addLoop(List<char[]> grid, int[] startPosition) {
    int colSize = grid.size();
    int rowSize = grid.get(0).length;

    int gridSize = rowSize * colSize;
    System.out.println("The grid size is: " + gridSize);
    int count = 0;
    int posX = 0;
    int posY = 0;

    for (int i = 0; i < gridSize; i++) {

      if (grid.get(posX)[posY] == '.') {
        grid.get(posX)[posY] = '#';
        int isLoop = findPath(grid, startPosition, true);
        if (isLoop == -1) {
          count++;
        }
        grid.get(posX)[posY] = '.';
      }
      posX = i / rowSize; // Calculate the row index
      posY = i % rowSize; // Calculate the column index
      // System.out.println("x coord: " + posX + " y coord: " + posY);
    }
    return count;
  }

  public static void main(String[] args) {
    PuzzleDay6 pd6 = new PuzzleDay6();
    System.out.println();

    pd6.extract();
    // for (int i = 0; i < pd6.mainGrid.size(); i++) {
    // System.out.println(Arrays.toString(pd6.mainGrid.get(i)));
    // }

    int[] startPosition = pd6.findStart(pd6.mainGrid);
    // System.out.println("The number of distinct paths the guard walks is " +
    // pd6.findPath(pd6.mainGrid, startPosition, false));
    System.out.println("The number of loopable positions in the grid is: " + pd6.addLoop(pd6.mainGrid, startPosition));
  }
}
