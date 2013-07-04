package pixl;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class Frame extends java.awt.Frame implements Runnable, WindowListener {
  protected final Screen screen = new Screen();
  protected final List<Renderable> renderables = new ArrayList<Renderable>();
  protected InputListener input;

  public Frame(String title) {
    this.add(screen);
    this.addWindowListener(this);
    this.setTitle(title);
    this.setResizable(false);
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    screen.requestFocus();
  }

  public void addRenderable(Renderable renderable) {
    renderables.add(renderable);
  }

  public void setInputListener(InputListener input) {
    screen.removeKeyListener(this.input);
    screen.removeMouseListener(this.input);
    screen.removeMouseMotionListener(this.input);
    this.input = input;
    screen.addKeyListener(input);
    screen.addMouseListener(input);
    screen.addMouseMotionListener(input);
  }

  public InputListener getInputListener() {
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

  @Override
  public void windowActivated(WindowEvent arg0) {
  }

  @Override
  public void windowClosed(WindowEvent arg0) {
  }

  @Override
  public void windowClosing(WindowEvent arg0) {
    System.exit(0);
  }

  @Override
  public void windowDeactivated(WindowEvent arg0) {
  }

  @Override
  public void windowDeiconified(WindowEvent arg0) {
  }

  @Override
  public void windowIconified(WindowEvent arg0) {
  }

  @Override
  public void windowOpened(WindowEvent arg0) {
  }
}
