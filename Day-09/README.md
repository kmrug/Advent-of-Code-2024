# 🎄 Day 9: Disk Fragmenter  

## 📜 Problem Description  

You arrive in the **hallways of some familiar amphipods**, where you notice one struggling with a **disk defragmentation program**. He’s trying to **compact files on disk** to make more **contiguous free space**, but his program isn’t working correctly—so you decide to help.

The amphipod provides a **disk map** (your puzzle input), which is a long string of digits. This format represents a sequence of **file lengths and free space lengths**, alternating between them.

### Disk Map Interpretation:

Example Input:  

`2333133121414131402`

This input translates into alternating segments of:
- **File blocks**, assigned an incrementing **ID (starting from 0)**
- **Free space blocks**, represented as dots `.`

For example:  

`2333133121414131402 → 00...111...2...333.44.5555.6666.777.888899`

---

## Part 1: Move File Blocks One-by-One  

To compact the disk:
- **Move file blocks one at a time** to the **leftmost free space**.
- Continue moving until **all file blocks are packed to the left**.

Then, compute the **filesystem checksum**:
- For each **non-free-space block**, multiply its **position index** by its **file ID**.
- Add all such products.

### Example Process:

Initial: `0..111....22222`

Compacted: `022111222......`

Checksum calculation: `pos * ID → 22 + 32 + 4*1 + ... → Total = 1928`

✅ **Rules Recap:**
- Move **blocks** (not whole files).
- Always shift to **leftmost** free space.
- Checksum is calculated by **position × file ID**, skipping free blocks.

### Example Output:

Checksum (Part 1): `1928`

---

## Part 2: Move Whole Files Instead  

Now the amphipod wants to:
- Move **entire files at once**, not just individual blocks.
- Files must move to the **leftmost span of free space large enough** to hold them.
- Process files **in descending order of file ID** (starting from highest).
- If no space exists to the left that fits a file, **it stays in place**.

### Example Output:

Checksum (Part 2): `2858`

✅ **Rules Recap:**
- Move **whole files**, not blocks.
- **One move per file**, processed from **highest to lowest ID**.
- New positions must have **enough contiguous free space**.

---

## 🔑 Key Observations  

- Part 1 required simulating the **step-by-step shifting** of individual file blocks (like Tetris).  
- Part 2 required more strategic logic, involving **free space scans**, and was **more efficient** once implemented.  
- The **checksum calculation** remained the same across both parts.

---

## 📂 Files in This Folder  

| File Name         | Description                                      |
| ----------------- | ------------------------------------------------ |
| `PuzzleDay9.java` | Java implementation of the puzzle                |
| `input.txt`       | Puzzle input containing the disk map             |
| `diskTest.txt`    | Puzzle input containing the disk map for testing |
| `disk.txt`        | Puzzle input containing the disk map for testing |

---

## 🧠 Approach  

### Part 1 (Block-by-Block Compaction):
1. Parse disk map into **segments** of file/free blocks.  
2. Simulate **moving file blocks** to the **leftmost free space**.  
3. Build the final disk layout.  
4. Compute checksum: **position × file ID**, skip free blocks.  

### Part 2 (Whole-File Compaction):
1. Parse the same input into file/free space segments.  
2. Process file IDs from **highest to lowest**.  
3. For each file:
   - Look for the **leftmost fitting free space**.
   - If found, move file there in full.  
4. Rebuild final layout and compute checksum.  

---

## 📌 Actual Output Summary  

| Part   | Result            |
| ------ | ----------------- |
| Part 1 | **6385338159127** |
| Part 2 | **6415163624282** |

---

📝 **Author:** [Kishan Mrug](https://www.linkedin.com/in/kishan-mrug/)