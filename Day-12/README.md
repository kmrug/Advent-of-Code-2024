# 🎄 Day 12: Garden Groups  

## 📜 Problem Description  

You help the Elves plan fencing for a **massive farm** full of different **garden plots**. Each plot grows a specific type of plant, labeled with a **single letter**.

✅ **Region Formation Rules:**
- Plots of the same type that are **connected horizontally or vertically** form a **region**.
- Diagonal connections **do not** link plots into the same region.

Your task is to calculate:
- The **area** of each region (number of garden plots).
- The **perimeter** of each region (number of exposed sides).

✅ **Perimeter Calculation:**
- Each plot has **four sides**.
- A side counts toward the perimeter if it is **next to empty space or a different plant type**.

---

## Part 1: Total Price Using Area × Perimeter  

The **cost** to fence a region is: `Area × Perimeter`

### Example Input  

```
AAAA
BBCD
BBCC
EEEC
```

Regions:
- A → Area 4, Perimeter 10
- B → Area 4, Perimeter 8
- C → Area 4, Perimeter 10
- D → Area 1, Perimeter 4
- E → Area 3, Perimeter 8

✅ **Example Price Calculation:**

Region A → 4 × 10 = 40
Region B → 4 × 8 = 32
Region C → 4 × 10 = 40
Region D → 1 × 4 = 4
Region E → 3 × 8 = 24

Total Price = `140`

---

### Larger Example  

Input:

```
RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE
```

Calculated region prices:
- R → 216
- I → 32
- C → 392
- F → 180
- V → 260
- J → 220
- C → 4
- E → 234
- I → 308
- M → 60
- S → 24

Total Price = **1930**

---

## 🔑 Key Observations  

- Each **new region** must be detected carefully (BFS/DFS flood fill).  
- **Perimeter counting** needs to check each plot's 4 sides independently.
- The **total price** is **the sum of price for all regions**.

---

## 📂 Files in This Folder  

| File Name          | Description                       |
| ------------------ | --------------------------------- |
| `PuzzleDay12.java` | Java implementation of the puzzle |
| `garden.txt`       | Garden map input                  |
| `gardenPart2.txt`  | Garden map input for Part 2       |
| `gardenTest.txt`   | Garden map test input             |

---

## 🧠 Approach  

### Part 1 (Completed):
1. Parse the **garden map** into a 2D grid.  
2. Use **BFS or DFS** to find **connected regions** of the same plant type.  
3. For each region:
   - Count the **area** (number of cells).
   - Calculate the **perimeter** (count exposed sides).
4. For each region, compute **Area × Perimeter**.
5. Sum all prices.

### Part 2 (Skipped for Now):
- Part 2 introduces **a different fencing cost calculation** based on **number of sides**, not perimeter.
- It was found **more complex**, so this part was **deferred** for now.

---

## 📌 Actual Output Summary  

| Part   | Result            |
| ------ | ----------------- |
| Part 1 | **1522850**       |
| Part 2 | *Skipped for now* |

---

📝 **Author:** [Kishan Mrug](https://www.linkedin.com/in/kishan-mrug/)