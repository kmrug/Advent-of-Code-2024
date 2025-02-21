import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PuzzleDay2 {

  List<List<Integer>> mainList = new ArrayList<>();

  private void fileReader() {

    String filePath = "reports.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] numbers = line.split("\\s");
        List<Integer> integerList = new ArrayList<>();
        for (String s : numbers) {
          integerList.add(Integer.parseInt(s)); // Convert and add each element
        }
        mainList.add(integerList);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean canBeSafe(List<Integer> newList) {

    for (int i = 0; i < newList.size(); i++) {
      List<Integer> modifiedList = new ArrayList<>(newList);
      modifiedList.remove(i); // Remove one element
      if (isSafe(modifiedList)) {
        return true;
      }
    }
    return false;
  }

  private boolean isSafe(List<Integer> list) {
    boolean increasing = true, decreasing = true;

    for (int i = 0; i < list.size() - 1; i++) {
      int diff = list.get(i + 1) - list.get(i);
      if (diff == 0 || Math.abs(diff) > 3)
        return false; // Invalid difference
      if (diff < 0)
        increasing = false; // Not increasing
      if (diff > 0)
        decreasing = false; // Not decreasing
    }

    return increasing || decreasing;
  }

  private int returnSafe(List<List<Integer>> mainList) {
    int safe = 0;

    for (List<Integer> list : mainList) {
      if (isSafe(list)) {
        // If the list is already safe
        safe++;
      } else if (canBeSafe(list)) {
        // If the list can be made safe by removing one element
        safe++;
      }
    }

    return safe;
  }

  public static void main(String[] args) {

    PuzzleDay2 pd2 = new PuzzleDay2();
    pd2.fileReader();
    System.out.println();
    System.out.println("The number of safe elements are " + pd2.returnSafe(pd2.mainList));
  }

}
