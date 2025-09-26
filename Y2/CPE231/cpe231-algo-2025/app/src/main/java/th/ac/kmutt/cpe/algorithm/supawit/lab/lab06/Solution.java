package th.ac.kmutt.cpe.algorithm.supawit.lab.lab06;

import java.util.*;
import java.io.*;

public class Solution {
  private static final double EPSILON = 1e-9;

  private class Box {
    public double x;
    public double y;
    public double width;
    public double length;

    public Box(double x, double y, double w, double h) {
      this.x = x;
      this.y = y;
      width = w;
      length = h;
    }

    public double getX() {
      return x;
    }

    public double getY() {
      return y;
    }

    public double getWidth() {
      return width;
    }

    public double getLength() {
      return length;
    }
  }

  private class Bin {
    public double width, length;
    ArrayList<ArrayList<Box>> space;

    public Bin(double w, double h) {
      this.length = h;
      this.width = w;
      this.space = new ArrayList<ArrayList<Box>>();
    }

    public boolean placeBox(Box box, double x, double y) {
      if (x < -EPSILON || y < -EPSILON ||
          x + box.getWidth() > this.width + EPSILON ||
          y + box.getLength() > this.length + EPSILON) {
        return false;
      }

      Box newBox = new Box(x, y, box.getWidth(), box.getLength());

      for (Box placedBox : getAllPlacedBoxes()) {
        if (isOverlap(newBox, placedBox)) {
          return false;
        }
      }

      if (space.isEmpty()) {
        ArrayList<Box> newRow = new ArrayList<>();
        newRow.add(newBox);
        space.add(newRow);
      } else {
        space.get(0).add(newBox);
      }
      return true;
    }

    public ArrayList<Box> getAllPlacedBoxes() {
      ArrayList<Box> allBoxes = new ArrayList<>();
      for (ArrayList<Box> row : space) {
        allBoxes.addAll(row);
      }
      return allBoxes;
    }

    private boolean isOverlap(Box a, Box b) {
      return !(a.getX() + a.getWidth() <= b.getX() + EPSILON ||
          b.getX() + b.getWidth() <= a.getX() + EPSILON ||
          a.getY() + a.getLength() <= b.getY() + EPSILON ||
          b.getY() + b.getLength() <= a.getY() + EPSILON);
    }

    public double getLength() {
      return this.length;
    }

    public double getWidth() {
      return this.width;
    }
  }

  public void binPacking(ArrayList<Box> boxes) {
    Bin bin = getBin();

    ArrayList<Box> sortedBoxes = new ArrayList<>(boxes);
    sortedBoxes.sort((b1, b2) -> {
      double area1 = b1.getWidth() * b1.getLength();
      double area2 = b2.getWidth() * b2.getLength();
      return Double.compare(area2, area1);
    });

    for (Box box : sortedBoxes) {
      if (!placeBoxOptimally(bin, box)) {
        System.out.println("Box " + box.getWidth() + "x" + box.getLength() + " could not be placed in the bin.");
      }
    }

    prettifiedPrintSpace(bin, sortedBoxes);
  }

  private boolean placeBoxOptimally(Bin bin, Box box) {
    ArrayList<Double> xPositions = getCandidateXPositions(bin, box);
    ArrayList<Double> yPositions = getCandidateYPositions(bin, box);

    for (double y : yPositions) {
      for (double x : xPositions) {
        if (bin.placeBox(box, x, y)) {
          return true;
        }
      }
    }
    return false;
  }

  private ArrayList<Double> getCandidateXPositions(Bin bin, Box box) {
    TreeSet<Double> positions = new TreeSet<>();

    positions.add(0.0);
    positions.add(Math.max(0, bin.getWidth() - box.getWidth()));

    for (Box placedBox : bin.getAllPlacedBoxes()) {
      positions.add(Math.max(0, placedBox.getX() - box.getWidth()));
      positions.add(placedBox.getX() + placedBox.getWidth());
    }

    return new ArrayList<>(positions);
  }

