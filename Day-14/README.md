# 🎄 Day 14: Restroom Redoubt

## 📜 Problem Description

While trying to reach the bathroom at **Easter Bunny Headquarters**, you're confronted with a new security obstacle: **teleporting robots** patrolling the tiled floor.

Each robot is defined by:

- A **starting position**: `p=x,y`
- A **velocity**: `v=dx,dy`

The robots move in **straight lines**, and when they hit the edge of the space (101x103 tiles), they **wrap around** like a toroidal grid.

---

## Part 1: Predict Robot Positions After 100 Seconds

After simulating 100 seconds of motion:

- Robots wrap around if they exceed map boundaries.
- Robots can share the same tile.

You must:
- **Track the number of robots** in each quadrant.
- Multiply the robot counts of all four quadrants to get the **safety factor**.

### Example Input

```
p=0,4 v=3,-3
p=6,3 v=-1,-3
p=10,3 v=-1,2
p=2,0 v=2,-1
p=0,0 v=1,3
p=3,0 v=-2,-2
p=7,6 v=-1,-3
p=3,0 v=-1,-2
p=9,3 v=2,3
p=7,3 v=-1,2
p=2,4 v=2,-3
p=9,5 v=-3,-3
```

After 100 seconds, compute the robots per tile and derive quadrant counts:

| Quadrant | Robot Count |
| -------- | ----------- |
| Q1       | 1           |
| Q2       | 3           |
| Q3       | 4           |
| Q4       | 1           |

✅ Safety Factor = `1 × 3 × 4 × 1 = 12`

---

## Part 2: Find the Christmas Tree Easter Egg

You're told the robots may **form a hidden image** when aligned at a special moment (like a **Christmas tree**).

You must:
- Find the **minimum number of seconds** needed for robots to align visibly.
- Detect visual patterns (usually when bounding box height/width is minimized).

### Hint Strategy
- Track bounding box area over time.
- Stop when robot arrangement becomes visually **stable and compact**.

---

## 🔑 Key Observations

- Robot motion follows:  

newX = `(startX + dx * seconds) % WIDTH`
newY = `(startY + dy * seconds) % HEIGHT`

- Wrapping ensures no robot leaves the map.
- In Part 2, the answer isn't a number you can solve mathematically—you must **observe patterns over time**.

---

## 📂 Files in This Folder

| File Name             | Description                                                |
| --------------------- | ---------------------------------------------------------- |
| `PuzzleDay14.java`    | Java implementation for both part 1                        |
| `PuzzleDay14P2.java`  | Java implementation for both part 2                        |
| `robot.txt`           | Input file containing robot data                           |
| `robotTest.txt`       | Input file containing robot data                           |
| `grid_iterations.txt` | Generated file to see the christmas tree at iteration 6446 |
| `Tree.png`            | Picture of the Christmas Tree                              |

---

## 🧠 Approach

### Part 1:

1. Parse each robot's position and velocity.
2. For each robot:
 - Calculate final position after 100 seconds using modular arithmetic.
3. Count robots in each quadrant (excluding midlines).
4. Return the product of the four quadrant counts.

### Part 2:

1. Simulate movement over time.
2. Track the **bounding box** of all robots at each time step.
3. When bounding box area starts increasing, stop:
 - Visual pattern (Christmas tree) appears at the **smallest bounding box**.
4. Return the number of seconds elapsed.

---

## 📌 Actual Output Summary

| Part   | Result        |
| ------ | ------------- |
| Part 1 | **218619324** |
| Part 2 | **6446**      |

---

📝 **Author:** [Kishan Mrug](https://www.linkedin.com/in/kishan-mrug/)