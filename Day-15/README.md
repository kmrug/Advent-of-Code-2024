# 🎄 Day 15: Warehouse Woes

## 📜 Problem Description

In the lanternfish warehouse, a robot is misbehaving and pushing boxes around in a constrained grid. The robot follows a specific movement sequence (like `^^<v>`) and interacts with boxes (`O`), walls (`#`), and empty space (`.`).

The robot can:
- Move freely into empty spaces.
- Push a box forward if there's an empty space beyond it.
- Fail to move if the box would hit a wall or another obstacle.

---

## Part 1: Predict Final Box Positions

After applying the robot's full movement sequence:

- Compute the final position of each box.
- Each box contributes to a **GPS coordinate sum**, calculated as:  
  `100 * row_index + column_index`

The goal is to compute the total of these GPS coordinates after simulation completes.

### Example Grid

```
#######
#..O..#
#.#@#.#
#..O..#
#######
```

Robot moves according to the instruction string (e.g., `>>>vvv<<<^^^`), updating the positions of itself and any pushed boxes.

✅ GPS coordinate sum = `100 * row + col` for each box  
✅ Final answer is the sum of all such values

---

## Part 2: Larger Grid and Wider Boxes

In Part 2:

- The grid is **twice as wide**.
- Boxes (`O`) are now **2 units wide** (`OO` or `[]`), but the robot remains 1 unit wide.
- The robot must push entire boxes without them overlapping or breaking apart.

The GPS calculation changes:
- Use the **top-left corner** of the 2-wide box as the reference.
- GPS = `100 * row + col` (top-left corner only)

### New Rules:
- Robot can't push boxes into walls or other boxes.
- If the path is blocked or the push is invalid, the robot doesn't move.

---

## 🔑 Key Observations

- All motion is **directional and deterministic**.
- For Part 1, the state is simple enough for a 2D grid simulation.
- For Part 2, box width and position complexity increase significantly.
- Simulations should avoid redundant moves and track positions precisely.

---

## 📂 Files in This Folder

| File Name            | Description                                                |
| -------------------- | ---------------------------------------------------------- |
| `PuzzleDay15.java`   | Java implementation for part 1                             |
| `PuzzleDay15P2.java` | Java implementation for part 2                             |
| `fishMap.txt`        | Puzzle input with warehouse grid + instructions            |
| `fishTest.txt`       | Test puzzle input with warehouse grid + instructions       |
| `fishTestSmall.txt`  | Small test puzzle input with warehouse grid + instructions |
| `fishTestSmall2.txt` | Small test puzzle input with warehouse grid + instructions |

---

## 🧠 Approach

### Part 1:

1. Parse input into a 2D grid and movement instructions.
2. Track the robot's position and simulate each move:
   - If direction is valid, move or push the box if possible.
   - If blocked, skip the move.
3. After simulation, locate all boxes and calculate:
   - `GPS = 100 * row + col` for each box
4. Return the sum.

### Part 2:

1. Expand the grid horizontally.
2. Parse and treat all boxes as **2-wide units**.
3. For each movement:
   - Validate if the robot can push an entire 2-wide box.
   - Ensure no overlap or wall collision occurs.
4. After the moves:
   - Find top-left of each box
   - Compute total GPS sum.

---

## 📌 Actual Output Summary

| Part   | Result       |
| ------ | ------------ |
| Part 1 | **1294459**  |
| Part 2 | _To Be Done_ |

---

📝 **Author:** [Kishan Mrug](https://www.linkedin.com/in/kishan-mrug/)