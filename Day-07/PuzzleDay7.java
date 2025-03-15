import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleDay7 {

  Map<Long, List<Long>> map = new HashMap<>();
  Map<Long, List<Long>> failedMap = new HashMap<>();

  private void extract() {
    String filePath = "num.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;

      while ((line = br.readLine()) != null) {

        String[] parts = line.split(":");
        long key = Long.parseLong(parts[0].trim());
        String[] values = parts[1].trim().split(" ");

        List<Long> valueList = new ArrayList<>();
        for (String value : values) {
          valueList.add(Long.valueOf(value));
        }

        map.put(key, valueList);
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private long processMap(Map<Long, List<Long>> map) {
    long sum = 0;
    for (Map.Entry<Long, List<Long>> entry : map.entrySet()) {
      long key = entry.getKey();
      List<Long> valueList = entry.getValue();
      List<Long> remainingList = valueList.subList(1, valueList.size());
      boolean exists = recursion(remainingList, key, valueList.get(0));
      if (exists) {
        sum += key;
      } else {
        failedMap.put(key, valueList);
      }
    }
    return sum;
  }

  private boolean recursion(List<Long> list, long ans, long result) {

    if (list.isEmpty()) {
      return result == ans;
    }
    Long first = list.get(0);
    List<Long> remainingList = list.subList(1, list.size());

    boolean addValid = recursion(remainingList, ans, result + first);

    boolean mulValid = recursion(remainingList, ans, result * first);

    return addValid || mulValid;
  }

  public static void main(String[] args) {

    PuzzleDay7 pd7 = new PuzzleDay7();
    pd7.extract();
    System.out.println();
    long mainSum = pd7.processMap(pd7.map);
    System.out.println("The sum is " + mainSum);
  }
}
