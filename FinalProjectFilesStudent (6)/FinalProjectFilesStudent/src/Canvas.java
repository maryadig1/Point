import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Canvas extends JComponent implements ActionListener, KeyListener {
	// DEFAULT SERIAL NUMBER
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private Timer gameLoopTimer;
	private List<GameObject> gameObjectList;
	private int highlighted = 0;
	


	public Canvas() {
		// TASK 1: CREATE A LIST OF CHARACTERS THAT WILL APPEAR ON THE CANVAS
		gameObjectList = new LinkedList<GameObject>();

		// TASK 2: CREATE A WINDOW FOR THE APPLICATION
		frame = new JFrame("Animation Canvas");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);

		// TASK 3: CONSTRUCT A TIMER FOR GAME LOOP
		gameLoopTimer = new Timer(25, this);
		gameLoopTimer.start();
		
		setFocusTraversalKeysEnabled(false);
	    addKeyListener(this);
	    setFocusable(true);
	    requestFocusInWindow();

		// TASK 4: MAKE THE WINDOW VISIBLE
		frame.setVisible(true);

	}
	
	/**
	 * Adds GameObjects to the List, which are latter added to the Canvas
	 */
	public synchronized void addGameObject(GameObject sprite) {
		gameObjectList.add(sprite);
	}

	/**
	 * Draws the GameObject graphic onto the Canvas
	 */
	public synchronized void paint(Graphics g) {
		for (int i = 0; i < gameObjectList.size(); i++) {
			GameObject s = gameObjectList.get(i);
			s.draw(this, g);
			
			// Draw red highlight around selected object
			if (i == highlighted) {
				g.setColor(Color.RED);
				javax.swing.Icon icon = s.getCurrentImage();
				int borderSize = 5;
				g.drawRect(s.getX() - borderSize, s.getY() - borderSize, 
				          icon.getIconWidth() + (borderSize * 2), 
				          icon.getIconHeight() + (borderSize * 2));
			}
		}
	}
	
	

	public synchronized void actionPerformed(ActionEvent e) {
		for (GameObject gameObject : gameObjectList) {
			gameObject.move(this);
			gameObject.setImage();
		}
		repaint();
	}



	
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (gameObjectList.isEmpty()) {
			return;
		}
		
		// Handle TAB key to cycle through objects
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			// Deselect current object
			if (highlighted < gameObjectList.size()) {
				setObjectSelected(highlighted, false);
			}
			
			// Move to next object
			highlighted = (highlighted + 1) % gameObjectList.size();
			
			// Select new object
			setObjectSelected(highlighted, true);
			repaint();
			return;
		}
		
		// Handle arrow keys for selected object
		GameObject selected = gameObjectList.get(highlighted);
		int newDirection = Direction.NONE;
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			newDirection = Direction.UP;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			newDirection = Direction.DOWN;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			newDirection = Direction.LEFT;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			newDirection = Direction.RIGHT;
		}
		
		// Check if the object can move in this direction
		if (newDirection != Direction.NONE) {
			if (selected instanceof Type_A_GameObject) {
				Type_A_GameObject obj = (Type_A_GameObject) selected;
				if (obj.canMoveInDirection(newDirection)) {
					obj.setDirection(newDirection);
				}
			} else if (selected instanceof Type_B_GameObject) {
				Type_B_GameObject obj = (Type_B_GameObject) selected;
				if (obj.canMoveInDirection(newDirection)) {
					obj.setDirection(newDirection);
				}
			} else if (selected instanceof Type_C_GameObject) {
				Type_C_GameObject obj = (Type_C_GameObject) selected;
				if (obj.canMoveInDirection(newDirection)) {
					obj.setDirection(newDirection);
				}
			} else if (selected instanceof Type_D_GameObject) {
				Type_D_GameObject obj = (Type_D_GameObject) selected;
				if (obj.canMoveInDirection(newDirection)) {
					obj.setDirection(newDirection);
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// Don't reset direction on key release - let it continue moving
	}
	
	/**
	 * Helper method to set selection state of an object
	 */
	private void setObjectSelected(int index, boolean selected) {
		if (index >= 0 && index < gameObjectList.size()) {
			GameObject obj = gameObjectList.get(index);
			if (obj instanceof Type_A_GameObject) {
				((Type_A_GameObject) obj).setSelected(selected);
			} else if (obj instanceof Type_B_GameObject) {
				((Type_B_GameObject) obj).setSelected(selected);
			} else if (obj instanceof Type_C_GameObject) {
				((Type_C_GameObject) obj).setSelected(selected);
			} else if (obj instanceof Type_D_GameObject) {
				((Type_D_GameObject) obj).setSelected(selected);
			}
		}
	}
	
	/**
	 * Call this after adding all game objects to select the first one
	 */
	public void initializeSelection() {
		if (!gameObjectList.isEmpty()) {
			highlighted = 0;
			setObjectSelected(0, true);
		}
	}

}

