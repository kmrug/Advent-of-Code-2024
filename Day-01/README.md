# 🎄 Day 1: Historian Hysteria

## 📜 Problem Description
The Chief Historian has gone missing, and the elves need to reconcile two lists of historically significant locations. However, the two lists do not match perfectly.

### **Part 1: Calculate the Total Distance**
The elves decide to match up numbers from the left and right lists to find how different they are. The total difference is found by:
- Sorting both lists in ascending order.
- Pairing numbers at the same index.
- Summing up the absolute differences.

### **Part 2: Calculate the Similarity Score**
The elves suspect that many numbers in the left list are just **badly written duplicates** of numbers in the right list. This time:
- Each number in the left list is multiplied by the number of times it appears in the right list.
- The sum of all these products gives the **similarity score**.

📖 **[Full problem description on Advent of Code](https://adventofcode.com/2024/day/1)**

---

## 📌 Example
### **Input**

`Left List: [3, 4, 2, 1, 3, 3]`

`Right List: [4, 3, 5, 3, 9, 3]`

### **Output**

`Total Distance: 11`

`Similarity Score: 31`

---

## 📂 Files in This Folder
- `solution.java` → Java implementation of the puzzle.
- `lists.txt` → The input file containing the lists.

---

📝 **Author:** [Kishan Mrug](https://github.com/kmrug)