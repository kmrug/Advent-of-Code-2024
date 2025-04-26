# 🎄 Day 10: Hoof It  

## 📜 Problem Description  

You arrive at a **Lava Production Facility** on a **floating island** in the sky. While the **Historians** explore, you meet a **reindeer wearing a hard hat** who needs help rebuilding **hiking trails** from a partially **scorched hiking guide**.

You are given a **topographic map** (your puzzle input) where:
- Each number `0` to `9` represents **elevation** (higher is harder to climb).
- The goal is to **trace hiking trails** that always **increase by exactly 1** at each step.

✅ **Movement Rules:**
- Move only **up, down, left, or right** (no diagonals).
- A valid hiking trail must:
  - Start at elevation `0`.
  - End at elevation `9`.
  - Increase exactly by **1** at every step.

---

## Part 1: Sum of Trailhead Scores  

Your first task is to:
- **Find all trailheads** (cells with height `0`).
- For each trailhead, count how many distinct `9` positions it can reach by following a valid trail.
- Sum the scores for all trailheads.

### Example Input

```
0123
1234
8765
9876
```

### Example Output

Trailhead score sum: `1`

✅ **Important Details:**
- A trailhead’s **score** is the number of different `9` height positions it can reach via any valid trail.

---

## Part 2: Sum of Trailhead Ratings  

Later, the reindeer brings a new instruction:  
- Now, you must calculate each trailhead’s **rating**, which is:
  - The **number of distinct valid hiking trails** that start at that trailhead.

Each valid trail must:
- Start at `0`.
- Only step to a neighbor whose height is exactly **+1** from the current height.
- End at a `9`.

### Example Output

Trailhead rating sum: `81`

✅ **Important Details:**
- Every distinct valid path counts toward the rating.
- Multiple distinct paths can lead to the same `9`.

---

## 🔑 Key Observations  

- **Part 1:** Focused on reaching any `9` via **any valid path**, counted only **destinations**.  
- **Part 2:** Focused on counting **all distinct hiking trails** from each trailhead, not just unique destinations.  
- **Dynamic programming or DFS with memoization** helped efficiently count paths for Part 2.  

---

## 📂 Files in This Folder  

| File Name          | Description                       |
| ------------------ | --------------------------------- |
| `PuzzleDay10.java` | Java implementation of the puzzle |
| `trail.txt`        | Topographic map input             |
| `trailTest.txt`    | Topographic map test input        |

---

## 🧠 Approach  

### Part 1 (Trailhead Scores):
1. Parse the **topographic map** into a grid.  
2. For every `0` position (trailhead), perform a **BFS or DFS**:
   - Find all reachable `9` heights following the `+1 per move` rule.
3. Count the **number of distinct 9s** reachable for each trailhead.
4. Sum the scores.

### Part 2 (Trailhead Ratings):
1. Reuse the parsed grid.
2. For each trailhead, use **DFS with memoization**:
   - At every step, only move to neighbors with height **+1**.
   - Count all distinct valid trails ending at `9`.
3. Sum the ratings for all trailheads.

---

## 📌 Actual Output Summary  

| Part   | Result   |
| ------ | -------- |
| Part 1 | **811**  |
| Part 2 | **1794** |

---

📝 **Author:** [Kishan Mrug](https://www.linkedin.com/in/kishan-mrug/)