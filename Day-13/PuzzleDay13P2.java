import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PuzzleDay13P2 {

  List<List<long[]>> machines = new ArrayList<>();

  public PuzzleDay13P2() {

    String filePath = "C:\\Users\\kisha\\Desktop\\Advent Calendar\\Day 13\\prizes.txt";
    final Pattern patternA = Pattern.compile("Button\\sA:\\sX\\+([0-9]+),\\sY\\+([0-9]+)");
    final Pattern patternB = Pattern.compile("Button\\sB:\\sX\\+([0-9]+),\\sY\\+([0-9]+)");
    final Pattern patternPrize = Pattern.compile("Prize:\sX=([0-9]+),\sY=([0-9]+)");

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

      String line;
      List<long[]> currentLineCoordinates = new ArrayList<>();

      while ((line = br.readLine()) != null) {
        Matcher matcherA = patternA.matcher(line);
        Matcher matcherB = patternB.matcher(line);
        Matcher matcherP = patternPrize.matcher(line);

        // Extract Button A coordinates
        if (matcherA.find()) {
          long xA = Long.parseLong(matcherA.group(1));
          long yA = Long.parseLong(matcherA.group(2));
          currentLineCoordinates.add(new long[] { xA, yA });
        }

        // Extract Button B coordinates
        if (matcherB.find()) {
          long xB = Long.parseLong(matcherB.group(1));
          long yB = Long.parseLong(matcherB.group(2));
          currentLineCoordinates.add(new long[] { xB, yB });
        }

        // Extract Prize coordinates
        if (matcherP.find()) {
          long xP = Long.parseLong(matcherP.group(1));
          long yP = Long.parseLong(matcherP.group(2));
          currentLineCoordinates.add(new long[] { xP, yP });
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

  private long getTotalTokens(List<Long> list) {
    long total = 0;
    for (Long integer : list) {
      total += integer;
    }
    return total;
  }

  private List<Long> quadraticEqn(List<List<long[]>> machines) {

    List<Long> finalList = new ArrayList<>();
    final long correction = Long.parseLong("10000000000000");

    for (List<long[]> list : machines) {
      long buttonAX = list.get(0)[0];
      long buttonBX = list.get(1)[0];

      long prizeX = list.get(2)[0] + correction;

      long buttonAY = list.get(0)[1];
      long buttonBY = list.get(1)[1];

      long prizeY = list.get(2)[1] + correction;

      long denominator = Math.abs(buttonBX * buttonAY - buttonBY * buttonAX);

      long numeratorX = Math.abs(buttonBX * prizeY - buttonBY * prizeX);
      long resultX = numeratorX / denominator;
      long resultY = (prizeY - (buttonAY * resultX)) / buttonBY;

      if ((buttonAX * resultX) + (buttonBX * resultY) == prizeX
          && (buttonAY * resultX) + (buttonBY * resultY) == prizeY) {

        long pressA = resultX * 3;
        long pressB = resultY * 1;
        long total = pressA + pressB;
        finalList.add(total);
      }
    }
    return finalList;
  }

  public static void main(String[] args) {
    PuzzleDay13P2 pd13 = new PuzzleDay13P2();
    System.out.println();
    System.out.println();

    List<Long> finalList = pd13.quadraticEqn(pd13.machines);
    // System.out.println(finalList);
    long totalTokens = pd13.getTotalTokens(finalList);
    System.out.println("The minimum number of total tokens required are " + totalTokens);

  }
}
