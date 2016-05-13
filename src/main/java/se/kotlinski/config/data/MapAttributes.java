package se.kotlinski.config.data;

public class MapAttributes {
  private final int height;
  private final int width;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    MapAttributes that = (MapAttributes) o;

    if (height != that.height) {
      return false;
    }
    if (width != that.width) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = height;
    result = 31 * result + width;
    return result;
  }

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
