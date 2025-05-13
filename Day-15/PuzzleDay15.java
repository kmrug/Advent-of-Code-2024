import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PuzzleDay15 {

  List<char[]> fishMap = new ArrayList<>();
  List<Character> robotDirection = new ArrayList<>();
  Map<Character, int[]> directions = new HashMap<>();
  int startX, startY;

  public PuzzleDay15() {

    boolean addDirections = false;
    String filePath = "fishMap.txt";

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

      for (int i = 0; i < fishMap.size(); i++) {
        for (int j = 0; j < fishMap.get(i).length; j++) {
          if (fishMap.get(i)[j] == '@') {
            startX = i;
            startY = j;
          }
        }
      }

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
      } else if (fishMap.get(startX)[startY] == 'O') {

        int tempX = startX, tempY = startY;
        startX -= x;
        startY -= y; // this is where '@' is right now
        Stack<int[]> stackBoxPos = new Stack<>();
        boolean hashBreak = false;

        stackBoxPos.push(new int[] { tempX, tempY }); // Pushing in first 'O' here

        if (c == '>') {

          while (tempY < colLen - 2) {
            tempY++;
            if (fishMap.get(tempX)[tempY] == 'O') {
              stackBoxPos.push(new int[] { tempX, tempY }); // next 'O' is added if available
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
              tempY++;
            }

            fishMap.get(tempX)[tempY] = '@';
            fishMap.get(startX)[startY] = '.';
            startX = tempX;
            startY = tempY;
          }

        } else if (c == '^') {
          while (tempX > 1) {
            tempX--;
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
              tempX++;
            }

            fishMap.get(tempX)[tempY] = '@';
            fishMap.get(startX)[startY] = '.';
            startX = tempX;
            startY = tempY;
          }
        }
      }
    }
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

    PuzzleDay15 pd15 = new PuzzleDay15();
    System.out.println();

    System.out.println("The original warehouse: ");
    for (int i = 0; i < pd15.fishMap.size(); i++) {
      System.out.println(Arrays.toString(pd15.fishMap.get(i)));
    }
    System.out.println();

    pd15.moveRobot(pd15.robotDirection, pd15.directions, pd15.fishMap, pd15.startX, pd15.startY);
    
    System.out.println("The updated warehouse: ");
    for (int i = 0; i < pd15.fishMap.size(); i++) {
      System.out.println(Arrays.toString(pd15.fishMap.get(i)));
    }
    System.out.println();

    System.out.println("The sum of all boxes' GPS coordinates is " + pd15.getGPS(pd15.fishMap));
    // for (Map.Entry<Character, int[]> entries : pd15.directions.entrySet()) {

    // System.out.println(entries.getKey() + " " +
    // Arrays.toString(entries.getValue()));
    // }

  }
}
