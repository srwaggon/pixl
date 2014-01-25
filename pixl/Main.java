package pixl;

import java.awt.Color;
import java.awt.Graphics;

public class Main {
  
  public static void main(String[] args) {
    Frame f = new Frame("woot");
    f.addRenderable(new Renderable() {
      
      @Override
      public void render(Screen screen) {
        Graphics graphics = screen.getGraphics();
        graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, 500, 500);
        screen.render(3, 64, 32);
      }
    });
    f.start();
  }
}
