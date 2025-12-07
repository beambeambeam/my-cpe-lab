package kmutt.cpe.algorithm.placeholder;

public class P00_ImageProcessing {

  public static Image brighten(Image img, int value) {
    int height = img.getHeight();
    int width = img.getWidth();
    Image result = new Image(height, width);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int newVal = Math.min(255, img.getPixels(y, x) + value);
        result.setPixels(y, x, newVal);
      }
    }

    return result;
  }

  public static Image darken(Image img, int value) {
    int height = img.getHeight();
    int width = img.getWidth();
    Image result = new Image(height, width);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int newVal = Math.max(0, img.getPixels(y, x) - value);
        result.setPixels(y, x, newVal);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    Image img = new Image("kmutt/cpe/algorithm/placeholder/image.png");

    Image brightened = brighten(img, 80);
    Image darkened = darken(img, 100);

    brightened.export("brighten.png");
    darkened.export("darken.png");

    System.out.println("Original pixel at (1,0): " + img.getPixels(1, 0));
    System.out.println("Brightened pixel at (1,0): " + brightened.getPixels(1, 0));
    System.out.println("Darkened pixel at (1,0): " + darkened.getPixels(1, 0));
  }
}
