package pixl;

import java.util.ArrayList;
import java.util.List;

public class Frame extends java.awt.Frame implements Runnable {
  private final Screen screen = new Screen();
  private final List<Renderable> renderables = new ArrayList<Renderable>();
  private final InputHandler input = new InputHandler();
  
  public Frame(String title) {
    add(screen);
    setTitle(title);
    setResizable(false);
    pack();
    setVisible(true);
    setLocationRelativeTo(null);
    screen.requestFocus();
  }
  
  public void addRenderable(Renderable renderable) {
    renderables.add(renderable);
  }
  
  public void addInputHandler(InputHandler input) {
    screen.addKeyListener(input);
    screen.addMouseListener(input);
    screen.addMouseMotionListener(input);
  }
  
  public InputHandler getInputHandler() {
    return input;
  }
  
  public void render() {
    for (Renderable r : renderables) {
      r.render(screen);
    }
    screen.render();
  }
  
  @Override
  public void run() {
    while (true) {
      render();
      try {
        Thread.sleep(2);
      } catch (InterruptedException ioe) {
        ioe.printStackTrace();
      }
    }
  }
  
  public void start() {
    new Thread(this).start();
  }
}
