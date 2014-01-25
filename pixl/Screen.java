package pixl;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen extends Canvas {

  private static final long serialVersionUID = -2226504463501471657L;
  final int WIDTH = 200;
  final int HEIGHT = WIDTH * 10 / 16;
  final int SCALE = 3;
  final int PUREBLACK = -16777216;

  private final BufferedImage screen = new BufferedImage(WIDTH, HEIGHT,
      BufferedImage.TYPE_INT_ARGB);
  private final int[] pixels = ((DataBufferInt) screen.getRaster()
      .getDataBuffer()).getData();
  protected Spritesheet sheet = new Spritesheet("sprites2.png", 16);

  @Override
  public final Dimension getPreferredSize() {
    return new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
  }

  public void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(1);
      return;
    }
    Graphics gfx = bs.getDrawGraphics();
    gfx.drawImage(screen, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
    gfx.dispose();
    bs.show();
  }

  public void render(int spriteID, int xOffset, int yOffset) {
    renderEachRow(spriteID, xOffset, yOffset);
  }

  private void renderEachRow(int spriteID, int xOffset, int yOffset) {
    for (int y = 0; y < sheet.TILESIZE; y++) {
      if (isValidSpriteSheetY(y, yOffset)) {
        renderRow(spriteID, xOffset, y, yOffset);
      }
    }
  }

  private void renderRow(int spriteID, int xOffset, int y, int yOffset) {
    for (int x = 0; x < sheet.TILESIZE; x++) {
      if (isValidSpriteSheetX(x, xOffset)) {
        copyPixelFromSpriteSheetToCanvas(spriteID, x, xOffset, y, yOffset);
      }
    }
  }

  private void copyPixelFromSpriteSheetToCanvas(int spriteID, int x,
      int xOffset, int y, int yOffset) {
    int sheetIndex = calculateSpriteSheetPixelIndex(spriteID, y, x);
    int canvasIndex = calculateCanvasPixelIndex(xOffset, yOffset, y, x);
    
    replaceCanvasPixelIfNotBlack(sheetIndex, canvasIndex);
  }

  private boolean isValidSpriteSheetY(int y, int yOffset) {
    return yOffset + y >= 0 && yOffset + y < HEIGHT;
  }

  private boolean isValidSpriteSheetX(int x, int xOffset) {
    return x + xOffset >= 0 && xOffset + x < WIDTH;
  }

  private int calculateSpriteSheetPixelIndex(int spriteID, int y, int x) {
    return x + y * sheet.width + getSpriteOffset(spriteID);
  }

  private int calculateCanvasPixelIndex(int xOffset, int yOffset, int y, int x) {
    return xOffset + x + (yOffset + y) * WIDTH;
  }

  private void replaceCanvasPixelIfNotBlack(int sheetIndex, int canvasIndex) {
    int colour = sheet.pixels[sheetIndex];
    if (colour != PUREBLACK) {
      pixels[canvasIndex] = colour;
    }
  }

  private int getSpriteOffset(int spriteID) {
    int spriteX = spriteID % sheet.PER_ROW;
    int spriteY = spriteID / sheet.PER_ROW;
    int spriteOffset = spriteX * sheet.TILESIZE + spriteY * sheet.TILESIZE
        * sheet.width;
    return spriteOffset;
  }
}
