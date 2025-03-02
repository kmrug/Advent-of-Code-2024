# 🎄 Day 5: Print Queue  

## 📜 Problem Description  

Satisfied with their search on Ceres, the squadron of scholars suggests subsequently scanning the stationery stacks of sub-basement 17.

The North Pole printing department is busier than ever this close to Christmas, and while The Historians continue their search of this historically significant facility, an Elf operating a very familiar printer beckons you over.

The Elf must recognize you, because they waste no time explaining that the new sleigh launch safety manual updates won't print correctly. Failure to update the safety manuals would be dire indeed, so you offer your services.

Safety protocols clearly indicate that new pages for the safety manuals must be printed in a very specific order. The notation **X|Y** means that if both page number **X** and page number **Y** are to be produced as part of an update, page number **X** must be printed at some point **before** page number **Y**.

---

## Part 1: Identify Correctly Ordered Updates  

Your task is to determine which updates have their pages in the correct order according to the ordering rules. Then, find the **middle page number** of each correctly ordered update and **sum them up**.  

### Example Input  

**Rules:**  

```
47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47
```

### Example Output  

Correctly Ordered Updates:  
- **75,47,61,53,29** → Middle: **61**  
- **97,61,53,29,13** → Middle: **53**  
- **75,29,13** → Middle: **29**  

Total Sum: `143`

---

## Part 2: Fix Incorrectly Ordered Updates  

For each of the **incorrectly-ordered updates**, sort the pages according to the ordering rules and then calculate the **middle page number**.

### Example Corrections  
- `75,97,47,61,53` → `97,75,47,61,53` → Middle: **47**  
- `61,13,29` → `61,29,13` → Middle: **29**  
- `97,13,75,29,47` → `97,75,47,29,13` → Middle: **47**  

Total Sum: `123`

---

## 🔑 Key Observations  

- Only rules that apply to pages in the update should be considered.  
- The middle page number is calculated after the pages are **sorted or verified**.  
- Overlapping rules can affect the sorting order, so **topological sorting** is an efficient way to handle this problem.  

---

## 📂 Files in This Folder  

| File Name      | Description                   |
|---------------|------------------------------|
| `PuzzleDay5.java` | Java implementation of the puzzle |
| `print.txt`    | Page ordering rules and updates |
| `printTest.txt`    | Page ordering rules and updates |

---

## 🧠 Approach  

### Part 1:  
1. Parse the input to separate **rules** and **updates**.  
2. For each update, validate if the pages follow the ordering rules.  
3. If the update is correct, extract the **middle page number**.  
4. Sum all the middle page numbers from correctly ordered updates.  

### Part 2:  
1. Identify incorrectly ordered updates.  
2. Use **topological sorting** or custom sorting to arrange the pages according to the rules.  
3. Calculate the middle page number from the sorted updates.  
4. Sum the middle page numbers from corrected updates.  

---

## 📌 Actual Output Summary  

| Part    | Result |
|---------|-------|
| Part 1  | **5732** |
| Part 2  | **4716** |

---

📝 **Author:** [Kishan Mrug](https://github.com/kmrug)