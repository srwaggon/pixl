package pixl.interaction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface InputListener extends MouseMotionListener, KeyListener,
    MouseListener {
  void keyPressed(KeyEvent event);
  
  void keyReleased(KeyEvent event);
  
  void keyTyped(KeyEvent e);
  
  void mouseClicked(MouseEvent event);
  
  void mouseDragged(MouseEvent event);
  
  void mouseEntered(MouseEvent event);
  
  void mouseExited(MouseEvent event);
  
  void mouseMoved(MouseEvent event);
  
  void mousePressed(MouseEvent event);
  
  void mouseReleased(MouseEvent event);
}
