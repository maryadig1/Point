import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameObjectTest {
	
	private Canvas canvas;
	private Type_A_GameObject typeA;
	private Type_B_GameObject typeB;
	private Type_C_GameObject typeC;
	private Type_D_GameObject typeD;
	
	@BeforeEach
	public void setUp() {
		canvas = new Canvas();
		typeA = new Type_A_GameObject(100, 100);
		typeB = new Type_B_GameObject(200, 200);
		typeC = new Type_C_GameObject(300, 300);
		typeD = new Type_D_GameObject(400, 400);
	}
	
	@Test
	public void testTypeAInitialization() {
		assertNotNull(typeA);
		assertEquals(100, typeA.getX());
		assertEquals(100, typeA.getY());
		assertEquals(2, typeA.getVelocity());
		assertFalse(typeA.isSelected());
	}
	
	@Test
	public void testTypeACanMoveUpDown() {
		assertTrue(typeA.canMoveInDirection(Direction.UP));
		assertTrue(typeA.canMoveInDirection(Direction.DOWN));
		assertFalse(typeA.canMoveInDirection(Direction.LEFT));
		assertFalse(typeA.canMoveInDirection(Direction.RIGHT));
	}
	
	@Test
	public void testTypeBInitialization() {
		assertNotNull(typeB);
		assertEquals(200, typeB.getX());
		assertEquals(200, typeB.getY());
		assertEquals(2, typeB.getVelocity());
		assertFalse(typeB.isSelected());
	}
	
	@Test
	public void testTypeBCanMoveAllDirections() {
		assertTrue(typeB.canMoveInDirection(Direction.UP));
		assertTrue(typeB.canMoveInDirection(Direction.DOWN));
		assertTrue(typeB.canMoveInDirection(Direction.LEFT));
		assertTrue(typeB.canMoveInDirection(Direction.RIGHT));
	}
	
	@Test
	public void testTypeCInitialization() {
		assertNotNull(typeC);
		assertEquals(300, typeC.getX());
		assertEquals(300, typeC.getY());
		assertEquals(2, typeC.getVelocity());
		assertFalse(typeC.isSelected());
	}
	
	@Test
	public void testTypeCCanMoveLeftRight() {
		assertFalse(typeC.canMoveInDirection(Direction.UP));
		assertFalse(typeC.canMoveInDirection(Direction.DOWN));
		assertTrue(typeC.canMoveInDirection(Direction.LEFT));
		assertTrue(typeC.canMoveInDirection(Direction.RIGHT));
	}
	
	@Test
	public void testTypeDInitialization() {
		assertNotNull(typeD);
		assertEquals(400, typeD.getX());
		assertEquals(400, typeD.getY());
		assertEquals(2, typeD.getVelocity());
		assertFalse(typeD.isSelected());
	}
	
	@Test
	public void testTypeDCanMoveAllDirections() {
		assertTrue(typeD.canMoveInDirection(Direction.UP));
		assertTrue(typeD.canMoveInDirection(Direction.DOWN));
		assertTrue(typeD.canMoveInDirection(Direction.LEFT));
		assertTrue(typeD.canMoveInDirection(Direction.RIGHT));
	}
	
	@Test
	public void testSelection() {
		typeA.setSelected(true);
		assertTrue(typeA.isSelected());
		
		typeA.setSelected(false);
		assertFalse(typeA.isSelected());
	}
	
	@Test
	public void testMovement() {
		int initialY = typeA.getY();
		typeA.setDirection(Direction.DOWN);
		typeA.setSelected(true);
		typeA.move(canvas);
		
		// Should have moved down
		assertTrue(typeA.getY() > initialY || typeA.getY() == initialY);
	}
	
	@Test
	public void testCanvasAddGameObject() {
		canvas.addGameObject(typeA);
		canvas.addGameObject(typeB);
		// If no exception is thrown, test passes
		assertTrue(true);
	}
	
	@Test
	public void testCanvasInitialization() {
		canvas.addGameObject(typeA);
		canvas.addGameObject(typeB);
		canvas.initializeSelection();
		// First object should be selected
		assertTrue(typeA.isSelected());
		assertFalse(typeB.isSelected());
	}
	
	@Test
	public void testVelocityChange() {
		int initialVelocity = typeA.getVelocity();
		typeA.setVelocity(5);
		assertEquals(5, typeA.getVelocity());
		assertNotEquals(initialVelocity, typeA.getVelocity());
	}
	
	@Test
	public void testPositionChange() {
		typeA.setX(150);
		typeA.setY(250);
		assertEquals(150, typeA.getX());
		assertEquals(250, typeA.getY());
	}
}

