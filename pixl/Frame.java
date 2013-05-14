package pixl;

public class Frame extends java.awt.Frame {
  public final Screen screen = new Screen();
  
  public Frame(String title) {
    add(screen);
    setTitle(title);
    setResizable(false);
    pack();
    setVisible(true);
    setLocationRelativeTo(null);
    screen.requestFocus();
  }
  
  public void addInputListener(InputHandler input) {
    screen.addKeyListener(input);
    screen.addMouseListener(input);
    screen.addMouseMotionListener(input);
  }
}
