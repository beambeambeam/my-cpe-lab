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

  public static void main(String[] args) {
    Solution solution = new Solution();
    solution.binPackingCSVTestCase();
  }
}
