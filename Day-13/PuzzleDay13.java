import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PuzzleDay13 {

  List<List<int[]>> machines = new ArrayList<>();

  public PuzzleDay13() {

    String filePath = "C:\\Users\\kisha\\Desktop\\Advent Calendar\\Day 13\\prizes.txt";
    final Pattern patternA = Pattern.compile("Button\\sA:\\sX\\+([0-9]+),\\sY\\+([0-9]+)");
    final Pattern patternB = Pattern.compile("Button\\sB:\\sX\\+([0-9]+),\\sY\\+([0-9]+)");
    final Pattern patternPrize = Pattern.compile("Prize:\sX=([0-9]+),\sY=([0-9]+)");

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

      String line;
      List<int[]> currentLineCoordinates = new ArrayList<>();

      while ((line = br.readLine()) != null) {
        Matcher matcherA = patternA.matcher(line);
        Matcher matcherB = patternB.matcher(line);
        Matcher matcherP = patternPrize.matcher(line);

        // Extract Button A coordinates
        if (matcherA.find()) {
          int xA = Integer.parseInt(matcherA.group(1));
          int yA = Integer.parseInt(matcherA.group(2));
          currentLineCoordinates.add(new int[] { xA, yA });
        }

        // Extract Button B coordinates
        if (matcherB.find()) {
          int xB = Integer.parseInt(matcherB.group(1));
          int yB = Integer.parseInt(matcherB.group(2));
          currentLineCoordinates.add(new int[] { xB, yB });
        }

        // Extract Prize coordinates
        if (matcherP.find()) {
          int xP = Integer.parseInt(matcherP.group(1));
          int yP = Integer.parseInt(matcherP.group(2));
          currentLineCoordinates.add(new int[] { xP, yP });
        }

        // If we have all three (Button A, Button B, Prize) in the current block, add it
        // to the main list
        if (currentLineCoordinates.size() == 3) {
          machines.add(new ArrayList<>(currentLineCoordinates));
          currentLineCoordinates.clear(); // Clear the current line coordinates for the next block
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // FAILED BRUTE FORCE METHOD

  // If A has a bigger shift for X, we apply this function
  private List<int[]> findMinX(int buttonAX, int buttonBX, int prizeX) {

    List<int[]> listX = new ArrayList<>();

    int left = 0, right = 100; // 100 is the max button pushes

    while (left <= 100 && right > 0) {

      int calculation = (left * buttonAX) + (right * buttonBX);

      if (calculation == prizeX) {

        listX.add(new int[] { left, right });
        left++;
      } else if (calculation < prizeX) {
        left++;
      } else {
        right--;
      }
    }
    return listX;
  }

  private List<int[]> findMinY(int buttonAY, int buttonBY, int prizeY) {

    List<int[]> listY = new ArrayList<>();

    int left = 0, right = 100; // 100 is the max button pushes

    while (left <= 100 && right > 0) {

      int calculation = (left * buttonAY) + (right * buttonBY);
      if (calculation == prizeY) {
        listY.add(new int[] { left, right });
        left++;
      } else if (calculation < prizeY) {
        left++;
      } else {
        right--;
      }
    }
    return listY;
  }

  private List<int[]> listIntersect(List<int[]> listX, List<int[]> listY) {

    List<int[]> intersectedList = new ArrayList<>();

    for (int[] arr : listX) {
      // Check if any array in listY matches arr using Arrays.equals()
      for (int i = 0; i < listY.size(); i++) {
        if (Arrays.equals(arr, listY.get(i))) {
          intersectedList.add(arr);
          break; // Break early if found, no need to check further elements in listY
        }
      }
    }
    return intersectedList;
  }

  @SuppressWarnings("unused")
  private List<Long> processMachines(List<List<int[]>> machines) {

    List<Long> finaList = new ArrayList<>();

    for (List<int[]> list : machines) {

      long minNum = Integer.MAX_VALUE;
      int buttonAX = list.get(0)[0];
      int buttonBX = list.get(1)[0];

      int prizeX = list.get(2)[0];

      int buttonAY = list.get(0)[1];
      int buttonBY = list.get(1)[1];

      int prizeY = list.get(2)[1];

      List<int[]> listX = findMinX(buttonAX, buttonBX, prizeX);
      List<int[]> listY = findMinY(buttonAY, buttonBY, prizeY);
      List<int[]> intersectList = listIntersect(listX, listY);

      if (intersectList.size() == 0) { // not possible to win prize in machine
        continue;
      } else {

        for (int[] arr : intersectList) {
          long pressA = arr[0] * 3;
          long pressB = arr[1] * 1;
          long total = pressA + pressB;
          minNum = Math.min(minNum, total);
        }
      }
      finaList.add(minNum);
    }
    return finaList;
  }

  private long getTotalTokens(List<Long> list) {
    long total = 0;
    for (Long integer : list) {
      total += integer;
    }
    return total;
  }

  // CORRECT QUADRATIC EQUATION SOLVING

  private List<Long> quadraticEqn(List<List<int[]>> machines) {

    List<Long> finalList = new ArrayList<>();

    for (List<int[]> list : machines) {
      int buttonAX = list.get(0)[0];
      int buttonBX = list.get(1)[0];

      int prizeX = list.get(2)[0];

      int buttonAY = list.get(0)[1];
      int buttonBY = list.get(1)[1];

      int prizeY = list.get(2)[1];

      int denominator = Math.abs(buttonBX * buttonAY - buttonBY * buttonAX);

      double resultX = Math.abs((double) (buttonBX * prizeY - buttonBY * prizeX)) / denominator;
      double resultY = (prizeY - buttonAY * resultX) / buttonBY;

      long finalResultX = Math.round(resultX);
      long finalResultY = Math.round(resultY);

      if ((buttonAX * finalResultX) + (buttonBX * finalResultY) == prizeX
          && (buttonAY * finalResultX) + (buttonBY * finalResultY) == prizeY) {

        long pressA = finalResultX * 3;
        long pressB = finalResultY * 1;
        long total = pressA + pressB;
        finalList.add(total);
      }
    }
    return finalList;
  }

  public static void main(String[] args) {
    PuzzleDay13 pd13 = new PuzzleDay13();
    System.out.println();
    System.out.println();

    List<Long> finalList = pd13.quadraticEqn(pd13.machines);
    // System.out.println(finalList);
    long totalTokens = pd13.getTotalTokens(finalList);
    System.out.println("The minimum number of total tokens required are " + totalTokens);

  }
}
