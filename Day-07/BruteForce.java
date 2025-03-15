import java.io.*;
import java.util.*;

public class BruteForce {

  // Method to read the input from a file and return a list of test cases
  static List<TestCase> readInputFile(String filename) throws IOException {
    List<TestCase> testCases = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String line;

    while ((line = reader.readLine()) != null) {
      String[] parts = line.split(": ");
      long ans = Long.parseLong(parts[0].trim());
      List<Long> numbers = new ArrayList<>();
      String[] numParts = parts[1].split(" ");
      for (String num : numParts) {
        numbers.add(Long.valueOf(num.trim()));
      }
      testCases.add(new TestCase(ans, numbers));
    }

    reader.close();
    return testCases;
  }

  // Class to represent a single test case
  static class TestCase {
    long targetValue;
    List<Long> numbers;

    TestCase(long targetValue, List<Long> numbers) {
      this.targetValue = targetValue;
      this.numbers = numbers;
    }
  }

  // Method to determine if the target value can be formed using the operators
  public static boolean canBeMade(long ans, List<Long> numbers) {
    List<String> operators = Arrays.asList("+", "*", "||");
    int n = numbers.size();
    int numOperators = n - 1;
    List<String[]> operatorCombinations = generateOperatorCombinations(numOperators, operators);

    for (String[] ops : operatorCombinations) {
      long result = numbers.get(0);
      for (int i = 1; i < n; i++) {
        String operator = ops[i - 1];
        long current = numbers.get(i);

        switch (operator) {
          case "+" -> result += current;
          case "*" -> result *= current;
          case "||" -> result = Long.parseLong(String.valueOf(result) + String.valueOf(current));
          default -> {
          }
        }
      }
      if (result == ans) {
        return true;
      }
    }
    return false;
  }

  // Helper method to generate all combinations of operators
  private static List<String[]> generateOperatorCombinations(int n, List<String> operators) {
    List<String[]> result = new ArrayList<>();
    int totalCombinations = (int) Math.pow(operators.size(), n);

    for (int i = 0; i < totalCombinations; i++) {
      String[] combination = new String[n];
      int index = i;
      for (int j = 0; j < n; j++) {
        combination[j] = operators.get(index % operators.size());
        index /= operators.size();
      }
      result.add(combination);
    }

    return result;
  }

  // Main method to process the test cases
  public static void main(String[] args) {
    try {
      // File name
      String filename = "num.txt";
      List<TestCase> testCases = readInputFile(filename);

      // Process each test case
      long totalCalibrationResult = 0;
      for (TestCase testCase : testCases) {
        boolean result = canBeMade(testCase.targetValue, testCase.numbers);
        if (result) {
          totalCalibrationResult += testCase.targetValue;
        }
      }

      System.out.println("Total Calibration Result: " + totalCalibrationResult);

    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
