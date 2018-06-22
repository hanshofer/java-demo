import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * Objective 6.2: Develop code that iterates a collection, filters a collection, and 
 * sorts a collection by using lambda expressions.
 */
public class Objective_6_2 {
	
	public static void main(String... args) {
		Objective_6_2 _this = new Objective_6_2(); 
		_this.testComparator();
	}
	
	static class Rabbit{ int id; }
	
	private void testComparator() {
		
		Set<Rabbit> rabbit = new TreeSet<>();
		rabbit.add(new Rabbit()); // RE> throws an exception (not Comparable). 
		
		Comparator<Duck> byWeight = new Comparator<Duck>() {
			public int compare(Duck d1, Duck d2) {
				return d1.getWeight() - d2.getWeight();
			}
		};
		
		Comparator<Duck> byWeight1 = (d1, d2) -> d1.getWeight()-d2.getWeight();
		Comparator<Duck> byWeight2 = (Duck d1, Duck d2) -> d1.getWeight()-d2.getWeight();
		Comparator<Duck> byWeight3 = (d1, d2) -> { return d1.getWeight()-d2.getWeight(); };
		Comparator<Duck> byWeight4 = (Duck d1, Duck d2) -> {return d1.getWeight()-d2.getWeight(); };
		
		List<Duck> ducks = new ArrayList<>();
		ducks.add(new Duck("Quack", 7));
		ducks.add(new Duck("Puddles", 10));
		Collections.sort(ducks);
		System.out.println(ducks); // [Puddles, Quack]
		Collections.sort(ducks, byWeight);
		System.out.println(ducks); // [Quack, Puddles]
		Collections.sort(ducks, byWeight1);
		System.out.println(ducks); // [Quack, Puddles]
		Collections.sort(ducks, byWeight2);
		System.out.println(ducks); // [Quack, Puddles]
		Collections.sort(ducks, byWeight3);
		System.out.println(ducks); // [Quack, Puddles]
		Collections.sort(ducks, byWeight4);
		System.out.println(ducks); // [Quack, Puddles]
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