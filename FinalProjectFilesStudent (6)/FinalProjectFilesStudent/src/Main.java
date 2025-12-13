public class Main {
	
	public static void main(String[] args) {
		Canvas canvas = new Canvas();
		
		// Create different types of GameObjects
		canvas.addGameObject(new Type_A_GameObject(100, 100));
		canvas.addGameObject(new Type_B_GameObject(300, 200));
		canvas.addGameObject(new Type_C_GameObject(500, 300));
		canvas.addGameObject(new Type_D_GameObject(200, 400));
		
		// Initialize selection - first object is selected by default
		canvas.initializeSelection();
	}
}

