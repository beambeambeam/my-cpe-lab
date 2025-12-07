# M3 Practice Problems

## Directory Structure

```
pratice-sheets/m3/
└── src/
    └── kmutt/cpe/algorithm/placeholder/
        ├── Image.java
        ├── image.png
        ├── P00_ImageProcessing.java
        ├── P01_Wee.java
        ├── P02_GrilledGooseDPTabular.java      (Bottom-up DP)
        ├── P02_GrilledGooseDPMemoization.java  (Top-down DP)
        ├── P03_Salesman.java
        ├── P03_SalesmanDPTabular.java          (Bottom-up DP - Held-Karp)
        ├── P03_SalesmanDPMemoization.java      (Top-down DP - Held-Karp)
        └── P04_DragQueen.java
```

## Algorithm Patterns Summary

| File                                 | Pattern                    | Problem Type                     |
| ------------------------------------ | -------------------------- | -------------------------------- |
| `Image.java`                         | Utility class              | -                                |
| `P00_ImageProcessing.java`           | Iteration                  | Image manipulation               |
| `P01_Wee.java`                       | Greedy                     | Number partitioning              |
| `P02_GrilledGooseDPMemoization.java` | DP (Memoization)           | Unbounded Knapsack / Coin Change |
| `P02_GrilledGooseDPTabular.java`     | DP (Tabulation)            | Same as above                    |
| `P03_Salesman.java`                  | Brute Force / Backtracking | TSP                              |
| `P03_SalesmanDPMemoization.java`     | Bitmask DP (Memoization)   | TSP (Held-Karp)                  |
| `P03_SalesmanDPTabular.java`         | Bitmask DP (Tabulation)    | TSP (Held-Karp)                  |
| `P04_DragQueen.java`                 | Greedy + Max-Heap          | Reorganize String                |

## How to Compile and Run

Navigate to the `src` directory first:

```bash
cd pratice-sheets/m3/src
```

### P00 - Image Processing (Brighten/Darken)

```bash
javac kmutt/cpe/algorithm/placeholder/Image.java
javac kmutt/cpe/algorithm/placeholder/P00_ImageProcessing.java
java kmutt.cpe.algorithm.placeholder.P00_ImageProcessing
```

Output: Creates `brighten.png` and `darken.png` in the placeholder directory.

### P01 - Wee (Coin Partition)

```bash
javac kmutt/cpe/algorithm/placeholder/P01_Wee.java
java kmutt.cpe.algorithm.placeholder.P01_Wee
```

Input:

```
7
```

Output:

```
1 6 7
2 3 4 5
```

### P02 - Grilled Goose (Largest Unreachable Sum)

Both variants solve the same problem with O(maxSum \* n) time complexity.

**Tabular (Bottom-up):**

```bash
javac kmutt/cpe/algorithm/placeholder/P02_GrilledGooseDPTabular.java
java kmutt.cpe.algorithm.placeholder.P02_GrilledGooseDPTabular
```

**Memoization (Top-down):**

```bash
javac kmutt/cpe/algorithm/placeholder/P02_GrilledGooseDPMemoization.java
java kmutt.cpe.algorithm.placeholder.P02_GrilledGooseDPMemoization
```

Input:

```
100
4
8 12 15 20
```

Output:

```
49
```

### P03 - Salesman (Maximum Cost TSP)

```bash
javac kmutt/cpe/algorithm/placeholder/P03_Salesman.java
java kmutt.cpe.algorithm.placeholder.P03_Salesman
```

Input:

```
5 12
THA UK 6
THA UAE 4
THA USA 8
UK UAE 2
UK USA 3
UK SWE 7
UAE USA 3
UAE SWE 6
UAE DEN 10
USA SWE 9
USA DEN 5
SWE DEN 9
```

Output:

```
THA UK UAE DEN SWE USA THA
44
```

### P03 - Salesman DP Variants (Held-Karp Algorithm)

Both variants use bitmask DP with O(n^2 \* 2^n) time complexity, much better than brute force O(n!).

**Tabular (Bottom-up):**

```bash
javac kmutt/cpe/algorithm/placeholder/P03_SalesmanDPTabular.java
java kmutt.cpe.algorithm.placeholder.P03_SalesmanDPTabular
```

**Memoization (Top-down):**

```bash
javac kmutt/cpe/algorithm/placeholder/P03_SalesmanDPMemoization.java
java kmutt.cpe.algorithm.placeholder.P03_SalesmanDPMemoization
```

### P04 - DragQueen (Rearrange Adjacent Duplicates)

```bash
javac kmutt/cpe/algorithm/placeholder/P04_DragQueen.java
java kmutt.cpe.algorithm.placeholder.P04_DragQueen
```

Input:

```
aabca
```

Output:

```
abaca
```

## Compile All at Once

```bash
cd pratice-sheets/m3/src
javac kmutt/cpe/algorithm/placeholder/*.java
```
