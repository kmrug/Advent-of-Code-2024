import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class PuzzleDay15P2 {

  List<char[]> fishMap = new ArrayList<>();
  List<char[]> fishMapNew = new ArrayList<>();
  List<Character> robotDirection = new ArrayList<>();
  Map<Character, int[]> directions = new HashMap<>();
  int startX, startY;

  public PuzzleDay15P2() {

    boolean addDirections = false;
    String filePath = "fishTestSmall2.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

      String line;
      while ((line = br.readLine()) != null) {

        if (line.isEmpty()) {
          addDirections = true;
        }

        if (!addDirections) {
          char[] fish = line.toCharArray();
          fishMap.add(fish);
        } else {
          for (char c : line.toCharArray()) {
            robotDirection.add(c);
          }
        }
      }

      // Modifies the warehouse map for Part 2

      for (int i = 0; i < fishMap.size(); i++) {
        char[] row = new char[fishMap.get(0).length * 2]; // Create a new row with 20 columns
        fishMapNew.add(row); // Add the row to fishMapNew
      }

      for (int i = 0; i < fishMap.size(); i++) {
        int counter = 0;
        for (int j = 0; j < fishMap.get(i).length; j++) {
          if (fishMap.get(i)[j] == '#') {
            fishMapNew.get(i)[counter++] = '#';
            fishMapNew.get(i)[counter++] = '#';
          } else if (fishMap.get(i)[j] == 'O') {
            fishMapNew.get(i)[counter++] = '[';
            fishMapNew.get(i)[counter++] = ']';
          } else if (fishMap.get(i)[j] == '.') {
            fishMapNew.get(i)[counter++] = '.';
            fishMapNew.get(i)[counter++] = '.';
          } else if (fishMap.get(i)[j] == '@') {
            fishMapNew.get(i)[counter++] = '@';
            fishMapNew.get(i)[counter++] = '.';
          }
        }
      }

      for (int i = 0; i < fishMapNew.size(); i++) {
        for (int j = 0; j < fishMapNew.get(i).length; j++) {
          if (fishMapNew.get(i)[j] == '@') {
            startX = i;
            startY = j;
          }
        }
      }

      // Add directions
      directions.put('>', new int[] { 0, 1 });
      directions.put('v', new int[] { 1, 0 });
      directions.put('<', new int[] { 0, -1 });
      directions.put('^', new int[] { -1, 0 });
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private void moveRobot(List<Character> robotDirection, Map<Character, int[]> directions, List<char[]> fishMap,
      int startX, int startY) {

    int rowLen = fishMap.size();
    int colLen = fishMap.get(0).length;
    for (char c : robotDirection) {
      int[] getDirection = directions.get(c);
      int x = getDirection[0], y = getDirection[1];
      startX += x;
      startY += y;

      if (fishMap.get(startX)[startY] == '#') {
        startX -= x;
        startY -= y;
        continue;
      } else if (fishMap.get(startX)[startY] == '.') {
        fishMap.get(startX)[startY] = '@';
        fishMap.get(startX - x)[startY - y] = '.';
        continue;
      } else if (fishMap.get(startX)[startY] == '[' || fishMap.get(startX)[startY] == ']') {

        int tempX = startX, tempY = startY;
        startX -= x;
        startY -= y; // this is where '@' is right now
        Stack<int[]> stackBoxPos = new Stack<>();
        boolean hashBreak = false;

        stackBoxPos.push(new int[] { tempX, tempY }); // Pushing in first 'O' here
        // stackBoxPos.push(new int[] { tempX, tempY });
        if (c == '>') {

          while (tempY < colLen - 2) {
            tempY++;
            if (fishMap.get(tempX)[tempY] == '[') {
              stackBoxPos.push(new int[] { tempX, tempY });
            } else if (fishMap.get(tempX)[tempY] == ']') {
              stackBoxPos.push(new int[] { tempX, tempY });
            } else if (fishMap.get(tempX)[tempY] == '.') {
              break;
            } else if (fishMap.get(tempX)[tempY] == '#') {
              hashBreak = true;
              break;
            }
          }
          int value = 0;
          if (!hashBreak) {
            while (!stackBoxPos.isEmpty()) {
              int[] boxPos = stackBoxPos.pop();
              fishMap.get(boxPos[0])[boxPos[1]] = '.';
              if (value == 0) {
                fishMap.get(tempX)[tempY] = ']';
                value = 1;
              } else {
                fishMap.get(tempX)[tempY] = '[';
                value = 0;
              }
              tempY--;
            }
            fishMap.get(tempX)[tempY] = '@';
            fishMap.get(startX)[startY] = '.';
            startX = tempX;
            startY = tempY;
          }

        } else if (c == 'v') {

          while (tempX < rowLen - 2) {
            tempX++;
            if (fishMap.get(tempX)[tempY] == 'O') {
              stackBoxPos.push(new int[] { tempX, tempY }); // next 'O'
            } else if (fishMap.get(tempX)[tempY] == '.') {
              break;
            } else if (fishMap.get(tempX)[tempY] == '#') {
              hashBreak = true;
              break;
            }
          }
          if (!hashBreak) {
            while (!stackBoxPos.isEmpty()) {
              int[] boxPos = stackBoxPos.pop();
              fishMap.get(boxPos[0])[boxPos[1]] = '.';
              fishMap.get(tempX)[tempY] = 'O';
              tempX--;
            }

            fishMap.get(tempX)[tempY] = '@';
            fishMap.get(startX)[startY] = '.';
            startX = tempX;
            startY = tempY;
          }

        } else if (c == '<') {
          while (tempY > 1) {
            tempY--;
            if (fishMap.get(tempX)[tempY] == ']') {
              stackBoxPos.push(new int[] { tempX, tempY }); // next 'O'
            } else if (fishMap.get(tempX)[tempY] == '[') {
              stackBoxPos.push(new int[] { tempX, tempY });
            } else if (fishMap.get(tempX)[tempY] == '.') {
              break;
            } else if (fishMap.get(tempX)[tempY] == '#') {
              hashBreak = true;
              break;
            }
          }
          int value = 0;
          if (!hashBreak) {
            while (!stackBoxPos.isEmpty()) {
              int[] boxPos = stackBoxPos.pop();
              fishMap.get(boxPos[0])[boxPos[1]] = '.';
              if (value == 0) {
                fishMap.get(tempX)[tempY] = '[';
                value = 1;
              } else {
                fishMap.get(tempX)[tempY] = ']';
                value = 0;
              }

              tempY++;
            }

            fishMap.get(tempX)[tempY] = '@';
            fishMap.get(startX)[startY] = '.';
            startX = tempX;
            startY = tempY;
          }

        } else if (c == '^') {

          if (fishMap.get(tempX)[tempY] == '[') {
            upBFS(fishMap, tempX, tempY + 1, tempX, tempY);
          } else {
            upBFS(fishMap, tempX, tempY, tempX, tempY - 1);
          }

          // while (tempX > 1) {
          // tempX--;
          // if (fishMap.get(tempX)[tempY] == ']') {
          // stackBoxPos.push(new int[] { tempX, tempY }); // next 'O'
          // } else if (fishMap.get(tempX)[tempY] == '.' && fishMap.get(tempX)[tempY - 1]
          // == '.') {
          // break;
          // } else if (fishMap.get(tempX)[tempY] == '#' || fishMap.get(tempX)[tempY - 1]
          // == '#') {
          // hashBreak = true;
          // break;
          // }
          // }

          // if (!hashBreak) {
          // while (!stackBoxPos.isEmpty()) {
          // stackBoxPos.pop();
          // fishMap.get(tempX)[tempY] = ']';
          // fishMap.get(tempX)[tempY - 1] = '[';
          // tempX++;
          // }

          // fishMap.get(tempX)[tempY] = '@';
          // fishMap.get(startX)[startY] = '.';
          // fishMap.get(startX - 1)[startY - 1] = '.';
          // startX = tempX + 1;
          // startY = tempY;
          // }
        }
      }
    }
  }

  private void upBFS(List<char[]> fishMap, int rowR, int colR, int rowL, int colL) {

    List<char[]> fishMapDuplicate = new ArrayList<>();

    for (char[] row : fishMap) {
      // Create a new char[] with the same length as the original row
      char[] newRow = Arrays.copyOf(row, row.length);
      // Add the new row to the duplicate list
      fishMapDuplicate.add(newRow);
    }

    Queue<int[]> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>(); // Track visited positions
    int upX = -1, upY = 0;
    queue.offer(new int[] { rowL, colL, rowR, colR });
    visited.add(rowL + "," + colL + "," + rowR + "," + colR);

    while (!queue.isEmpty()) {
      int[] pair = queue.poll();

      int newRowL = pair[0] + upX;
      int newColL = pair[1] + upY;
      int newRowR = pair[2] + upX;
      int newColR = pair[3] + upY;
      System.out.println();
      for (int i = 0; i < fishMapDuplicate.size(); i++) {
        System.out.println(Arrays.toString(fishMapDuplicate.get(i)));
      }

      if (newRowL < 0 || newRowR < 0 || newColL < 0 || newColR < 0
          || newRowL >= fishMapDuplicate.size() || newRowR >= fishMapDuplicate.size()
          || newColL >= fishMapDuplicate.get(0).length || newColR >= fishMapDuplicate.get(0).length) {
        continue; // Skip out-of-bounds moves
      }
      if (fishMapDuplicate.get(newRowL)[newColL] == '.' && fishMapDuplicate.get(newRowR)[newColR] == '.') {
        // Clear the old positions of [ and ]
        fishMapDuplicate.get(pair[0])[pair[1]] = '.';
        fishMapDuplicate.get(pair[2])[pair[3]] = '.';

        // Move both [ and ] to the new positions
        fishMapDuplicate.get(newRowL)[newColL] = '[';
        fishMapDuplicate.get(newRowR)[newColR] = ']';

        // Print the grid for debugging
        System.out.println();
        for (char[] row : fishMapDuplicate) {
          System.out.println(Arrays.toString(row));
        }

        // Create a unique key for the new positions
        String newKey = newRowL + "," + newColL + "," + newRowR + "," + newColR;
        if (!visited.contains(newKey)) {
          // Add the new pair to the queue for further processing
          queue.offer(new int[] { newRowL, newColL, newRowR, newColR });
          visited.add(newKey);
        }
      }

    }
    System.out.println("\n\nlast");
    for (int i = 0; i < fishMapDuplicate.size(); i++) {
      System.out.println(Arrays.toString(fishMapDuplicate.get(i)));
    }

  }

  private void callDown(List<char[]> fishMap) {

  }

  private int getGPS(List<char[]> fishMap) {

    int sum = 0;
    for (int row = 0; row < fishMap.size(); row++) {
      for (int col = 0; col < fishMap.get(row).length; col++) {
        if (fishMap.get(row)[col] == 'O') {
          sum += ((100 * row) + col);
        }
      }
    }
    return sum;
  }

  public static void main(String[] args) {

    PuzzleDay15P2 pd15 = new PuzzleDay15P2();
    System.out.println();

    System.out.println("The original warehouse: ");
    for (int i = 0; i < pd15.fishMapNew.size(); i++) {
      System.out.println(Arrays.toString(pd15.fishMapNew.get(i)));
    }

    pd15.moveRobot(pd15.robotDirection, pd15.directions, pd15.fishMapNew, pd15.startX, pd15.startY);

    System.out.println("The updated warehouse: ");
    for (int i = 0; i < pd15.fishMapNew.size(); i++) {
      System.out.println(Arrays.toString(pd15.fishMapNew.get(i)));
    }
    // System.out.println();

    // System.out.println("The sum of all boxes' GPS coordinates is " +
    // pd15.getGPS(pd15.fishMap));

  }
}
