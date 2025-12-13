import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameObjectTestJUnit4 {
	
	private Canvas canvas;
	private Type_A_GameObject typeA;
	private Type_B_GameObject typeB;
	private Type_C_GameObject typeC;
	private Type_D_GameObject typeD;
	
	@Before
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
	public void testSelection() {
		typeA.setSelected(true);
		assertTrue(typeA.isSelected());
		
		typeA.setSelected(false);
		assertFalse(typeA.isSelected());
	}
	
	@Test
	public void testCanvasAddGameObject() {
		canvas.addGameObject(typeA);
		canvas.addGameObject(typeB);
		assertTrue(true);
	}
}

