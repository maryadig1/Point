import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_C_GameObject extends GameObject {
	
	// Type C can only move Left and Right
	private boolean isSelected = false;
	private int autoDirection = Direction.RIGHT;
	
	public Type_C_GameObject(int x, int y) {
		super(x, y);
		setDirection(Direction.RIGHT);
		setVelocity(2);
		
		imageList = new LinkedList<>();
		imageList.add(new ImageIcon("images/Type_C_Left.png"));
		imageList.add(new ImageIcon("images/Type_C_Right.png"));
	}
	
	@Override
	public void move(Canvas c) {
		Icon icon = getCurrentImage();
		int iconHeight = icon.getIconHeight();
		int iconWidth = icon.getIconWidth();
		int canvasHeight = (int)c.getSize().getHeight();
		int canvasWidth = (int)c.getSize().getWidth();
		
		// If not selected, move automatically
		if (!isSelected) {
			// Auto movement: bounce between left and right
			if (getX() <= 0) {
				autoDirection = Direction.RIGHT;
			} else if (getX() + iconWidth >= canvasWidth) {
				autoDirection = Direction.LEFT;
			}
			setDirection(autoDirection);
		}
		
		// Move based on current direction
		switch (getDirection()) {
			case Direction.LEFT:
				setX(getX() - getVelocity());
				if (getX() < 0) {
					setX(0);
				}
				break;
			case Direction.RIGHT:
				setX(getX() + getVelocity());
				if (getX() + iconWidth > canvasWidth) {
					setX(canvasWidth - iconWidth);
				}
				break;
			default:
				break;
		}
	}
	
	@Override
	public void setImage() {
		switch (getDirection()) {
			case Direction.LEFT:
				currentImage = 0;
				break;
			case Direction.RIGHT:
				currentImage = 1;
				break;
			default:
				break;
		}
	}
	
	public void setSelected(boolean selected) {
		this.isSelected = selected;
		if (!selected) {
			setDirection(autoDirection);
		}
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	// Type C can only move Left or Right
	public boolean canMoveInDirection(int direction) {
		return direction == Direction.LEFT || direction == Direction.RIGHT;
	}
}

