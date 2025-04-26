import java.io.*;
import java.util.*;

public class PuzzleDay10 {

  List<List<Integer>> grid = new ArrayList<>();
  int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // right, down, left, up
  List<Integer> scoreCount = new ArrayList<>();

  public PuzzleDay10() {

    String filePath = "trail.txt";
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        List<Integer> row = new ArrayList<>();
        for (char c : line.toCharArray()) {
          row.add(c - '0');
        }
        grid.add(row);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private int findPath(List<List<Integer>> grid, int row, int col) {
    
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[grid.size()][grid.get(0).size()];
    int gridRowLen = grid.size();
    int gridColLen = grid.get(0).size();
    int score = 0;

    queue.add(new int[] { row, col });
    visited[row][col] = true;
    

    while(!queue.isEmpty()) {

      int[] curTrail = queue.poll();
      int curRow = curTrail[0];
      int curCol = curTrail[1];

      for (int i = 0; i < directions.length; i++) {
        int newRow = curRow + directions[i][0];
        int newCol = curCol + directions[i][1];

        // check for bounds
        if (newRow < 0 || newCol < 0 || newRow >= gridRowLen || newCol >= gridColLen) {
          continue;
        }

        // Uncomment this for part 1

        // check if visited
        // if (visited[newRow][newCol]) {
        //   continue;
        // }
        
        int oldValue = grid.get(curRow).get(curCol);
        int newValue = grid.get(newRow).get(newCol);

        if (newValue - oldValue == 1) {
          // valid path
          visited[newRow][newCol] = true;
          queue.offer(new int[] {newRow, newCol});
          if (grid.get(newRow).get(newCol) == 9) {
            score++;
          }
        }
      }
    }
    return score;
  }

  private void findZero(List<List<Integer>> grid) {

    for (int row = 0; row < grid.size(); row++) {

      for (int col = 0; col < grid.size(); col++) {
        if (grid.get(row).get(col) == 0) {
          int score = findPath(grid, row, col);
          scoreCount.add(score);
        }
      }
    }
  }

  public static void main(String[] args) {

    PuzzleDay10 pd10 = new PuzzleDay10();
    System.out.println();

    System.out.println(pd10.grid);
    // For part 1 we keep a trick of visited cells. So even if a unique path leads to the same 9, we don't count it
    // This finds the zeroes and when it finds them calls BFS on them
    // pd10.findZero(pd10.grid);

    // // We should now have a score array
    // System.out.println(pd10.scoreCount);
    // int ans = 0;
    // for (int count : pd10.scoreCount) {
    //   ans += count;
    // }
    // System.out.println("The sum of all scores is " + ans);

    // For part two, we also include all distinct ways of reaching a 9 from starting cell. Essentially finding all
    // paths from the start to that same 9. For this, we don't track visited

    pd10.findZero(pd10.grid);

    // We should now have a score array
    System.out.println(pd10.scoreCount);
    int ans = 0;
    for (int count : pd10.scoreCount) {
      ans += count;
    }
    System.out.println("The sum of all scores is " + ans);
  }
}
