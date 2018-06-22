/*
 * Objective 1.4: Use static and default methods of an interface including inheritance 
 *                rules for a default method.
 */

public class Objective_1_4 {

	public static void main(String... args) {
		Fly flyingAnimal = new Eagle();
		flyingAnimal.land(); // Eagle is diving fast
	}
}

// ************************************************************************************

interface Fly {

	public int getWingSpan() throws Exception;

	public static final int MAX_SPEED = 100;

	public default void land() { // New!
		System.out.println("Animal is landing");
	}

	// available without an instance of the interface
	public static double calculateSpeed(float distance, double time) {
		return distance / time;
	}
}

class Eagle implements Fly {

	// does not require the exception to be declared.
	public int getWingSpan() {
		return 15;
	}

	// default method that has been optionally overridden
	public void land() {
		System.out.println("Eagle is diving fast");
	}
}

interface Jump {

	public default void land() { // New!
		System.out.println("Animal is landing after a jump");
	}
}

class SuperEagle implements Fly, Jump {
	
	public int getWingSpan() {
		return 15;
	}
	
	// CE > Duplicate default methods named land with the parameters () and () are 
	// inherited from the types Jump and Fly. 
	/* + */ public void land() { 
	/* + */		System.out.println("Animal is landing every time");
	/* + */ }
}

// ************************************************************************************
