import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Default {

	public static void main(String... args) {
		testSetCollection();
		navigableSet();
	}
	
	private static void testSetCollection() {
		Set<Integer> set = new HashSet<>();
		boolean b1 = set.add(66); // true
		boolean b2 = set.add(10); // true
		boolean b3 = set.add(66); // false
		boolean b4 = set.add(8); // true
		for (Integer integer: set) System.out.println(integer); // 66,8,10
		// 8,10,66  if a TreeSet is used
	}
	
	private static void navigableSet() {
		NavigableSet<Integer> set = new TreeSet<>();
		for (int i = 1; i <= 20; i++) set.add(i);
		System.out.println(set.lower(10)); 		// 9
		System.out.println(set.floor(10)); 		// 10
		System.out.println(set.ceiling(14)); 	// 14
		System.out.println(set.higher(14)); 	// 15
	}
}
