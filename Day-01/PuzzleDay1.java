import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PuzzleDay1 {

  List<Integer> leftList = new ArrayList<>();
  List<Integer> rightList = new ArrayList<>();

  private void fileReader() {

    String filePath = "lists.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

      String line;
      while ((line = br.readLine()) != null) {

        String[] numbers = line.split("\\s+");
        if (numbers.length == 2) {
          leftList.add(Integer.parseInt(numbers[0]));
          rightList.add(Integer.parseInt(numbers[1]));
        } else {
          System.out.println("Invalid input format: " + line);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private int puzzleDay1(List<Integer> leftList, List<Integer> rightList) {

    Collections.sort(leftList);
    Collections.sort(rightList);
    int sum = 0;

    for (int i = 0; i < rightList.size(); i++) {
      sum += Math.abs(leftList.get(i) - rightList.get(i));
    }

    return sum;
  }

  private int partTwo(List<Integer> leftList, List<Integer> rightList) {

    Map<Integer, Integer> freqMap = new HashMap<>();
    int sum = 0;

    for (int i = 0; i < rightList.size(); i++) {
      
      if (!freqMap.containsKey(rightList.get(i))) {
        freqMap.put(rightList.get(i), 1);
      } else {
        freqMap.put(rightList.get(i), freqMap.get(rightList.get(i)) + 1);
      }
    }

    for (int i = 0; i < leftList.size(); i++) {
      sum += freqMap.getOrDefault(leftList.get(i), 0) * leftList.get(i);
    }
    return sum;
  }

  public static void main(String[] args) {

    PuzzleDay1 p = new PuzzleDay1();

    // Example Lists
    // int[] leftList = new int[] { 3, 4, 2, 1, 3, 3 };
    // int[] rightList = new int[] { 4, 3, 5, 3, 9, 3 };
    // System.out.println();
    // System.out.println("The distance apart is " + p.puzzleDay1(leftList,
    // rightList));

    System.out.println();
    p.fileReader();

    // Part 1 of the puzzle
    System.out.println("The distance apart is " + p.puzzleDay1(p.leftList, p.rightList));

    // Part 2 of the puzzle
    System.out.println("The frequency between lists is " + p.partTwo(p.leftList, p.rightList));
  }
}