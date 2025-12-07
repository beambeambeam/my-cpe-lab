package kmutt.cpe.algorithm.placeholder;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image {
  private int height;
  private int width;
  private int[][] pixels;

  public Image(String filePath) {
    try {
      File file = new File(filePath);
      BufferedImage img = ImageIO.read(file);
      Raster raster = img.getRaster();

      this.height = img.getHeight();
      this.width = img.getWidth();

      this.pixels = new int[this.height][this.width];

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          pixels[y][x] = raster.getSample(x, y, 0);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Image(int height, int width) {
    this.height = height;
    this.width = width;
    this.pixels = new int[height][width];
  }

  public void export(String fileName) {
    BufferedImage img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_BYTE_GRAY);
    WritableRaster raster = img.getRaster();

    for (int y = 0; y < this.height; y++) {
      for (int x = 0; x < this.width; x++) {
        raster.setSample(x, y, 0, this.pixels[y][x]);
      }
    }

    try {
      ImageIO.write(img, "png", new File("kmutt/cpe/algorithm/placeholder/" + fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public int[][] getPixels() {
    return pixels;
  }

  public int getPixels(int y, int x) {
    return pixels[y][x];
  }

  public void setPixels(int y, int x, int val) {
    this.pixels[y][x] = val;
  }

}
