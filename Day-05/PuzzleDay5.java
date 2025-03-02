import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleDay5 {

  Map<Integer, List<Integer>> map = new HashMap<>();
  List<List<Integer>> mainList = new ArrayList<>();
  List<List<Integer>> incorrectList = new ArrayList<>();
  List<int[]> correctedList = new ArrayList<>();

  private void extractMap() {

    String filePath = "print.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      boolean isAdjacencySection = true;

      while ((line = br.readLine()) != null) {

        if (line.isBlank()) { // Detect blank line to switch sections
          isAdjacencySection = false;
          continue;
        }

        if (isAdjacencySection) {
          String[] parts = line.split("\\|");
          int num1 = Integer.parseInt(parts[0]);
          int num2 = Integer.parseInt(parts[1]);
          if (!map.containsKey(num1)) {
            map.put(num1, new ArrayList<>());
            map.get(num1).add(num2);
          } else {
            map.get(num1).add(num2);
          }
        } else {
          // Parse lines in the comma-separated format
          String[] numbers = line.split(",");
          List<Integer> list = new ArrayList<>();
          for (String num : numbers) {
            list.add(Integer.parseInt(num.trim()));
          }
          mainList.add(list);
        }

      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private int countMiddle(Map<Integer, List<Integer>> map, List<List<Integer>> mainList) {

    int sum = 0;
    boolean wrongUpdate = false;

    for (List<Integer> list : mainList) {
      wrongUpdate = false;

      for (int i = 0; i < list.size() - 1; i++) {

        List<Integer> validVal = map.get(list.get(i));
        if (validVal == null) {
          // start reverse checking
          List<Integer> nextPage = map.get(list.get(i + 1));
          if (nextPage.contains(list.get(i))) {
            wrongUpdate = true;
            incorrectList.add(list);
            break;
          }
        } else if (validVal != null) {
          if (!validVal.contains(list.get(i + 1))) {
            wrongUpdate = true;
            incorrectList.add(list);
            break;
          }
        }

      }
      if (wrongUpdate == false) {
        int mid = list.get(list.size() / 2);
        sum += mid;
      }

    }
    return sum;
  }

  private List<Integer> checkErrors(Map<Integer, List<Integer>> map, List<Integer> fixList) {

    boolean wrongUpdate = false;

    for (int i = 0; i < fixList.size() - 1; i++) {

      List<Integer> validVal = map.get(fixList.get(i));
      if (validVal == null) {
        // start reverse checking
        List<Integer> nextPage = map.get(fixList.get(i + 1));
        if (nextPage.contains(fixList.get(i))) {
          Collections.swap(fixList, i + 1, i);
          wrongUpdate = true;
          break;
        }
      } else if (validVal != null) {
        if (!validVal.contains(fixList.get(i + 1))) {
          Collections.swap(fixList, i + 1, i);
          wrongUpdate = true;
          break;
        }
      }
    }

    if (wrongUpdate == true) {
      return checkErrors(map, fixList);
    }
    return fixList;
  }

  private int countInvalidLists(Map<Integer, List<Integer>> map, List<List<Integer>> incorrectList) {   

    List<List<Integer>> correctedList = new ArrayList<>();

    for (List<Integer> list : incorrectList) {
    
      List<Integer> fixedList = checkErrors(map, list);
      correctedList.add(fixedList);
    }
    
    // calculate sum of middle values now
    int sum = 0;
    for (List<Integer> list : correctedList) {
      sum += list.get(list.size() / 2);
    }

    return sum;
  }

  public static void main(String[] args) {
    PuzzleDay5 pd5 = new PuzzleDay5();

    System.out.println();
    pd5.extractMap();
    System.out.println(pd5.map);
    System.out.println(pd5.mainList);    
    System.out.println();
    System.out.println("The sum of all valid middle values is " + pd5.countMiddle(pd5.map, pd5.mainList));
    System.out.println();
    System.out.println("Incorrect list looks like " + pd5.incorrectList);
    System.out.println();
    System.out.println("The sum of middle values of newly corrected list is " + pd5.countInvalidLists(pd5.map, pd5.incorrectList));
  }
}
