# 🧬 SoftComputingGA  
## ⚙️ Components of the Genetic Algorithm (GA)

### 🧩 **Encoding Technique**
- **Type:** Permutation-based integer encoding  
- **Gene:** Represents a single car  
- **Chromosome:** A permutation of all car IDs indicating the charging order  
  - Example: `[3, 1, 2, 4, 5, 6]`  
    - Slot 1 → 3 → 2 → 5  
    - Slot 2 → 1 → 4 → 6  

---

### 🌱 **Initialization Procedure**
Randomly generate several valid chromosomes:
1. Start with all car IDs `[1, 2, ..., n]`
2. Shuffle them randomly to create a valid schedule
3. Repeat to form the initial population of size **N**

---

### 🧠 **Evaluation Function (Fitness Function)**
The **fitness** of each chromosome is based on total charging time or total waiting time:

Fitness = 1/Total Charging Time
or equivalently,
Fitness = 1/Total Waiting Time

Higher fitness values correspond to more efficient charging schedules.

---

### 🎯 **Selection Method**
**Tournament Selection**:
- Randomly pick a subset of individuals (size \( k \))
- Select the best one (highest fitness) as a parent for reproduction

---

### 🔁 **Genetic Operators**
#### **Crossover (Reproduction)**
- **Method:** Order Crossover (OX)  
  Preserves relative order of genes and ensures valid permutations.

#### **Mutation**
- **Method:** Swap Mutation  
  Randomly selects two genes and swaps their positions to maintain diversity.

---

### ⚙️ **Parameter Settings**

| Parameter | Symbol | Value | Description |
|------------|---------|--------|-------------|
| Population size | N | 50 | Number of individuals per generation |
| Mutation rate | m | 0.05 | Probability of mutation per chromosome |
| Crossover rate | c | 0.9 | Probability of crossover between parents |
| Generations | G | 100 | Number of generations to evolve |
| Tournament size | k | 3 | Number of individuals per tournament |
| Slots | m | 2–3 | Number of charging slots |

---

### 🧾 **Summary**
| GA Component | Implementation |
|---------------|----------------|
| **Problem** | Schedule EVs to minimize total waiting and charging time |
| **Encoding** | Permutation of car IDs |
| **Initialization** | Random valid permutations |
| **Evaluation** | Based on total charging/waiting time |
| **Selection** | Tournament Selection |
| **Crossover** | Order Crossover (OX) |
| **Mutation** | Swap Mutation |
| **Parameters** | N=50, m=0.05, c=0.9, G=100, k=3, Slots=2–3 |

---

🧩 **Developed as part of the Soft Computing GA Library Project**  
Faculty of Computers and Artificial Intelligence – Cairo University  
