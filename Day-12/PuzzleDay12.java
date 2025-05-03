import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleDay12 {

  List<char[]> plantGrid = new ArrayList<>();
  Map<Character, List<int[]>> plantCoord = new HashMap<>();

  public PuzzleDay12() {
    String filePath = "garden.txt";
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        plantGrid.add(line.toCharArray());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void getGrids(List<char[]> plantGrid, Map<Character, List<int[]>> plantCoord) {

    for (int row = 0; row < plantGrid.size(); row++) {

      for (int col = 0; col < plantGrid.get(row).length; col++) {
        char plantType = plantGrid.get(row)[col];
        if (!plantCoord.containsKey(plantType)) {
          plantCoord.putIfAbsent(plantType, new ArrayList<>());
        }
        plantCoord.get(plantType).add(new int[] { row, col });
      }
    }
  }

  private int findPerimeter(List<char[]> plantGrid, char c, int rowS, int colS, boolean[][] visited) {

    if (rowS < 0 || colS < 0 || rowS >= plantGrid.size() || colS >= plantGrid.get(rowS).length) {
      return 1;
    }

    if (visited[rowS][colS]) {
      return 0;
    }

    if (plantGrid.get(rowS)[colS] != c) {
      return 1;
    }

    visited[rowS][colS] = true;

    int perimeter = 0;

    perimeter += findPerimeter(plantGrid, c, rowS, colS + 1, visited);

    perimeter += findPerimeter(plantGrid, c, rowS + 1, colS, visited);

    perimeter += findPerimeter(plantGrid, c, rowS, colS - 1, visited);

    perimeter += findPerimeter(plantGrid, c, rowS - 1, colS, visited);
    return perimeter;
  }

  private int getArea(List<char[]> plantGrid, char c, int rowS, int colS, boolean[][] visited) {

    if (rowS < 0 || colS < 0 || rowS >= plantGrid.size() || colS >= plantGrid.get(rowS).length) {
      return 0;
    }

    if (visited[rowS][colS] || plantGrid.get(rowS)[colS] != c) {
      return 0;
    }

    int area = 1;
    visited[rowS][colS] = true;

    // if (plantGrid.get(rowS)[colS] == c) {

    // count -= 1;
    area += getArea(plantGrid, c, rowS, colS + 1, visited);

    area += getArea(plantGrid, c, rowS + 1, colS, visited);

    area += getArea(plantGrid, c, rowS, colS - 1, visited);

    area += getArea(plantGrid, c, rowS - 1, colS, visited);
    // }
    return area;
  }

  private List<Integer> getAreaList(Map<Character, List<int[]>> plantCoord, List<char[]> plantGrid) {

    List<Integer> list = new ArrayList<>();

    for (Map.Entry<Character, List<int[]>> entries : plantCoord.entrySet()) {
      char plantType = entries.getKey();
      List<int[]> arr = entries.getValue();

      boolean[][] visitedArea = new boolean[plantGrid.size()][plantGrid.get(0).length];
      boolean[][] visitedPerimeter = new boolean[plantGrid.size()][plantGrid.get(0).length];
      for (int[] coord : arr) {

        int row = coord[0];
        int col = coord[1];

        // Only calculate the area and perimeter for each unique region
        if (!visitedArea[row][col]) {
          int area = getArea(plantGrid, plantType, row, col, visitedArea);
          int perimeter = findPerimeter(plantGrid, plantType, row, col, visitedPerimeter);

          // If perimeter is 1, adjust it to the minimum valid perimeter (4)
          if (perimeter == 1) {
            perimeter = 4;
          }

          // Add area * perimeter to the list
          list.add(area * perimeter);
        }
      }
    }
    return list;
  }

  private int sumFinalList(List<Integer> list) {

    int sum = 0;
    for (Integer integer : list) {
      sum += integer;
    }
    return sum;
  }

  private int findPerimeterPartTwo(List<char[]> plantGrid, char c, int rowS, int colS, boolean[][] visited, int oldRow, int oldCol) {

    if (rowS < 0 || colS < 0 || rowS >= plantGrid.size() || colS >= plantGrid.get(rowS).length) {
      return 1;
    }

    if (visited[rowS][colS]) {
      return 0;
    }

    if (plantGrid.get(rowS)[colS] != c) {
      return 1;
    }

    // if ((Math.abs(oldCol - colS) == 1 && rowS == oldRow) || (Math.abs(oldRow - rowS) == 1 && colS == oldCol)) {
    //   return 0;
    // }

    visited[rowS][colS] = true;

    int perimeter = 0;

    perimeter += findPerimeterPartTwo(plantGrid, c, rowS, colS + 1, visited, rowS, colS);

    perimeter += findPerimeterPartTwo(plantGrid, c, rowS + 1, colS, visited, rowS, colS);

    perimeter += findPerimeterPartTwo(plantGrid, c, rowS, colS - 1, visited, rowS, colS);

    perimeter += findPerimeterPartTwo(plantGrid, c, rowS - 1, colS, visited, rowS, colS);
    return perimeter;
  }

  public static void main(String[] args) {

    PuzzleDay12 pd12 = new PuzzleDay12();
    System.out.println();
    System.out.println();
    // for (int i = 0; i < pd12.plantGrid.size(); i++) {
    // System.out.println(Arrays.toString(pd12.plantGrid.get(i)));
    // }

    pd12.getGrids(pd12.plantGrid, pd12.plantCoord);

    // for (int i = 0; i < pd12.plantCoord.get('C').size(); i++) {
    // System.out.println(Arrays.toString(pd12.plantCoord.get('C').get(i)));
    // }

    // Finally part 1 fucking works, goddamn
    // List<Integer> priceList = pd12.getAreaList(pd12.plantCoord, pd12.plantGrid);
    
    // int finalPrice = pd12.sumFinalList(priceList);
    // System.out.println("The final price is " + finalPrice);

    // Now over to cry for part 2
    boolean[][] visited = new boolean[pd12.plantGrid.size()][pd12.plantGrid.get(0).length];
    System.out.println(pd12.findPerimeterPartTwo(pd12.plantGrid, 'R', 0, 0, visited, 0, 0));


  }
}
