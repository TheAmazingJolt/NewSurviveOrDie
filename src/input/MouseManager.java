package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener{

	private boolean wheelUp, wheelDown;
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	
	public MouseManager() {
		
	}
	
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	public boolean isRightPressed() {
		return rightPressed;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() == -1) {
			wheelUp = true;
			wheelDown = false;
		}else if(e.getWheelRotation() == 1) {
			wheelDown = true;
			wheelUp = false;
		}
	}

	public boolean isWheelUp(MouseWheelEvent e) {
		return wheelUp;
	}

	public void setWheelUp(boolean wheelUp) {
		this.wheelUp = wheelUp;
	}

	public boolean isWheelDown() {
		return wheelDown;
	}

	public void setWheelDown(boolean wheelDown) {
		this.wheelDown = wheelDown;
	}

}
