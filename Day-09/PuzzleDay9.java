import java.io.*;
import java.util.*;

public class PuzzleDay9 {

  char[] originalString;
  List<String> modList = new ArrayList<>();

  private void extractString() {
    String filePath = "input.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line = br.readLine();
      originalString = line.toCharArray();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private void formatDiskMap(List<String> modList) {

    String ID, freeSpace;
    int IDRepeat, freeSpaceRepeat, count = 0;

    for (int i = 0; i < originalString.length; i++) {

      if (i % 2 == 0) {
        ID = "" + count++;
        IDRepeat = originalString[i] - '0';
        for (int j = 0; j < IDRepeat; j++) {
          modList.add(ID);
        }
      } else {
        freeSpace = ".";
        freeSpaceRepeat = originalString[i] - '0';
        for (int j = 0; j < freeSpaceRepeat; j++) {
          modList.add(freeSpace);
        }
      }
    }
  }

  @SuppressWarnings("unused")
  private void fillSpace(List<String> modList) {

    int left = 0, right = modList.size() - 1;

    while (left < right) {
      while (right > left && modList.get(right).equals(".")) {
        right--;
      }
      if (modList.get(left).equals(".")) {

        String temp = modList.get(left);
        modList.set(left, modList.get(right));
        modList.set(right, temp);
        left++;
        right--;
      } else {
        left++;
      }

    }
  }

  private void fillSpaceTwo(List<String> modList) {

    int right = modList.size() - 1, emptySpots = 0, rightElemCount = 0,
        currentElement = 0;

    while (right > 0) {

      while (modList.get(right).equals(".")) {
        right--;
      }

      currentElement = Integer.parseInt(modList.get(right));
      rightElemCount = 0;
      while (right > 0 && modList.get(right).equals(String.valueOf(currentElement))) {
        right--;
        rightElemCount++;
        if (modList.get(right).equals(".")) {
          break;
        }
      }

      emptySpots = 0;
      for (int left = 0; left <= right; left++) {

        if (!modList.get(left).equals(".") && emptySpots == 0) {
          continue;
        }
        if (modList.get(left).equals(".")) {
          emptySpots++;
          if (emptySpots < rightElemCount) {
            continue;
          }

        }

        if (emptySpots >= rightElemCount) {
          int insertPosition = left - emptySpots + 1;
          int rightIndex = right + 1;

          for (int i = 0; i < rightElemCount; i++) {
            modList.set(insertPosition++, String.valueOf(currentElement));
            modList.set(rightIndex++, ".");
            // System.out.println(modList);
          }
          rightElemCount = 0;
          break;
        }
        emptySpots = 0;
      }
    }
  }

  private long checkSum(List<String> modList) {

    long sum = 0;
    for (int i = 0; i < modList.size(); i++) {
      if (!modList.get(i).equals(".")) {
        sum += (i * (Long.parseLong(modList.get(i))));
      }
    }
    return sum;
  }

  public static void main(String[] args) {

    long startNano = System.nanoTime();

    PuzzleDay9 pd9 = new PuzzleDay9();
    System.out.println();
    // System.out.println();

    // Extracts string from text file
    pd9.extractString();
    // System.out.println(pd9.originalString);

    // Get Inner Disk View, pass string list
    pd9.formatDiskMap(pd9.modList);
    // System.out.println(pd9.modList);
    // System.out.println();

    // Part 1

    // Now fill empty space
    // pd9.fillSpace(pd9.modList);
    // System.out.println(pd9.modList);

    // // // Do checksum calculation
    // long checkSum = pd9.checkSum(pd9.modList);
    // System.out.println("The checksum value is " + checkSum);

    // Part 2

    pd9.fillSpaceTwo(pd9.modList);
    // System.out.println(pd9.modList);
    long checkSum2 = pd9.checkSum(pd9.modList);
    System.out.println("The checksum value is " + checkSum2);

    long endNano = System.nanoTime();
    long durationNano = endNano - startNano;
    long durationMillis = durationNano / 1000000;

    System.out.println("Elapsed time in nanoseconds: " + durationNano);
    System.out.println("Elapsed time in milliseconds: " + durationMillis);

  }
}