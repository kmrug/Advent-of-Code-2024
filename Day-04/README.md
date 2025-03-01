# 🎄 Day 4: Ceres Search

## 📜 Problem Description

While searching for the Chief, you encounter a small Elf who needs help solving a **word search puzzle**. The goal is to locate the word **XMAS** hidden inside the grid.

The word search allows:

- **Horizontal** (left-to-right and right-to-left)
- **Vertical** (top-to-bottom and bottom-to-top)
- **Diagonal** (all four directions)
- **Overlapping words**

---

## Part 1: Count All Instances of XMAS

Your task is to **find all occurrences** of the word **XMAS** in the grid, regardless of orientation or overlapping.

### Example Input

`MMMSXXMASM`
`MSAMXMSMSA`
`AMXSXMAAMM`
`MSAMASMSMX`
`XMASAMXAMM`
`XXAMMXXAMA`
`SMSMSASXSS`
`SAXAMASAAA`
`MAMMMXMMMM`
`MXMXAXMASX`

### Example Output

XMAS occurs: `18 times`

### ✅ Allowed Directions:
- Horizontal: Left-to-Right, Right-to-Left
- Vertical: Top-to-Bottom, Bottom-to-Top
- Diagonal: All 4 possible directions
- Overlapping Matches

---

## Part 2: Find X-MAS Patterns

Flip the puzzle instructions and discover that you're supposed to find **X-MAS patterns**. An **X-MAS** is two `"MAS"` words arranged in the shape of an **X**.

A valid X-MAS looks like this:

M.S
.A.
M.S

Where each **MAS** can be written **forwards** or **backwards**.

---

### Example Input

`MMMSXXMASM`
`MSAMXMSMSA`
`AMXSXMAAMM`
`MSAMASMSMX`
`XMASAMXAMM`
`XXAMMXXAMA`
`SMSMSASXSS`
`SAXAMASAAA`
`MAMMMXMMMM`
`MXMXAXMASX`

### Example Output

X-MAS occurs: `9 times`

---

## 🔑 Key Observations
- In **Part 1**, every individual instance of **XMAS** needs to be counted.
- In **Part 2**, focus on finding diagonal intersections that form the **X shape**.
- **Overlapping matches** are allowed in both parts.

---

## 📂 Files in This Folder
- `solution.java` → Java implementation of the puzzle.
- `search.txt` → Word search grid input.
- `searchTest.txt` → Word search grid input.

---

## 🧠 Approach

### Part 1:
1. Loop through the grid.
2. Use **sliding window techniques** to extract substrings.
3. Check for matches in all **8 possible directions**.
4. Count each occurrence.

### Part 2:
1. Scan the grid to detect **MAS** patterns.
2. Use **diagonal checks** to form X-shaped pairs.
3. Count both **forward** and **backward** MAS matches.

---

## 📌 Actual Output Summary

| Part    | Result |
|---------|-------|
| Part 1  | 2414  |
| Part 2  | 1871  |

---

📝 **Author:** [Kishan Mrug](https://github.com/kmrug)