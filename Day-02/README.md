# 🎄 Day 2: Red-Nosed Reports

## 📜 Problem Description
The second search location The Historians visit is the **Red-Nosed Reindeer nuclear fusion/fission plant**. While they don’t find the Chief Historian, the engineers at the facility approach you with a request for help analyzing unusual reactor data.

The unusual data (your puzzle input) consists of many reports, one per line. Each report is a **list of numerical levels** separated by spaces. 

For example:
```
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9
```

Each report is considered **safe** if:
1. The levels are **strictly increasing** or **strictly decreasing**.
2. Any two adjacent levels differ by at least **1** and at most **3**.

### **Part 1: Counting Safe Reports**
For each report, check if it satisfies the safety conditions.  
Using the example data:
```
7 6 4 2 1 → Safe ✅ (decreasing, difference 1-2)
1 2 7 8 9 → Unsafe ❌ (7 - 2 = 5)
9 7 6 2 1 → Unsafe ❌ (6 - 2 = 4)
1 3 2 4 5 → Unsafe ❌ (switching increase/decrease)
8 6 4 4 1 → Unsafe ❌ (contains repeated 4)
1 3 6 7 9 → Safe ✅ (increasing, difference 1-3)
```

In this example, **2 reports** are safe.

### **Part 2: Problem Dampener**
The engineers introduce the **Problem Dampener**, which allows the system to **ignore a single bad level** in an otherwise safe report.  
Now, if removing a single level would make an unsafe report safe, it **counts as safe**.

Applying this rule to the previous example:
```
7 6 4 2 1 → Safe ✅ (no removal needed)
1 2 7 8 9 → Unsafe ❌ (no single removal fixes it)
9 7 6 2 1 → Unsafe ❌ (no single removal fixes it)
1 3 2 4 5 → Safe ✅ (remove 3)
8 6 4 4 1 → Safe ✅ (remove third 4)
1 3 6 7 9 → Safe ✅ (no removal needed)
```

Now, **4 reports** are safe.

📖 **[Full problem description on Advent of Code](https://adventofcode.com/2024/day/2)**

---

## 📂 Files in This Folder
- `PuzzleDay2.java` → Java implementation of the puzzle.
- `reports.txt` → The input file containing the reactor reports.
- `reportsTest.txt` → The input file containing the reactor reports.

---

📝 **Author:** [Kishan Mrug](https://github.com/kmrug)