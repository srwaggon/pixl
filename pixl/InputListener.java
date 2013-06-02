package pixl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface InputListener extends MouseMotionListener, KeyListener,
    MouseListener {
  public void keyPressed(KeyEvent event);
  
  public void keyReleased(KeyEvent event);
  
  public void keyTyped(KeyEvent e);
  
  public void mouseClicked(MouseEvent event);
  
  public void mouseDragged(MouseEvent event);
  
  public void mouseEntered(MouseEvent event);
  
  public void mouseExited(MouseEvent event);
  
  public void mouseMoved(MouseEvent event);
  
  public void mousePressed(MouseEvent event);
  
  public void mouseReleased(MouseEvent event);
}
