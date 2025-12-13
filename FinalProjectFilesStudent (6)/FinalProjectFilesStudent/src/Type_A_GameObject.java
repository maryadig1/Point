import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_A_GameObject extends GameObject {
	
	// Type A can only move Up and Down
	private boolean isSelected = false;
	private int autoDirection = Direction.DOWN;
	
	public Type_A_GameObject(int x, int y) {
		super(x, y);
		setDirection(Direction.DOWN);
		setVelocity(2);
		
		imageList = new LinkedList<>();
		imageList.add(new ImageIcon("images/Type_A_Up.png"));
		imageList.add(new ImageIcon("images/Type_A_Down.png"));
	}
	
	@Override
	public void move(Canvas c) {
		Icon icon = getCurrentImage();
		int iconHeight = icon.getIconHeight();
		int canvasHeight = (int)c.getSize().getHeight();
		
		// If not selected, move automatically
		if (!isSelected) {
			// Auto movement: bounce between top and bottom
			if (getY() <= 0) {
				autoDirection = Direction.DOWN;
			} else if (getY() + iconHeight >= canvasHeight) {
				autoDirection = Direction.UP;
			}
			setDirection(autoDirection);
		}
		
		// Move based on current direction
		switch (getDirection()) {
			case Direction.UP -> {
				setY(getY() - getVelocity());
				if (getY() < 0) {
					setY(0);
				}
			}
			case Direction.DOWN -> {
				setY(getY() + getVelocity());
				if (getY() + iconHeight > canvasHeight) {
					setY(canvasHeight - iconHeight);
				}
			}
			default -> { }
		}
	}
	
	@Override
	public void setImage() {
		switch (getDirection()) {
			case Direction.UP -> currentImage = 0;
			case Direction.DOWN -> currentImage = 1;
			default -> { }
		}
	}
	
	public void setSelected(boolean selected) {
		this.isSelected = selected;
		if (!selected) {
			// Return to auto movement
			setDirection(autoDirection);
		}
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	// Type A can only move Up or Down
	public boolean canMoveInDirection(int direction) {
		return direction == Direction.UP || direction == Direction.DOWN;
	}
}

