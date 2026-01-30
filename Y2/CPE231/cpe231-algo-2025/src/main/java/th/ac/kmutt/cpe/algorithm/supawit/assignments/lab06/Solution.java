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

    System.out.println("\n" + "=".repeat(60));
    System.out.println("BIN PACKING ALGORITHM COMPARISON");
    System.out.println("=".repeat(60));

    System.out.println("\n[1/4] Running Brute Force Algorithm...");
    long startTime = System.currentTimeMillis();
    BruteForce bruteForce = new BruteForce();
    Bin bruteResult = bruteForce.solve(boxes, bin);
    long bruteTime = System.currentTimeMillis() - startTime;

    System.out.println("[2/4] Running Next-Fit Algorithm...");
    startTime = System.currentTimeMillis();
    NextFit nextFit = new NextFit();
    Bin nextFitResult = nextFit.solve(boxes, bin);
    long nextFitTime = System.currentTimeMillis() - startTime;

    System.out.println("[3/4] Running First-Fit Decreasing Algorithm...");
    startTime = System.currentTimeMillis();
    FirstFitDecreasing ffd = new FirstFitDecreasing();
    Bin ffdResult = ffd.solve(boxes, bin);
    long ffdTime = System.currentTimeMillis() - startTime;

    System.out.println("[4/4] Running Code Golf FFD Algorithm...");
    startTime = System.currentTimeMillis();
    FFDCodeGolf codeGolf = new FFDCodeGolf();
    Bin codeGolfResult = codeGolf.solve(boxes, bin);
    long codeGolfTime = System.currentTimeMillis() - startTime;

    System.out.println("\n" + "=".repeat(60));
    System.out.println("RESULTS SUMMARY");
    System.out.println("=".repeat(60));
    System.out.printf("%-20s | %6s | %8s | %10s%n", "Algorithm", "Boxes", "Util%", "Time(ms)");
    System.out.println("-".repeat(50));

    System.out.printf("%-20s | %6d | %7.1f%% | %10d%n",
        "Brute Force", bruteResult.getPlacedBoxes().size(), calculateUtilization(bruteResult), bruteTime);
    System.out.printf("%-20s | %6d | %7.1f%% | %10d%n",
        "Next-Fit", nextFitResult.getPlacedBoxes().size(), calculateUtilization(nextFitResult), nextFitTime);
    System.out.printf("%-20s | %6d | %7.1f%% | %10d%n",
        "First-Fit Decreasing", ffdResult.getPlacedBoxes().size(), calculateUtilization(ffdResult), ffdTime);
    System.out.printf("%-20s | %6d | %7.1f%% | %10d%n",
        "Code Golf FFD", codeGolfResult.getPlacedBoxes().size(), calculateUtilization(codeGolfResult), codeGolfTime);

    System.out.println("-".repeat(60));
    System.out.printf("Total boxes to place: %d%n", boxes.size());
    System.out.printf("Bin dimensions: %.1f x %.1f%n", bin.getWidth(), bin.getLength());

    System.out.println("\n" + "=".repeat(60));
    System.out.println("PERFORMANCE ANALYSIS");
    System.out.println("=".repeat(60));

    String bestAlgorithm = "";
    int bestBoxes = 0;
    double bestUtil = 0;

    if (bruteResult.getPlacedBoxes().size() >= nextFitResult.getPlacedBoxes().size() &&
        bruteResult.getPlacedBoxes().size() >= ffdResult.getPlacedBoxes().size() &&
        bruteResult.getPlacedBoxes().size() >= codeGolfResult.getPlacedBoxes().size()) {
      bestAlgorithm = "Brute Force";
      bestBoxes = bruteResult.getPlacedBoxes().size();
      bestUtil = calculateUtilization(bruteResult);
    } else if (nextFitResult.getPlacedBoxes().size() >= ffdResult.getPlacedBoxes().size() &&
        nextFitResult.getPlacedBoxes().size() >= codeGolfResult.getPlacedBoxes().size()) {
      bestAlgorithm = "Next-Fit";
      bestBoxes = nextFitResult.getPlacedBoxes().size();
      bestUtil = calculateUtilization(nextFitResult);
    } else if (ffdResult.getPlacedBoxes().size() >= codeGolfResult.getPlacedBoxes().size()) {
      bestAlgorithm = "First-Fit Decreasing";
      bestBoxes = ffdResult.getPlacedBoxes().size();
      bestUtil = calculateUtilization(ffdResult);
    } else {
      bestAlgorithm = "Code Golf FFD";
      bestBoxes = codeGolfResult.getPlacedBoxes().size();
      bestUtil = calculateUtilization(codeGolfResult);
    }

    System.out.printf("🏆 Best Algorithm: %s%n", bestAlgorithm);
    System.out.printf("📦 Most boxes placed: %d%n", bestBoxes);
    System.out.printf("📊 Highest utilization: %.1f%%%n", bestUtil);

    long fastestTime = Math.min(Math.min(Math.min(bruteTime, nextFitTime), ffdTime), codeGolfTime);
    if (fastestTime == bruteTime) {
      System.out.printf("⚡ Fastest execution: Brute Force (%d ms)%n", bruteTime);
    } else if (fastestTime == nextFitTime) {
      System.out.printf("⚡ Fastest execution: Next-Fit (%d ms)%n", nextFitTime);
    } else if (fastestTime == ffdTime) {
      System.out.printf("⚡ Fastest execution: First-Fit Decreasing (%d ms)%n", ffdTime);
    } else {
      System.out.printf("⚡ Fastest execution: Code Golf FFD (%d ms)%n", codeGolfTime);
    }
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

  private class NextFit {
    public Bin solve(ArrayList<Box> boxes, Bin originalBin) {
      Bin resultBin = new Bin(originalBin.getWidth(), originalBin.getLength());

      for (Box box : boxes) {
        if (!placeBoxNextFit(box, resultBin)) {
          System.out.println("Box " + box.getWidth() + "x" + box.getLength() + " could not be placed");
        }
      }

      return resultBin;
    }

    private boolean placeBoxNextFit(Box box, Bin bin) {

      double startX = 0;
      double startY = 0;

      if (!bin.getPlacedBoxes().isEmpty()) {
        Box lastBox = bin.getPlacedBoxes().get(bin.getPlacedBoxes().size() - 1);
        startX = lastBox.getX() + lastBox.getWidth();
        startY = lastBox.getY();
      }

      for (double x = startX; x <= bin.getWidth() - box.getWidth(); x += 0.5) {
        for (double y = startY; y <= bin.getLength() - box.getLength(); y += 0.5) {
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

  // Code Golf FFD - Total characters: 487 (including spaces and newlines)
  private class FFDCodeGolf {
    public Bin solve(ArrayList<Box> boxes, Bin b) {
      Bin r = new Bin(b.getWidth(), b.getLength());
      boxes.stream().sorted((a, c) -> Double.compare(c.getWidth() * c.getLength(), a.getWidth() * a.getLength()))
          .forEach(box -> {
            for (double x = 0; x <= r.getWidth() - box.getWidth(); x += 0.5)
              for (double y = 0; y <= r.getLength() - box.getLength(); y += 0.5) {
                Box p = new Box(x, y, box.getWidth(), box.getLength());
                if (isValidPlacement(p, r.getPlacedBoxes(), r)) {
                  r.getPlacedBoxes().add(p);
                  return;
                }
              }
          });
      return r;
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
