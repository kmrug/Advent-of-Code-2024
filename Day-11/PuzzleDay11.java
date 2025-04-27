import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleDay11 {

  List<String> originalString;

  public PuzzleDay11() {

    String filePath = "stone.txt";
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line = br.readLine();
      originalString = Arrays.asList(line.split(" "));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private List<String> modifyList(List<String> stringArr) {
    List<String> modifiedList = new ArrayList<>();
    int count = 0;
    for (String string : stringArr) {
      if (string.equals("0")) {
        modifiedList.add("1");
      }

      else if (string.length() % 2 == 0) {
        String left = string.substring(0, string.length() / 2);
        String right = string.substring(string.length() / 2);
        // Remove leading and trailing zeroes (if applicable)
        String trimmedLeft = left.replaceFirst("^0+", "");
        String trimmedRight = right.replaceFirst("^0+", "");

        // Add back "0" if the result is empty
        if (trimmedLeft.isEmpty())
          trimmedLeft = "0";
        if (trimmedRight.isEmpty())
          trimmedRight = "0";

        modifiedList.add(trimmedLeft);
        modifiedList.add(trimmedRight);
        // count += 2;
      } else {
        long num = Long.parseLong(string) * 2024;
        modifiedList.add(String.valueOf(num));
      }
    }
    // System.out.println("the c was " + count);
    return modifiedList;
  }

  private List<String> toUpdateArray(List<String> stringArr) {

    List<String> finaList = new ArrayList<>(stringArr);

    for (int i = 0; i < 45; i++) {
      List<String> temp = modifyList(finaList);
      finaList = temp;
    }

    return finaList;
  }

  private final Map<String, Long> memo = new HashMap<>();

  private long handleNum(long number, int limit, int count) {
    // Create a unique key for the current state
    String key = number + "," + limit + "," + count;

    // Check if result is already computed
    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    // Base case
    if (limit == 0) {
      return count + 1;
    }

    // Handle number = 0 case
    if (number == 0) {
      long result = handleNum(1, limit - 1, count);
      memo.put(key, result);
      return result;
    }

    // Handle even-length numbers
    if (String.valueOf(number).length() % 2 == 0) {
      String numStr = String.valueOf(number);
      String left = numStr.substring(0, numStr.length() / 2);
      String right = numStr.substring(numStr.length() / 2);

      // Remove leading and trailing zeroes
      String trimmedLeft = left.replaceFirst("^0+", "");
      String trimmedRight = right.replaceFirst("^0+", "");

      // Add back "0" if the result is empty
      if (trimmedLeft.isEmpty()) {
        trimmedLeft = "0";
      }
      if (trimmedRight.isEmpty()) {
        trimmedRight = "0";
      }

      long result = handleNum(Long.parseLong(trimmedLeft), limit - 1, count) + handleNum(Long.parseLong(trimmedRight), limit - 1, count);

      // Store the result in the memoization map
      memo.put(key, result);
      return result;
    } else {
      // Handle odd-length numbers
      long num = number * 2024;
      long result = handleNum(num, limit - 1, count);

      // Store the result in the memoization map
      memo.put(key, result);
      return result;
    }
  }

  public static void main(String[] args) {

    PuzzleDay11 pd11 = new PuzzleDay11();
    System.out.println();

    // System.out.println(pd11.originalString);
    // List<String> finalArr = pd11.toUpdateArray(pd11.originalString);
    // System.out.println(finalArr);
    // long stones = finalArr.size();
    // System.out.println("The number of stones created are " + stones);

    // System.out.println(pd11.handleNum(17, 6, 0));
    // pd11.toUpdateArray(pd11.originalString);
    long stones = 0;

    for (String s : pd11.originalString) {
    long num = Long.parseLong(s);
    long value = pd11.handleNum(num, 75, 0);
    stones += value;
    }

    System.out.println("The number of stones created are " + stones);
  }
}
