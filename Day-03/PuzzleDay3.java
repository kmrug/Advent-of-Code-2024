import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

public class PuzzleDay3 {

  List<String> mainList = new ArrayList<>();
  List<String> newMainList = new ArrayList<>();
  List<int[]> finalList = new ArrayList<>();
  List<int[]> newFinalList = new ArrayList<>();

  @SuppressWarnings("unused")
  private void extractMulPattern() {

    String filePath = "memory.txt";
    final String regex = "mul\\(\\d{1,3},\\d{1,3}\\)"; // Regex to match "mul(x, y)"

    Pattern pattern = Pattern.compile(regex);

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
          mainList.add(matcher.group());
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unused")
  private void extractNums(List<String> mulList) {

    String regex = "mul\\((\\d+),(\\d+)\\)";
    Pattern pattern = Pattern.compile(regex);

    for (String str : mulList) {

      Matcher matcher = pattern.matcher(str);

      if (matcher.find()) {
        int num1 = Integer.parseInt(matcher.group(1));
        int num2 = Integer.parseInt(matcher.group(2));

        finalList.add(new int[] { num1, num2 });

      } else {
        System.out.println("No match found!");
      }
    }
  }

  private int multiply(List<int[]> arr) {

    int result = 0;

    for (int[] nums : arr) {
      int num1 = nums[0];
      int num2 = nums[1];
      result += (num1 * num2);
    }

    return result;
  }

  private void extractMulPatternWithInstruction() {

    String filePath = "memory.txt";

    final String combinedRegex = "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)";

    Pattern pattern = Pattern.compile(combinedRegex);

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
          newMainList.add(matcher.group());
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void extractNewNums(List<String> mulList) {
    String regex = "mul\\((\\d+),(\\d+)\\)";
    Pattern pattern = Pattern.compile(regex);
    boolean doMul = true;

    for (String str : mulList) {

      if (str.equals("do()")) {
        doMul = true;
      } else if (str.equals("don't()")) {
        doMul = false;
      }

      if (doMul) {
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
          int num1 = Integer.parseInt(matcher.group(1));
          int num2 = Integer.parseInt(matcher.group(2));

          newFinalList.add(new int[] { num1, num2 });

        }
      }

    }
  }

  public static void main(String[] args) {

    PuzzleDay3 pd3 = new PuzzleDay3();

    // Part 1 of the puzzle 
    
    // pd3.extractMulPattern();
    // pd3.extractNums(pd3.mainList);
    // System.out.println("The multiplied number is " +
    // pd3.multiply(pd3.finalList));

    // Part 2 of the puzzle

    pd3.extractMulPatternWithInstruction();
    pd3.extractNewNums(pd3.newMainList);

    System.out.println("The multiplied number is " + pd3.multiply(pd3.newFinalList));

  }
}