# 🎄 Day 11: Plutonian Pebbles  

## 📜 Problem Description  

While exploring **Pluto's ancient civilization**, you observe a strange set of **physics-defying stones**.  
Each stone:
- Is arranged in a **perfect straight line**.
- Has a **number engraved** on it.

The stones **change behavior every time you blink**, following specific rules.

✅ **Blinking Rules:**
1. If the stone is engraved with **0**, it becomes a stone engraved with **1**.
2. If the stone has an **even number of digits**, it **splits** into two stones:
   - Left half → new left stone.
   - Right half → new right stone.
   - Leading zeros are **discarded**.
3. Otherwise, **multiply the number by 2024**.

The **order of stones is preserved** during all transformations.

---

## Part 1: Stone Evolution after 25 Blinks  

You are asked:
- Given the **initial list of stones** (your puzzle input),
- **Simulate 25 blinks**,
- **Count the total number of stones** after 25 blinks.

### Example Walkthrough

Initial Stones: `125 17`

Evolution:

```
Blink 1: 253000 1 7
Blink 2: 253 0 2024 14168
Blink 3: 512072 1 20 24 28676032
Blink 4: 512 72 2024 2 0 2 4 2867 6032
Blink 5: 1036288 7 2 20 24 4048 1 4048 8096 28 67 60 32
...
```

✅ **Key Simulation Points:**
- Splitting and multiplying happen **at the same time** for all stones per blink.
- A **single stone** can split into multiple stones during one blink.

### Example Output:

Number of stones after 25 blinks: `55312`

---

## Part 2: Stone Evolution after 75 Blinks  

Now, simulate **75 total blinks** starting from the same initial configuration.

✅ **Notes for Part 2:**
- The number of stones **grows extremely fast**.
- Optimizations such as **grouping stones by digit patterns** and **memoization** might be needed to simulate efficiently.

### Example Output:

Number of stones after 75 blinks: `(very large number)`

---

## 🔑 Key Observations  

- **Rule priority matters:** Always apply Rule 1 first (stone = 0 → 1).  
- **Splitting a stone** exponentially increases the number of stones.
- The problem can **quickly explode in size**, requiring **careful simulation** or **optimized counting** rather than tracking individual stones.

---

## 📂 Files in This Folder  

| File Name          | Description                                    |
| ------------------ | ---------------------------------------------- |
| `PuzzleDay11.java` | Java implementation of the puzzle              |
| `stone.txt`        | Input file containing initial stones list      |
| `stoneTest.txt`    | Input test file containing initial stones list |

---

## 🧠 Approach  

### Part 1 (Simple Simulation for 25 Blinks):
1. Parse the **initial list of stones**.
2. For **each blink**:
   - Apply the blinking rules to **each stone** in the current list.
   - Build a **new list** of stones for the next blink.
3. After 25 blinks, **count the number of stones**.

### Part 2 (Optimized Simulation for 75 Blinks):
1. Same parsing logic as Part 1.
2. Use **optimized techniques** like:
   - **Grouping stones by value** (count how many of each type).
   - **Efficient transformations** rather than tracking individual stones.
3. Simulate all 75 blinks efficiently.
4. Count the final number of stones.

---

## 📌 Actual Output Summary  

| Part   | Result              |
| ------ | ------------------- |
| Part 1 | **188902**          |
| Part 2 | **223894720281135** |

---

📝 **Author:** [Kishan Mrug](https://www.linkedin.com/in/kishan-mrug/)