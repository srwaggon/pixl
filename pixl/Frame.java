package pixl;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import pixl.interaction.InputListener;
import pixl.interaction.SimpleWindowListener;

public class Frame extends java.awt.Frame implements Callable<Void> {

  private static final long serialVersionUID = -2075549855504940816L;
  protected Screen screen;
  protected List<Renderable> renderables = new ArrayList<Renderable>();

  public Frame(String title) {
    initFrame(title);
    setScreen(new Screen());
  }

  public void setScreen(Screen screen) {
    this.screen = screen;
    add(screen);
    pack();
    screen.requestFocus();
  }

  private void initFrame(String title) {
    setTitle(title);
    addWindowListener(getDefaultWindowListener());
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);
  }

  private SimpleWindowListener getDefaultWindowListener() {

    return new SimpleWindowListener() {

      @Override
      public void windowClosing(WindowEvent arg0) {
        super.windowClosing(arg0);
        System.exit(0);
      }
    };
  }

  public void addRenderable(Renderable renderable) {
    renderables.add(renderable);
  }

  public void addInputListener(InputListener input) {
    screen.addKeyListener(input);
    screen.addMouseListener(input);
    screen.addMouseMotionListener(input);
  }

  public void render() {
    for (Renderable renderable : renderables) {
      renderable.render(screen);
    }
    screen.render();
  }

  @Override
  public Void call() throws Exception {

    while (true) {
      render();
    }
  }
}
