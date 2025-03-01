import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PuzzleDay4 {

  List<String> mainGrid = new ArrayList<>();
  final String word = "XMAS";
  final String wordReversed = "SAMX";
  final String wordPt2 = "MAS";
  final String wordReversedPt2 = "SAM";
  final int wordLen = word.length();

  private void extractGrid() {

    String filePath = "search.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

      String line;

      while ((line = br.readLine()) != null) {
        mainGrid.add(line);
      }
    } catch (IOException e) {
      System.err.println("IOException has occured: " + e);
    }
  }

  @SuppressWarnings("unused")
  private int countWord(List<String> stringArray) {

    int ans = 0, substringRowEnd = 0, substringColEnd = 0;

    for (int row = 0; row < stringArray.size(); row++) {

      for (int col = 0; col < stringArray.get(row).length(); col++) {

        if (stringArray.get(row).charAt(col) == 'X' || stringArray.get(row).charAt(col) == 'S') {

          // check right for XMAS / SAMX
          substringColEnd = col + wordLen - 1;
          if (substringColEnd < stringArray.get(row).length()) {
            String col2Substring = "" + stringArray.get(row).charAt(col) +
                stringArray.get(row).charAt(col + 1) + stringArray.get(row).charAt(col + 2) +
                stringArray.get(row).charAt(col + 3);
            if (col2Substring.equals(word) || col2Substring.equals(wordReversed)) {
              ans++;
            }
          }

          // check down for XMAS / SAMX

          substringRowEnd = row + wordLen - 1;
          if (substringRowEnd < stringArray.size()) {
            String col2Substring = "" + stringArray.get(row).charAt(col) +
                stringArray.get(row + 1).charAt(col) + stringArray.get(row + 2).charAt(col) +
                stringArray.get(row + 3).charAt(col);
            if (col2Substring.equals(word) || col2Substring.equals(wordReversed)) {
              ans++;
            }
          }

          // check diagonally left for XMAS / SAMX

          substringColEnd = col - wordLen + 1;
          substringRowEnd = row + wordLen - 1;
          if (substringColEnd >= 0 && substringRowEnd < stringArray.size()) {
            String col2Substring = "" + stringArray.get(row).charAt(col) +
                stringArray.get(row + 1).charAt(col - 1)
                + stringArray.get(row + 2).charAt(col - 2) +
                stringArray.get(row + 3).charAt(col - 3);
            if (col2Substring.equals(word) || col2Substring.equals(wordReversed)) {
              ans++;
            }
          }

          // check diagonally right for XMAS / SAMX

          substringColEnd = col + wordLen - 1;
          substringRowEnd = row + wordLen - 1;
          if (substringColEnd < stringArray.size() && substringRowEnd < stringArray.size()) {
            String col2Substring = "" + stringArray.get(row).charAt(col) + stringArray.get(row + 1).charAt(col + 1)
                + stringArray.get(row + 2).charAt(col + 2) +
                stringArray.get(row + 3).charAt(col + 3);
            if (col2Substring.equals(word) || col2Substring.equals(wordReversed)) {
              ans++;
            }
          }
        }
      }
    }
    return ans;
  }

  private int countPartTwo(List<String> stringArray) {

    int ans = 0, substringRowEnd = 0, substringColEnd = 0;
    final int maxMove = 2;

    for (int row = 0; row < stringArray.size(); row++) {

      for (int col = 0; col < stringArray.get(row).length(); col++) {

        if (stringArray.get(row).charAt(col) == 'M' || stringArray.get(row).charAt(col) == 'S') {

          substringColEnd = col + maxMove;
          substringRowEnd = row + maxMove;

          if (substringRowEnd < stringArray.size() && substringColEnd < stringArray.get(row).length()) {
            String col2Substring = "" + stringArray.get(row).charAt(col) +
                stringArray.get(row + 1).charAt(col + 1) +
                stringArray.get(row + 2).charAt(col + 2);
            if (col2Substring.equals(wordPt2) || col2Substring.equals(wordReversedPt2)) {
              String secondCheck = "" + stringArray.get(row).charAt(col + 2) +
                  stringArray.get(row + 1).charAt(col + 1) +
                  stringArray.get(row + 2).charAt(col);
              if (secondCheck.equals(wordPt2) || secondCheck.equals(wordReversedPt2)) {
                ans++;
              }

            }
          }

        }
      }

    }

    return ans;
  }

  public static void main(String[] args) {

    PuzzleDay4 pd4 = new PuzzleDay4();
    pd4.extractGrid();
    System.out.println();
    System.out.println();

    // Part 1
    // System.out.println("The count is " + pd4.countWord(pd4.mainGrid));

    // Part 2
    System.out.println("The count is " + pd4.countPartTwo(pd4.mainGrid));
  }
}
