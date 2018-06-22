import java.util.function.Predicate;

/*
 * Objective 5.1: Define and write functional interfaces and describe the interfaces 
 *                of the java.util.function package rules for a default method.
 */

public class Objective_5_1 {

	public static void main(String... args) {
	}
}

//************************************************************************************

class Animal {
	private String species;
	private boolean canHop;
	private boolean canSwim;

	public Animal(String speciesName, boolean hopper, boolean swimmer) {
		species = speciesName;
		canHop = hopper;
		canSwim = swimmer;
	}

	public boolean canHop() {
		return canHop;
	}

	public boolean canSwim() {
		return canSwim;
	}

	public String twoParams(Animal a2) {
		return this.species + " + " + a2.species;
	}
	
	public String toString() {
		return species;
	}
}

// Java defines a functional interface as an interface that contains a single
// abstract method. The annotation is optional (good practice).
@FunctionalInterface
interface Sprint {
	public void sprint(Animal animal);
}

class Kangaroo implements Sprint {
	public void sprint(Animal animal) {
		System.out.println("Animal is jumping fast! " + animal.toString());
	}
}

class Tiger implements Sprint {
	public void sprint(Animal animal) {
		System.out.println("Animal is sprinting fast! " + animal.toString());
	}
}

// Valid FunctionalInterface (only one super abstract method)
interface Run extends Sprint {
}

// Valid FunctionalInterface (same super method override)
interface SprintFaster extends Sprint {
	public void sprint(Animal animal);
}

// Valid FunctionalInterface (only one super abstract method)
interface Skip extends Sprint {

	public default int getHopCount(Kangaroo kangaroo) {
		return 10;
	}

	public static void skip(int speed) {
	}
}

// CE > Crawl is not a functional interface
//- @FunctionalInterface
interface Crawl {
	public void crawl();
	public int getCount();
}

//************************************************************************************

// FunctionalInterface 
interface CheckTrait {
	public boolean test(Animal a);
}

// FunctionalInterface with no params
interface CheckTrait_noParam {
	public String test();
}

//FunctionalInterface with two params
interface CheckTrait2Param {
	public String test(Animal a, Animal b);
}

class FindMatchingAnimals {

	private static void print(Animal animal, CheckTrait trait) {
		if (trait.test(animal)) {
			System.out.println(animal);
		}
	}

	private static void print2(Animal animal, Predicate<Animal> trait) {
		if (trait.test(animal))
			System.out.println(animal);
	}

	private static void print(Animal animal, CheckTrait_noParam trait) {
		System.out.println(trait.test());
	}

	private static void print3(Animal dog, Animal cat, CheckTrait2Param trait) {
		System.out.println(trait.test(dog, cat));
	}
	
	public static void main(String[] args) {
		
		// Lambda expression here is same as: (Animal a) -> { return a.canHop();
		// - We specify a single parameter with the name a.
		// - The arrow operator → separates the parameter from the body.
		// - The body calls a single method and returns the result of that method.
		
		// The parentheses () can be omitted in a lambda expression if there is 
		// exactly one input parameter and the type is not explicitly stated in the 
		// expression.
		
		print(new Animal("fish", false, true), a -> a.canHop());     // (no result)

		Animal dog = new Animal("dog", true, false);
		Animal cat = new Animal("cat", true, false);
				
		// same result
		print(dog, a -> a.canHop()); // dog
		print(dog, a -> { return a.canHop(); }); // dog
		print(dog, (Animal a) ->  a.canHop()); // dog
		
		// applying the Predicate Interface
		print2(cat, a -> a.canHop()); // cat
		
		// lambda expression with two params
		print3(dog, cat, (a, b) -> a.twoParams(b));
		
		// CE > Lambda expression's parameter dog cannot redeclare another local 
		//      variable defined in an enclosing scope. Idem for cat var.
		//- print3(dog, cat, (dog, cat) -> dog.twoParams(cat));
		
		// CE > Lambda expression's parameter a is expected to be of type Animal
		//- print(new Animal("kangaroo", true, false), (Kangaroo a) ->  a.canHop()); 

		// CE > requires parentheses 
		//- print(new Animal("kangaroo", true, false), Animal a -> a.canHop()); 
		
		// expressions that have zero or more than one input parameter will 
		// require parentheses.
		print(new Animal("eagle", true, false), () -> new String("test")); // test
		
		/*
		 * Valid lambda expressions:
		 *  
		 *   () -> true 							// 0 parameters
		 *   a -> {return a.startsWith("test");} 	// 1 parameter
		 *   (String a) -> a.startsWith("test") 	// 1 parameter
		 *   (int x) -> {} 							// 1 parameter
		 *   (int y) -> {return;} 					// 1 parameter
		 *   (a, b) -> a.startsWith("test") 		// 2 parameters
		 *   (String a, String b) -> a.startsWith("test") // 2 parameters
		 */
		
		/*
		 * Does not compile:
		 * 
		 *   Duck d -> d.quack() 
         *   a,d -> d.quack() 
         *   Animal a, Duck d -> d.quack() 
         *   a, b -> a.startsWith("test") 
         *   c -> return 10; // DOES NOT COMPILE
		 *	 a -> { return a.startsWith("test") } 
		 *
		 *   // ** When one parameter has a data type listed, though, all parameters  
		 *   // must provide a data type.
		 *   (int y, z) -> {int x=1; return y+10; }  // does not compile   
		 *   
		 *   // ** Java doesn’t allow us to re‐declare a local variable (a)
		 *   (a, b) -> { int a = 0; return 5;}
		 */
	}
}

//************************************************************************************
