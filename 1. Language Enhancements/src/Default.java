import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Default {
	
	public static void main(String... args) {

		Integer numPounds = 15_000;	// Java7: numeric literals can contain underscores
		
		ship("Allo!");	// String
		ship(123);		// Other
		
		//- <String>ship("Au Revoir!");		// CE
		Default.<String>ship("Au Revoir!");	// String
		
		autoboxing();
		arrayStoreException();
		upperBoundedWildcards();
		lowerBoundedWildcards();
	}
	
	public static <T> void sink(T t) { }
	public static <T> T identity(T t) { return t; }
	//- public static T noGood(T t) { return t; } // CE> parameter type is missing
	
	public static <T> void ship(T type) {
		if (type instanceof String) 
			System.out.println("String");
		else 
			System.out.println("Other");
	}
	
	public static void autoboxing() {
		List numbers = new ArrayList();
		numbers.add(2);
		//- int xx = numbers.get(0); // CE> cannot convert from Object to int
		int xx = (Integer) numbers.get(0);
	}
	
	public static void arrayStoreException() {
		
		Integer[] numbers = { new Integer(42)};
		Object[] objects = numbers;
		//- objects[0] = "forty two"; // throws ArrayStoreException
		
		objects = new String[1];
		objects[0] = "forty two"; // OK!
	}
	
	public static void upperBoundedWildcards() {
		// Java doesn’t know what type List<? extends IOException> really is.
		List<? extends IOException> exceptions = new ArrayList<IOException>();
		//- exceptions.add(new Exception()); 	// DOES NOT COMPILE
		//- exceptions.add(new IOException());  // DOES NOT COMPILE
		//- exceptions.add(new FileNotFoundException()); // DOES NOT COMPILE
	}
	
	public static void lowerBoundedWildcards() {
		List<? super IOException> exceptions = new ArrayList<Exception>();
		//- exceptions.add(new Exception()); // DOES NOT COMPILE
		exceptions.add(new IOException());
		exceptions.add(new FileNotFoundException());
	}
	
}
