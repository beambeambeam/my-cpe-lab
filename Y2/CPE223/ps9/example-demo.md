Source: :contentReference[oaicite:0]{index=0}

# LAB

---

# LRU Example

- Example
- Data cache has 8 blocks, each block contain a 16-bit word.
- A 16-bit memory address is used.
- Array `A(4, 10)` is stored in column order.

---

# Example Program

- Execute a program that normalizes the elements of the first row with respect to the average value of elements in the row.

```text
SUM := 0

for j := 0 to 9 do
    SUM := SUM + A(0,j);
end

AVE := SUM / 10;

for i := 9 downto 0 do
    A(0,i) := A(0,i) / AVE;
end
```

---

# Mapping Schemes

Task: Create animation of tables representing how the data cache content evolve in each iteration.

- Associative mapping
  - tag = 16
  - word = 0

- Set associative mapping
  - tag = 15
  - set = 1
  - word = 0

---

# Example: LRU with Direct Mapping

- Direct mapping

## Content of Data Cache After Pass

| Block position | j = 0 |     |     |     |     |     |     |     |     |
| -------------- | ----- | --- | --- | --- | --- | --- | --- | --- | --- |
| 0              |       |     |     |     |     |     |     |     |     |
| 1              |       |     |     |     |     |     |     |     |     |
| 2              |       |     |     |     |     |     |     |     |     |
| 3              |       |     |     |     |     |     |     |     |     |
| 4              |       |     |     |     |     |     |     |     |     |
| 5              |       |     |     |     |     |     |     |     |     |
| 6              |       |     |     |     |     |     |     |     |     |
| 7              |       |     |     |     |     |     |     |     |     |

Contents of a direct-mapped data cache.
