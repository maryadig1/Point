import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_D_GameObject extends GameObject {
	
	// Type D can move in all directions
	private boolean isSelected = false;
	private int autoDirection = Direction.UP;

  public Type_D_GameObject(int x, int y) {
    super(x, y);
    setDirection(Direction.UP);
    setVelocity(2);
    
    imageList = new LinkedList<>();
    imageList.add(new ImageIcon("images/Type_D_Up.png"));
    imageList.add(new ImageIcon("images/Type_D_Down.png"));
    imageList.add(new ImageIcon("images/Type_D_Left.png"));
    imageList.add(new ImageIcon("images/Type_D_Right.png"));
    
  }

  @Override
  public void move(Canvas c) {
    Icon icon = getCurrentImage();

    int  iconHeight   = icon.getIconHeight();
    int  iconWidth    = icon.getIconWidth();
    int  canvasHeight = (int)c.getSize().getHeight();
    int  canvasWidth  = (int)c.getSize().getWidth();
    
    // If not selected, move automatically
    if (!isSelected) {
    	// Auto movement: bounce around canvas
    	if (getX() <= 0 && autoDirection == Direction.LEFT) {
    		autoDirection = Direction.RIGHT;
    	} else if (getX() + iconWidth >= canvasWidth && autoDirection == Direction.RIGHT) {
    		autoDirection = Direction.LEFT;
    	} else if (getY() <= 0 && autoDirection == Direction.UP) {
    		autoDirection = Direction.DOWN;
    	} else if (getY() + iconHeight >= canvasHeight && autoDirection == Direction.DOWN) {
    		autoDirection = Direction.UP;
    	}
    	setDirection(autoDirection);
    }
    
    // Move based on current direction
    switch (getDirection()) {
      case Direction.UP:
        setY(getY() - getVelocity());
        if (getY() < 0) {
          setY(0);
        }
        break;
      case Direction.DOWN:
        setY(getY() + getVelocity());
        if (getY() + iconHeight > canvasHeight) {
          setY((int)(canvasHeight - iconHeight));
        }
        break;
      case Direction.LEFT:
        setX(getX() - getVelocity());
        if (getX() < 0) {
          setX(0);
        }
        break;
      case Direction.RIGHT:
        setX(getX() + getVelocity());
        if (getX() + iconWidth > canvasWidth) {
          setX((int)(canvasWidth - iconWidth));
        }
        break;
	default:
		break;
    }

  }

  //SPECIFY THE IMAGE TO DISPLAY
  //   USED FOR ANIMATION
  @Override
  public void setImage() {
	    switch (getDirection()) {
	      case Direction.UP:
	        currentImage = 0;
	        break;
	      case Direction.DOWN:
	        currentImage = 1;
	        break;
	      case Direction.LEFT:
	        currentImage = 2;
	        break;
	      case Direction.RIGHT:
	        currentImage = 3;
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
	
	// Type D can move in all directions
	public boolean canMoveInDirection(int direction) {
		return direction == Direction.UP || direction == Direction.DOWN || 
		       direction == Direction.LEFT || direction == Direction.RIGHT;
	}

}

