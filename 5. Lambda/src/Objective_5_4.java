import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/*
 * Objective 5.4: Develop code that uses a method reference, including refactoring a 
 *                lambda expression to a method reference
 */

public class Objective_5_4 {

	public static void main(String... args) {
		Objective_5_4 _this = new Objective_5_4();
		
		_this.testMethodReference();
		_this.testMethodReferenceWithStaticMethod();
		_this.testMethodReferenceWithInstanceMethod();
	}
	
	public void testMethodReference() {

		List<Duck> ducks = new ArrayList<>();
		ducks.add(new Duck("AAA", 12));
		ducks.add(new Duck("ZZZ", 10));

		Comparator<Duck> byWeight = (d1, d2) -> DuckHelper.compareByWeight(d1, d2);
		Collections.sort(ducks, byWeight);
		System.out.println(ducks); // [ZZZ, AAA]
		
		// Method reference: The :: operator tells Java to pass the parameters 
		// automatically into compareByWeight -> Funcion interface.
		Comparator<Duck> byWeight2 = DuckHelper::compareByWeight;
		Collections.sort(ducks, byWeight2);
		System.out.println(ducks); // [ZZZ, AAA] 
	}
		
	// 1. Method reference - Static Method
	public void testMethodReferenceWithStaticMethod() {

		List<Integer> nums = new ArrayList<>();
		nums.add(22); nums.add(11);
		
		Consumer<List<Integer>> lambda1 = l -> Collections.sort(l);
		
		// In this case, we said that we were declaring a Consumer , which takes only
		// one parameter. Java looks for a method that matches that description.
		Consumer<List<Integer>> methodRef1 = Collections::sort;
		
		methodRef1.accept(nums);
		System.out.println(nums); // [11, 22]
		
		nums.add(10);
		System.out.println(nums); // [11, 22, 10]
		
		lambda1.accept(nums);
		System.out.println(nums); // [10, 11, 22]
	}
	
	// 2. Method reference - Instance Method
	public void testMethodReferenceWithInstanceMethod() {
		
		String str = "abc";
		Predicate<String> methodRef2 = str::startsWith;
		Predicate<String> lambda2 = s -> str.startsWith(s);
		
		System.out.println(methodRef2.test("abc"));		// true
		System.out.println(methodRef2.test("zabc"));	// false
		
		System.out.println(lambda2.test("abc"));		// true
		System.out.println(lambda2.test("zabc"));		// false
		
		// 3. We call an instance method without knowing the instance in advance:
		Predicate<String> methodRef3 = String::isEmpty;
		Predicate<String> lambda3 = s -> s.isEmpty();
		
		System.out.println(methodRef3.test("abc"));		// false
		System.out.println(methodRef3.test(""));		// true
	}
}

class Duck implements Comparable<Duck> {

	private String name;
	private int weight;

	public Duck(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Duck d) {
		return name.compareTo(d.name);
	}
}

class DuckHelper {
	public static int compareByWeight(Duck d1, Duck d2) {
		return d1.getWeight()-d2.getWeight();
	}
	public static int compareByName(Duck d1, Duck d2) {
		return d1.getName().compareTo(d2.getName());
	}
}

//************************************************************************************
