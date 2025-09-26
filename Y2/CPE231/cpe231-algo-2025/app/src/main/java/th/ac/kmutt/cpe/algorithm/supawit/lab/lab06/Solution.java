package th.ac.kmutt.cpe.algorithm.supawit.lab.lab06;

import java.util.*;
import java.io.*;

public class Solution {

  private class Box {
    public double x, y; // Position
    public double width, length; // Dimensions

    public Box(double x, double y, double w, double h) {
      this.x = x;
      this.y = y;
      this.width = w;
      this.length = h;
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
    public double width, length; // Bin size
    public ArrayList<Box> placedBoxes; // Simple list of placed boxes

    public Bin(double w, double h) {
      this.width = w;
      this.length = h;
      this.placedBoxes = new ArrayList<>();
    }

    public double getWidth() {
      return width;
    }

    public double getLength() {
      return length;
    }

    public ArrayList<Box> getPlacedBoxes() {
      return placedBoxes;
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
    ArrayList<Box> boxes = readBoxesFromCSV("Lab06_test/boxSize2.csv");
    Bin bin = getBin();

    // Test Brute Force Algorithm
    System.out.println("\n=== BRUTE FORCE ALGORITHM ===");
    long startTime = System.currentTimeMillis();
    BruteForce bruteForce = new BruteForce();
    Bin bruteResult = bruteForce.solve(boxes, bin);
    long bruteTime = System.currentTimeMillis() - startTime;

    System.out.println("Brute Force Results:");
    System.out.println("Placed " + bruteResult.getPlacedBoxes().size() + " out of " + boxes.size() + " boxes");
    System.out.println("Bin utilization: " + calculateUtilization(bruteResult) + "%");
    System.out.println("Execution time: " + bruteTime + " ms");

    System.out.println("\n=== FIRST-FIT DECREASING ALGORITHM ===");
    startTime = System.currentTimeMillis();
    FirstFitDecreasing ffd = new FirstFitDecreasing();
    Bin ffdResult = ffd.solve(boxes, bin);
    long ffdTime = System.currentTimeMillis() - startTime;

    System.out.println("First-Fit Decreasing Results:");
    System.out.println("Placed " + ffdResult.getPlacedBoxes().size() + " out of " + boxes.size() + " boxes");
    System.out.println("Bin utilization: " + calculateUtilization(ffdResult) + "%");
    System.out.println("Execution time: " + ffdTime + " ms");

    System.out.println("\n=== COMPARISON ===");
    System.out.println("Brute Force: " + bruteResult.getPlacedBoxes().size() + " boxes, "
        + calculateUtilization(bruteResult) + "% utilization, " + bruteTime + " ms");
    System.out.println("FFD: " + ffdResult.getPlacedBoxes().size() + " boxes, " + calculateUtilization(ffdResult)
        + "% utilization, " + ffdTime + " ms");
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

          Box box = new Box(-1, -1, width, length); // -1, -1 means not placed yet
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

  private class BruteForce {
    public Bin solve(ArrayList<Box> boxes, Bin originalBin) {
      Bin bestBin = new Bin(originalBin.getWidth(), originalBin.getLength());
      solveRecursive(boxes, 0, bestBin, new ArrayList<>());
      return bestBin;
    }

    private void solveRecursive(ArrayList<Box> remainingBoxes, int index, Bin currentBin,
        ArrayList<Box> currentPlacement) {
      if (index >= remainingBoxes.size()) {
        if (currentPlacement.size() > currentBin.getPlacedBoxes().size()) {
          currentBin.placedBoxes.clear();
          currentBin.placedBoxes.addAll(currentPlacement);
        }
        return;
      }

      Box currentBox = remainingBoxes.get(index);

      for (double x = 0; x <= currentBin.getWidth() - currentBox.getWidth(); x += 0.5) {
        for (double y = 0; y <= currentBin.getLength() - currentBox.getLength(); y += 0.5) {
          Box placedBox = new Box(x, y, currentBox.getWidth(), currentBox.getLength());

          if (isValidPlacement(placedBox, currentPlacement, currentBin)) {
            currentPlacement.add(placedBox);
            solveRecursive(remainingBoxes, index + 1, currentBin, currentPlacement);
            currentPlacement.remove(currentPlacement.size() - 1);
          }
        }
      }

      solveRecursive(remainingBoxes, index + 1, currentBin, currentPlacement);
    }
  }

  private class FirstFitDecreasing {
    public Bin solve(ArrayList<Box> boxes, Bin originalBin) {
      ArrayList<Box> sortedBoxes = new ArrayList<>(boxes);
      sortedBoxes.sort((b1, b2) -> {
        double area1 = b1.getWidth() * b1.getLength();
        double area2 = b2.getWidth() * b2.getLength();
        return Double.compare(area2, area1);
      });

      Bin resultBin = new Bin(originalBin.getWidth(), originalBin.getLength());

      for (Box box : sortedBoxes) {
        if (!placeBoxFirstFit(box, resultBin)) {
          System.out.println("Box " + box.getWidth() + "x" + box.getLength() + " could not be placed");
        }
      }

      return resultBin;
    }

    private boolean placeBoxFirstFit(Box box, Bin bin) {
      for (double x = 0; x <= bin.getWidth() - box.getWidth(); x += 0.5) {
        for (double y = 0; y <= bin.getLength() - box.getLength(); y += 0.5) {
          Box placedBox = new Box(x, y, box.getWidth(), box.getLength());

          if (isValidPlacement(placedBox, bin.getPlacedBoxes(), bin)) {
            bin.getPlacedBoxes().add(placedBox);
            return true;
          }
        }
      }
      return false;
    }

  }

  private double calculateUtilization(Bin bin) {
    double totalBoxArea = 0;
    for (Box box : bin.getPlacedBoxes()) {
      totalBoxArea += box.getWidth() * box.getLength();
    }
    double binArea = bin.getWidth() * bin.getLength();
    return (totalBoxArea / binArea) * 100;
  }

  private boolean isValidPlacement(Box newBox, ArrayList<Box> existingBoxes, Bin bin) {
    if (newBox.getX() < 0 || newBox.getY() < 0 ||
        newBox.getX() + newBox.getWidth() > bin.getWidth() ||
        newBox.getY() + newBox.getLength() > bin.getLength()) {
      return false;
    }

    for (Box existingBox : existingBoxes) {
      if (boxesOverlap(newBox, existingBox)) {
        return false;
      }
    }

    return true;
  }

  private boolean boxesOverlap(Box box1, Box box2) {
    return !(box1.getX() + box1.getWidth() <= box2.getX() ||
        box2.getX() + box2.getWidth() <= box1.getX() ||
        box1.getY() + box1.getLength() <= box2.getY() ||
        box2.getY() + box2.getLength() <= box1.getY());
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    solution.binPackingCSVTestCase();
  }
}
