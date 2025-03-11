# 🎄 Day 6: Guard Gallivant  

## 📜 Problem Description  

The **Historians** use their **time-traveling device** again, transporting you to the **North Pole prototype suit manufacturing lab in the year 1518**. While they search for the **Chief Historian**, you need to **track a patrolling guard** to help them move safely.  

The **lab map** (your puzzle input) contains:
- `#` → **Obstacles** (crates, desks, alchemical reactors, etc.)
- `^` / `>` / `v` / `<` → **Guard's current position and facing direction**
- `.` → **Empty spaces**  

### **Guard's Movement Rules:**
1. If there is an **obstacle in front**, the guard **turns right** by **90 degrees**.
2. If there is **no obstacle**, the guard **moves forward**.

By predicting the **guard's patrol path**, you can determine which positions will be visited.

---

## Part 1: Predict Guard's Path  

Your task is to **simulate the guard's patrol** until they **leave the mapped area**, then count how many distinct positions they **visited**.

### Example Input  

```
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
```

### Example Output  

Guard visits: `41 distinct positions`

✅ **Key Patrol Details:**  
- The guard follows the **turn-right-on-obstacle** rule.
- Once the guard leaves the map, tracking stops.
- The initial position **counts** as visited.

---

## Part 2: Trap the Guard in a Loop  

The **Historians** realize that the **guard’s patrol area is too large** for them to search safely.  
They need to place **one additional obstacle** to **trap the guard in a loop**.

### **Rules for Placing the Obstruction:**  
- The new **obstacle** can be placed **anywhere except the guard’s starting position**.
- The guard **must get stuck in a repeating loop**.
- There can be **multiple valid placements** for the obstacle.

### Example Output  

Possible obstruction placements: `6`

---

## 🔑 Key Observations  

- The guard **always turns right** when an obstacle is ahead.
- Part 1 **tracks all visited positions** until the guard exits the map.
- Part 2 **finds possible positions for an additional obstacle** to **trap the guard**.

---

## 📂 Files in This Folder  

| File Name       | Description                         |
|----------------|-------------------------------------|
| `PuzzleDay6.java` | Java implementation of the puzzle |
| `grid.txt`      | Lab map with guard positions       |
| `gridTest.txt`      | Lab map with guard positions       |

---

## 🧠 Approach  

### **Part 1:**  
1. **Parse the input** → Convert the map into a **2D grid**.  
2. **Simulate movement** → Track the guard’s **turning & stepping** logic.  
3. **Record visited positions** → Store in a **set** to count distinct positions.  
4. **Stop when the guard exits** the grid.  

### **Part 2:**  
1. **Simulate the patrol loop** with different **obstacle placements**.  
2. **Check which positions** create a repeating loop.  
3. **Count the valid obstruction placements**.  

---

## 📌 Actual Output Summary  

| Part    | Result |
|---------|-------|
| Part 1  | **5331** |
| Part 2  | **1812** |

---

## 📝 Author  

**Kishan Mrug**  