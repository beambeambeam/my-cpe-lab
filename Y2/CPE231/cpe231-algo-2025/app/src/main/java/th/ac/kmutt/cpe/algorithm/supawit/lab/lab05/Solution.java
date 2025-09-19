package th.ac.kmutt.cpe.algorithm.supawit.lab.lab05;

import java.util.*;
import java.io.*;

public class Solution {
  public static class Delivery {
    private ArrayList<String> csv_paths;
    public ArrayList<FeeSheet> fees;
    public int weight_limit;

    public Delivery(ArrayList<String> csv_paths, int weight_limit) {
      this.csv_paths = csv_paths;
      this.fees = new ArrayList<>();
      this.weight_limit = weight_limit;
      scanner();
    }

    public static class FeeObject {
      private Map<String, String> fields;

      public FeeObject() {
        this.fields = new HashMap<>();
      }

      public void setField(String key, String value) {
        fields.put(key, value);
      }

      public String getField(String key) {
        return fields.get(key);
      }

      public String get(String fieldName) {
        return fields.get(fieldName);
      }

      public String getValue(String headerName) {
        return fields.get(headerName);
      }

      public Map<String, String> getAllFields() {
        return new HashMap<>(fields);
      }
    }

    public static class FeeSheet {
      String label;
      ArrayList<FeeObject> fees;
      String[] headers;

      public FeeSheet(String label) {
        this.label = label;
        this.fees = new ArrayList<>();
      }

      public String getLabel() {
        return label;
      }

      public ArrayList<FeeObject> getFees() {
        return fees;
      }

      public void setHeaders(String[] headers) {
        this.headers = headers;
      }

      public String[] getHeaders() {
        return headers;
      }

      public FeeObject get(int index) {
        if (index >= 0 && index < fees.size()) {
          return fees.get(index);
        }
        return null;
      }

      public int size() {
        return fees.size();
      }
    }

    public FeeSheet findFees(String label) {
      if (this.fees == null)
        return null;
      for (FeeSheet sheet : this.fees) {
        if (sheet != null && sheet.getLabel() != null && sheet.getLabel().equalsIgnoreCase(label)) {
          return sheet;
        }
      }
      return null;
    }

    public int calculateCost(String deliveryType, int weight, ArrayList<String> destinations) {
      FeeSheet feeSheet = findFees(deliveryType);
      if (feeSheet == null) {
        System.out.println("No fee sheet found for delivery type: " + deliveryType);
        return 0;
      }

      int cost = 0;

      if (deliveryType.equalsIgnoreCase("Domestic")) {
        for (int i = 0; i < feeSheet.size(); i++) {
          FeeObject fee = feeSheet.get(i);
          int fromWeight = Integer.parseInt(fee.getField("From"));
          int toWeight = Integer.parseInt(fee.getField("To"));

          if (weight >= fromWeight && weight <= toWeight) {
            cost = Integer.parseInt(fee.getField("Cost"));
            break;
          }
        }
      } else if (deliveryType.equalsIgnoreCase("World")) {
        String[] zones = destinations.get(0).split(",");
        int totalCost = 0;
        for (String zone : zones) {
          zone = zone.trim();
          for (int i = 0; i < feeSheet.size(); i++) {
            FeeObject fee = feeSheet.get(i);
            int fromWeight = Integer.parseInt(fee.getField("From"));
            int toWeight = Integer.parseInt(fee.getField("To"));

            if (weight >= fromWeight && weight <= toWeight) {
              String costStr = fee.getField(zone);
              if (costStr != null && !costStr.trim().isEmpty()) {
                totalCost += Integer.parseInt(costStr);
              } else {
                System.out.println("No cost found for zone: " + zone);
              }
              break;
            }
          }
        }
        cost = totalCost;
      }

      System.out.println("Shipping cost: " + cost + " baht");
      return cost;
    }

    public void scanner() {
      for (String csvPath : csv_paths) {
        try {
          parseCSVFile(csvPath);
        } catch (IOException e) {
          System.err.println("Error reading CSV file: " + csvPath + " - " + e.getMessage());
        }
      }
    }

    private void parseCSVFile(String csvPath) throws IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(
          getClass().getClassLoader().getResourceAsStream(csvPath)));
      String line;
      String[] headers = null;
      FeeSheet currentSheet = null;

      String fileName = csvPath.substring(csvPath.lastIndexOf("/") + 1);
      String label = fileName.replace(".csv", "").replace("EMS_", "");
      currentSheet = new FeeSheet(label);

      boolean isFirstLine = true;

      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty())
          continue;

        String[] values = line.split(",");

        if (isFirstLine) {
          headers = values;
          if (headers.length > 0 && headers[0].startsWith("\uFEFF")) {
            headers[0] = headers[0].substring(1);
          }
          currentSheet.setHeaders(headers);
          isFirstLine = false;
        } else {
          FeeObject feeObj = new FeeObject();

          for (int i = 0; i < values.length && i < headers.length; i++) {
            String header = headers[i].trim();
            if (header.startsWith("\uFEFF")) {
              header = header.substring(1);
            }
            feeObj.setField(header, values[i].trim());
          }

          currentSheet.getFees().add(feeObj);
        }
      }

      reader.close();

      if (currentSheet != null) {
        this.fees.add(currentSheet);
      }
    }
  }

  public void scanner() {
    ArrayList<String> csvPaths = new ArrayList<>();
    csvPaths.add("fee/EMS_Domestic.csv");
    csvPaths.add("fee/EMS_World.csv");

    Delivery delivery = new Delivery(csvPaths, 30000);
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter Delivery Type: ");
    String deliveryType = scanner.nextLine().trim();

    boolean found = false;
    for (Delivery.FeeSheet sheet : delivery.fees) {
      if (sheet.label.equalsIgnoreCase(deliveryType)) {
        found = true;
        break;
      }
    }
    if (!found) {
      System.out.println("No fee sheet found for delivery type: " + deliveryType);
      scanner.close();
      return;
    }

    System.out.print("Enter Parcel Weight (grams): ");
    int weight = Integer.parseInt(scanner.nextLine().trim());

    if (weight < 0) {
      System.out.println("Error: Weight cannot be less than 0 grams.");
      scanner.close();
      return;
    }
    if (weight > delivery.weight_limit) {
      System.out.println("Error: Weight exceeds the maximum limit of " + delivery.weight_limit + " grams.");
      scanner.close();
      return;
    }

    ArrayList<String> destinations = new ArrayList<>();
    Delivery.FeeSheet feeSheet = null;

    for (Delivery.FeeSheet sheet : delivery.fees) {
      if (sheet.label.equalsIgnoreCase(deliveryType)) {
        feeSheet = sheet;
        break;
      }
    }

    if (feeSheet != null && feeSheet.getHeaders() != null && feeSheet.getHeaders().length > 3) {
      System.out.print("Enter Destination Zone (e.g., Zone 1, Zone 2, ...): ");
      String zone = scanner.nextLine().trim();
      destinations.add(zone);
    } else {
      destinations.add("Domestic");
    }

    scanner.close();

    delivery.calculateCost(deliveryType, weight, destinations);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    solution.scanner();
  }
}