  private ArrayList<Double> getCandidateYPositions(Bin bin, Box box) {
    TreeSet<Double> positions = new TreeSet<>();

    positions.add(0.0);
    positions.add(Math.max(0, bin.getLength() - box.getLength()));

    for (Box placedBox : bin.getAllPlacedBoxes()) {
      positions.add(Math.max(0, placedBox.getY() - box.getLength()));
      positions.add(placedBox.getY() + placedBox.getLength());
    }

    return new ArrayList<>(positions);
  }

  private void prettifiedPrintSpace(Bin bin, ArrayList<Box> sortedBoxes) {
    System.out.println("\n");
    double maxX = 0, maxY = 0;
    for (ArrayList<Box> row : bin.space) {
      for (Box box : row) {
        if (box.getX() != -1 && box.getY() != -1) {
          maxX = Math.max(maxX, box.getX() + box.getWidth());
          maxY = Math.max(maxY, box.getY() + box.getLength());
        }
      }
    }

    int gridWidth = (int) Math.ceil(maxX + 5);
    int gridLength = (int) Math.ceil(maxY + 5);

    int[][] grid = new int[gridLength][gridWidth];
    for (int i = 0; i < gridLength; i++) {
      for (int j = 0; j < gridWidth; j++) {
        grid[i][j] = -1;
      }
    }

    for (ArrayList<Box> row : bin.space) {
      for (Box box : row) {
        if (box.getX() != -1 && box.getY() != -1) {
          int boxIndex = -1;
          for (int i = 0; i < sortedBoxes.size(); i++) {
            Box sortedBox = sortedBoxes.get(i);
            if (isApproximatelyEqual(sortedBox.getWidth(), box.getWidth()) &&
                isApproximatelyEqual(sortedBox.getLength(), box.getLength())) {
              boxIndex = i;
              break;
            }
          }

          int startX = (int) Math.floor(box.getX());
          int startY = (int) Math.floor(box.getY());
          int endX = (int) Math.ceil(box.getX() + box.getWidth());
          int endY = (int) Math.ceil(box.getY() + box.getLength());

          for (int y = startY; y < endY && y < gridLength; y++) {
            for (int x = startX; x < endX && x < gridWidth; x++) {
              grid[y][x] = boxIndex;
            }
          }
        }
      }
    }

    for (int y = 0; y < gridLength; y++) {
      for (int x = 0; x < gridWidth; x++) {
        if (grid[y][x] == -1) {
          System.out.print("- ");
        } else {
          System.out.print(grid[y][x] + " ");
        }
      }
      System.out.println();
    }
  }

  private Bin getBin() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter bin width: ");
    double binWidth = scanner.nextDouble();
    System.out.print("Enter bin length: ");
    double binLength = scanner.nextDouble();
    Bin bin = new Bin(binWidth, binLength);
    scanner.close();

    return bin;
  }

  public void binPackingCSVTestCase() {
    ArrayList<Box> boxes = readBoxesFromCSV("Lab06_test/boxSize1.csv");
    binPacking(boxes);

  }

  private ArrayList<Box> readBoxesFromCSV(String csvPath) {
    ArrayList<Box> boxes = new ArrayList<Box>();

    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(
          getClass().getClassLoader().getResourceAsStream(csvPath)));
      String line;

      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) {
          continue;
        }

        String[] values = line.split(",");
        if (values.length >= 2) {
          double width = Double.parseDouble(values[0].trim());
          double length = Double.parseDouble(values[1].trim());

          Box box = new Box(-1, -1, width, length);
          boxes.add(box);
        }
      }

      reader.close();

      System.out.println("Loaded " + boxes.size() + " boxes from " + csvPath);

    } catch (IOException e) {
      System.err.println("Error reading CSV file: " + e.getMessage());
    }

    return boxes;
  }

  private boolean isApproximatelyEqual(double a, double b) {
    return Math.abs(a - b) < EPSILON;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    solution.binPackingCSVTestCase();
  }
}
