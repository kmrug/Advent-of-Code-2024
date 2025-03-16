# 🎄 Day 8: Resonant Collinearity  

## 📜 Problem Description  

You find yourself on the **roof of a top-secret Easter Bunny installation**. While the **Historians** search for the **Chief Historian**, you notice a **large antenna** transmitting a signal.  

Upon further investigation, you discover that **multiple antennas** are broadcasting a **resonant signal** across the city. Each antenna is labeled with a **single character**—either a **lowercase letter, uppercase letter, or a digit**—which represents its **frequency**.  

You create a **map** (your puzzle input) of these antennas.  

### **How Antinodes Are Created**
An **antinodal point** is a position where the **resonant effect** occurs. These positions appear when:
1. **Two antennas of the same frequency are in a straight line.**
2. **One antenna is exactly twice the distance of the other.**
3. **Each antenna pair creates two antinodes—one on either side.**

#### **Example Input**

```
............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............
```

#### **Example Output**

Total antinode locations: `14`

✅ **Key Rules for Antinode Creation**  
- **Antennas must have the same frequency** to create antinodes.  
- **Antinodes appear symmetrically** on both sides of the two antennas.  
- **Uppercase and lowercase letters count as different frequencies** (`A ≠ a`).  
- **Antinodes can occur at an antenna’s location.**  

---

## Part 1: Calculate Initial Antinode Locations  

Using the **basic model**, calculate the **number of unique grid locations** that contain an antinode.

---

## Part 2: Accounting for Resonant Harmonics  

One of the **Historians** notices that the model needs to account for **resonant harmonics**. Now, an **antinodal point occurs at any position exactly in line with at least two antennas of the same frequency**—even if they **aren’t spaced at a 2:1 ratio**.

This means:
- Any **antenna** that is **aligned with at least two others** of the same frequency is **also an antinode**.
- The number of **antinodes increases significantly**.

### **Updated Example Output**

Total antinode locations (updated model): `34`

---

## 🔑 Key Observations  

- **Part 1:** Calculated antinodes using a **strict 2:1 distance rule** between antennas.  
- **Part 2:** Expanded to **any collinear set of antennas**, significantly increasing the number of antinodes.  
- **Antinodes can now occur directly on antennas**, if aligned with at least two others.  

---

## 📂 Files in This Folder  

| File Name           | Description                       |
| ------------------- | --------------------------------- |
| `PuzzleDay8.java`   | Java implementation of the puzzle |
| `antenna.txt`       | Grid map containing antennas      |
| `antennaTest.txt`   | Grid map containing antennas      |
| `antennaTestP2.txt` | Grid map containing antennas      |

---

## 🧠 Approach  

### **Part 1 (Strict 2:1 Distance Rule):**  
1. **Parse the grid input** → Identify and store all **antenna locations and frequencies**.  
2. **Find pairs of antennas** with the **same frequency**.  
3. **Check if one is exactly twice the distance from the other** in a straight line.  
4. **Mark valid antinode locations** and count **unique positions**.  

### **Part 2 (Expanded Harmonic Model):**  
1. **Find all sets of three or more antennas that are collinear.**  
2. **Mark every antenna in those sets as an antinode.**  
3. **Include all valid antinodes from Part 1.**  
4. **Count the total number of unique antinode locations.**  

---

## 📌 Actual Output Summary  

| Part   | Result  |
| ------ | ------- |
| Part 1 | **244** |
| Part 2 | **912** |

---

📝 **Author:** [Kishan Mrug](https://www.linkedin.com/in/kishan-mrug/)