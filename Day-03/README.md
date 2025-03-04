# 🎄 Day 3: Mull It Over

## 📜 Problem Description
The North Pole Toboggan Rental Shop is having **computer issues** again! The shopkeeper has no idea if they have any **Chief Historians in stock**, so The Historians go to check the warehouse while you investigate the **corrupted memory** in their computer.

The **program's memory** (your puzzle input) contains **jumbled-up instructions** that were originally meant to perform **multiplication operations** using `mul(X,Y)`. However, due to corruption, **invalid characters** have appeared, making many of the instructions unreadable.

Your task is to **extract valid multiplication instructions** and sum up their results.

### **Part 1: Extract and Sum Valid Multiplications**
You need to **scan the corrupted memory** and extract **only valid** multiplication operations in the format:

`mul(X,Y)`

where `X` and `Y` are **1-3 digit numbers**.

✅ **Valid Examples:**

`mul(44,46) → 44 * 46 = 2024`

`mul(123,4) → 123 * 4 = 492`

❌ **Invalid Examples (Ignored):**

`mul(4*, mul(6,9! → Invalid characters included ?(12,34) → Incorrect syntax mul ( 2 , 4 ) → Spaces not allowed`

#### **Example Input (Corrupted Memory)**

`xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))`

#### **Example Output**

Valid Instructions: 
`mul(2,4) → 2 * 4 = 8`

`mul(5,5) → 5 * 5 = 25`

`mul(11,8) → 11 * 8 = 88`

`mul(8,5) → 8 * 5 = 40`

Sum of Results:
`161`

---

### **Part 2: Handling Conditional Statements**
While scanning, you realize the program contains **conditional statements**:
- `do()` → **Enables future `mul()` operations**.
- `don't()` → **Disables future `mul()` operations**.

By default, all `mul()` instructions are **enabled**, but the most recent `do()` or `don't()` instruction determines whether future multiplications are processed.

#### **Example Input (Corrupted Memory with Conditions)**

`xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))`

#### **Example Output**

Valid Instructions:

`mul(2,4) → 2 * 4 = 8 ✅ (Enabled)`

`mul(5,5) → 5 * 5 = 25 ❌ (Disabled by don't())`

`mul(11,8) → 11 * 8 = 88 ❌ (Disabled by don't())`

`mul(8,5) → 8 * 5 = 40 ✅ (Re-enabled by do())`

Sum of Results with Conditions:
`48`


📖 **[Full problem description on Advent of Code](https://adventofcode.com/2024/day/3)**

---

## 📂 Files in This Folder
- `PuzzleDay3.java` → Java implementation of the puzzle.
- `memory.txt` → The input file containing the corrupted memory.
- `memory2.txt` → The input file containing the corrupted memory.
- `memory3.txt` → The input file containing the corrupted memory.

---

📝 **Author:** [Kishan Mrug](https://github.com/kmrug)