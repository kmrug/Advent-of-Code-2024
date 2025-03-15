# 🎄 Day 7: Bridge Repair  

## 📜 Problem Description  

The **Historians** take you to a **rope bridge** over a jungle river. The **Chief Historian** isn't on this side, so you need to **cross the bridge**.  

However, a group of **engineers** is busy **repairing the bridge**, and they won’t let anyone cross until it's **fully calibrated**. The problem? Some **young elephants** stole all the **operators** from the calibration equations!  

The engineers need **your help** to determine which test values can be obtained by inserting **any combination of operators** into the calibration equations.  

Each equation follows this format:

`TEST_VALUE: NUM1 NUM2 NUM3 ...`

Your task is to check if inserting **addition (`+`)** and **multiplication (`*`)** operators in the given order (without rearranging the numbers) can make the right side **equal** the test value.  

---

## Part 1: Determine Valid Equations  

Using only `+` and `*`, find which equations can be **made true** by inserting operators.

### Example Input  

```
190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20
```

### Example Output  

Valid Equations:

```
190: 10 * 19
3267: 81 + 40 * 27
292: 11 + 6 * 16 + 20
```

Total Calibration Result: `3749`

✅ **Key Calculation Rules:**  
- Operators **always evaluate left-to-right** (ignoring normal precedence rules).  
- Numbers **cannot be rearranged**.  

---

## Part 2: Adding Concatenation (`||`)  

The **engineers realize** they missed something: some elephants were hiding an additional operator—**concatenation (`||`)**!  

Now, apart from `+` and `*`, you can **merge two numbers** by placing them together as a single number.  

### Example Output (with `||`)  

Valid Equations:

```
156: 15 || 6 = 156
7290: 6 * 8 || 6 * 15
192: 17 || 8 + 14
```

New Total Calibration Result: `11387`

✅ **Key Calculation Rules:**  
- `||` **joins numbers** (e.g., `12 || 345` → `12345`).  
- Operators still **evaluate left-to-right**.  

---

## 🔑 Key Observations  

- **Part 1** was solved **recursively**, testing every valid combination of `+` and `*`.  
- **Part 2** required a **brute force approach** due to the complexity of `||`.  
- The final solution for **Part 2** was **inspired by another approach**, optimizing brute force attempts.  

---

## 📂 Files in This Folder  

| File Name       | Description                         |
|----------------|-------------------------------------|
| `solution.java` | Java implementation of the puzzle |
| `equations.txt` | Calibration equations input       |

---

## 🧠 Approach  

### **Part 1 (Recursive Solution):**  
1. **Parse the input** → Extract test values and number sequences.  
2. **Recursively try inserting `+` and `*`** between numbers.  
3. **Evaluate expressions left-to-right** to check if they match the test value.  
4. **Sum up all valid test values**.  

### **Part 2 (Brute Force with Concatenation):**  
1. **Extend the recursive solution** to include `||` (concatenation).  
2. **Generate all possible valid operator placements** (including concatenation).  
3. **Brute-force evaluate each possibility** to see if it matches the test value.  
4. **Sum up all valid test values**.  

⚠️ **The approach for Part 2 was largely brute-forced** and was inspired by a method I saw elsewhere. It significantly increases the number of valid expressions and requires heavy computation.  

---

## 📌 Actual Output Summary  

| Part    | Result |
|---------|-------|
| Part 1  | **975671981569** |
| Part 2  | **223472064194845** |

---

📝 **Author:** [Kishan Mrug](https://www.linkedin.com/in/kishan-mrug/)