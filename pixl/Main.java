package pixl;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  
  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    
    Frame frame = new Frame("woot");
    frame.addRenderable(new Renderable() {
      
      @Override
      public void render(Screen screen) {
        Graphics graphics = screen.getGraphics();
        graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, 500, 500);
        screen.render(3, 64, 32);
      }
    });
    executor.submit(frame);
  }
}
