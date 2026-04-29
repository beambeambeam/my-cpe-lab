PS9: Replacement Algorithm

Introduction

When a cache is full, the cache controller must decide which one of the old blocks is to be overwritten. Least recently used (LRU) is one of the replacement algorithms. When a block is to be overwritten, it is sensible to overwrite the one that has gone the longest time without being referenced. This block is called the least recently used block.

From the given system information.

- Data cache has 8 blocks, each block contains a 16-bit word.
- A 16-bit memory address is used.
- Array A(4, 10) is stored in column order.

Execute a program that normalizes the elements of the first row with respect to the average value of elements in the row.

SUM := 0
for j:=0 to 9 do
SUM := SUM + A(0,j);
end

AVE := SUM / 10;
for i:=9 to 0 do
A(0,i) := A(0,i) / AVE;
end

Task

Create animation of tables representing how the data cache content evolve in each iteration of the loops from the given program using 2 different Mapping Schemes:

- Associative mapping:
  tag = 16, word = 0

- 2-way Set associative mapping:
  tag = 15, set = 1, word = 0

---

For example, this table represent the cache content of the 4th iteration of the first loop (j loop) using LRU with Associative mapping.

Submission

You are going to submit an digital copy of the report as Power Point file (.ppt) with the:

- First page that contains a list of your team members,
- Second contains the animation of associative mapping table through each iteration.
- Third contains the animation of 2-way set associative mapping table through each iteration.
- The example of how animation should look like can be found in PS9_Direct_Mapping_Demo.ppt which is provided along with this instruction. The file must be open using powerpoint software. otherwise the animation may not be shown properly.
