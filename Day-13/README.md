# 🎄 Day 13: Claw Contraption  

## 📜 Problem Description  

At a **tropical island resort arcade**, you find **claw machines** operated by **two buttons**, A and B.  
Each button:
- Moves the claw a specific amount along the **X** and **Y** axes.
- Costs a different number of **tokens** to press:
  - **A** costs **3 tokens**.
  - **B** costs **1 token**.

✅ **Goal:**  
- Position the claw **exactly** at the prize's location.
- **Minimize** the number of **tokens spent**.
- Win as **many prizes as possible**.

---

## Part 1: Winning Prizes with Normal Coordinates  

For each claw machine:
- Determine if it's possible to reach the prize.
- Find the minimum tokens needed to win.
- **Sum tokens across all prizes won**.

### Example Input  

```
Button A: X+94, Y+34
Button B: X+22, Y+67
Prize: X=8400, Y=5400

Button A: X+26, Y+66
Button B: X+67, Y+21
Prize: X=12748, Y=12176

Button A: X+17, Y+86
Button B: X+84, Y+37
Prize: X=7870, Y=6450

Button A: X+69, Y+23
Button B: X+27, Y+71
Prize: X=18641, Y=10279
```

✅ **Example Solution Highlights:**
- Machine 1 → Win (tokens spent: 280)
- Machine 2 → Cannot win
- Machine 3 → Win (tokens spent: 200)
- Machine 4 → Cannot win

Total tokens for winning possible prizes = **480**

---

## Part 2: Winning Prizes with Huge Coordinates  

After beginning, you discover:
- **All prize coordinates** are **increased by 10,000,000,000,000** on both axes.

This makes reaching the prize **much more complex**:
- You are allowed **many more button presses** than before.
- Advanced techniques like **Extended Euclidean Algorithm** are needed.

✅ **Updated Example Prize Locations:**

`Prize X = 10000000008400, Y = 10000000005400 ...`

✅ **Example Solution Highlights:**
- Machine 1 → Cannot win
- Machine 2 → Win
- Machine 3 → Cannot win
- Machine 4 → Win

---

## 🔑 Key Observations  

- The task is equivalent to solving a **Diophantine equation**:

a * (A button moves) + b * (B button moves) = (target position)

- **Part 1:** Brute-force search (small numbers) is enough.  
- **Part 2:** Requires **Extended GCD (Bézout’s identity)** to solve efficiently.
- **Token cost optimization** prioritizes pressing the **cheaper B button** more, whenever possible.

---

## 📂 Files in This Folder  

| File Name            | Description                                                     |
| -------------------- | --------------------------------------------------------------- |
| `PuzzleDay13.java`   | Java implementation for Part 1                                  |
| `PuzzleDay13P2.java` | Java implementation for Part 2                                  |
| `prizes.txt`         | Input file containing button behaviors and prize locations      |
| `prizesTest.txt`     | Input test file containing button behaviors and prize locations |

---

## 🧠 Approach  

### Part 1 (PuzzleDay13.java):
1. Parse input for each machine: button A move, button B move, prize coordinates.
2. Brute-force small combinations of A and B presses to reach prize.
3. Select minimal token cost across all valid solutions.
4. Sum minimal tokens across all winnable machines.

### Part 2 (PuzzleDay13P2.java):
1. Parse input similarly, but adjust all prize coordinates by **+10^13**.
2. Solve the system using **Extended Euclidean Algorithm**:
 - Find integer solutions `(a, b)` satisfying movement equations.
3. Adjust solutions to ensure **non-negative number of presses**.
4. Minimize total token cost for each winnable machine.

---

## 📌 Actual Output Summary  

| Part   | Result             |
| ------ | ------------------ |
| Part 1 | **36571**          |
| Part 2 | **85527711500010** |

---

📝 **Author:** [Kishan Mrug](https://www.linkedin.com/in/kishan-mrug/)