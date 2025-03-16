import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PuzzleDay8 {

  Map<Character, List<List<Integer>>> freq = new HashMap<>();
  List<char[]> grid = new ArrayList<>();
  Set<String> set = new HashSet<>();

  Map<Character, List<List<Integer>>> freqPartTwo = new HashMap<>();
  // List<char[]> grid = new ArrayList<>();
  Set<String> setPartTwo = new HashSet<>();

  private void extract() {
    String filePath = "antenna.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;

      while ((line = br.readLine()) != null) {

        char[] parts = line.toCharArray();
        grid.add(parts);
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private void createMap(Map<Character, List<List<Integer>>> freq, List<char[]> grid) {

    for (int row = 0; row < grid.size(); row++) {
      for (int col = 0; col < grid.get(row).length; col++) {
        char getChar = grid.get(row)[col];
        if (Character.isDigit(getChar) || Character.isLowerCase(getChar) || Character.isUpperCase(getChar)) {
          freq.putIfAbsent(getChar, new ArrayList<>());
          freq.get(getChar).add(Arrays.asList(row, col));
        }
      }
    }
  }

  // Add this method to the class
  private boolean isValidPosition(int row, int col, int rowLen, int colLen) {
    return row >= 0 && col >= 0 && col < colLen && row < rowLen;
  }

  private int getAntinode(Map<Character, List<List<Integer>>> freq, List<char[]> grid, Set<String> set) {

    int colLen = grid.size();
    int rowLen = grid.get(0).length;
    int newRowA, newColA, newRowB, newColB;

    for (Map.Entry<Character, List<List<Integer>>> entries : freq.entrySet()) {
      // char getChar = entries.getKey();
      List<List<Integer>> list = entries.getValue();
      for (int i = 0; i < list.size(); i++) {
        int rowA = list.get(i).get(0);
        int colA = list.get(i).get(1);

        for (int j = i + 1; j < list.size(); j++) {

          int rowB = list.get(j).get(0);
          int colB = list.get(j).get(1);

          int distanceA = Math.abs(rowA - rowB);
          int distanceB = Math.abs(colA - colB);

          if (colA > colB) {
            newRowA = (-1 * distanceA) + rowA;
            newColA = distanceB + colA;
            newRowB = distanceA + rowB;
            newColB = (-1 * distanceB) + colB;
          } else { // this was newly updated
            newRowA = (-1 * distanceA) + rowA;
            newColA = (-1 * distanceB) + colA;
            newRowB = distanceA + rowB;
            newColB = distanceB + colB;
          }

          if (isValidPosition(newRowA, newColA, rowLen, colLen)) {
            if (grid.get(newRowA)[newColA] == '.') {
              grid.get(newRowA)[newColA] = '#';
            }
            set.add("(" + newRowA + ", " + newColA + ")");
          }
          if (isValidPosition(newRowB, newColB, rowLen, colLen)) {
            if (grid.get(newRowB)[newColB] == '.') {
              grid.get(newRowB)[newColB] = '#';
            }

            set.add("(" + newRowB + ", " + newColB + ")");
          }
        }
      }
    }
    return set.size();
  }

  private int moreAntinodes(Map<Character, List<List<Integer>>> freq, List<char[]> grid, Set<String> set) {
    int colLen = grid.size();
    int rowLen = grid.get(0).length;
    int newRowA, newColA, newRowB, newColB;
    for (Map.Entry<Character, List<List<Integer>>> entries : freq.entrySet()) {
      List<List<Integer>> list = entries.getValue();
      for (int i = 0; i < list.size(); i++) {
        int rowA = list.get(i).get(0);
        int colA = list.get(i).get(1);

        for (int j = i + 1; j < list.size(); j++) {

          int rowB = list.get(j).get(0);
          int colB = list.get(j).get(1);

          int distanceA = Math.abs(rowA - rowB);
          int distanceB = Math.abs(colA - colB);
          boolean flagUp = true, flagDown = true;
          int tempRowA = rowA, tempColA = colA, tempRowB = rowB, tempColB = colB;
          while (flagUp || flagDown) {

            if (tempColA > tempColB) {
              newRowA = (-1 * distanceA) + tempRowA;
              newColA = distanceB + tempColA;
              newRowB = distanceA + tempRowB;
              newColB = (-1 * distanceB) + tempColB;
            } else { // this was newly updated
              newRowA = (-1 * distanceA) + tempRowA;
              newColA = (-1 * distanceB) + tempColA;
              newRowB = distanceA + tempRowB;
              newColB = distanceB + tempColB;
            }

            if (isValidPosition(tempRowA, tempColA, rowLen, colLen)) {
              if (grid.get(tempRowA)[tempColA] == '.') {
                grid.get(tempRowA)[tempColA] = '#';
              }
              set.add("(" + tempRowA + ", " + tempColA + ")");
              tempRowA = newRowA;
              tempColA = newColA;
            } else {
              flagUp = false;
            }
            if (isValidPosition(tempRowB, tempColB, rowLen, colLen)) {
              if (grid.get(tempRowB)[tempColB] == '.') {
                grid.get(tempRowB)[tempColB] = '#';
              }
              set.add("(" + tempRowB + ", " + tempColB + ")");
              tempRowB = newRowB;
              tempColB = newColB;
            } else {
              flagDown = false;
            }
          }
        }
      }
    }
    return set.size();
  }

  public static void main(String[] args) {

    PuzzleDay8 pd8 = new PuzzleDay8();
    pd8.extract();
    System.out.println();
    for (char[] charArr : pd8.grid) {
      System.out.println(Arrays.toString(charArr));
    }
    pd8.createMap(pd8.freq, pd8.grid);

    int numAntinodes = pd8.getAntinode(pd8.freq, pd8.grid, pd8.set);
    System.out.println("The number of unique antinodes are " + numAntinodes);

    System.out.println("New antinodes: " + pd8.moreAntinodes(pd8.freq, pd8.grid, pd8.setPartTwo));

    for (char[] charArr : pd8.grid) {
      System.out.println(Arrays.toString(charArr));
    }
  }
}
