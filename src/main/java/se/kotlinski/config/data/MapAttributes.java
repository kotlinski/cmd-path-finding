package se.kotlinski.config.data;

public class MapAttributes {
  private final int height;
  private final int width;

  public MapAttributes(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
