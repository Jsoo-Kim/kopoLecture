import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class RunClass {
	public static void main(String[] args) {
		Test[] t1 = new Test[10];
		ArrayList<Test> t2 = new ArrayList<Test>();
		
		String[] s1 = {new String("1"), "2"};
		
		ArrayList<String> s2 = new ArrayList<String>();
		s2.add(new String("1"));
		s2.add("2");
		
		System.out.println(Arrays.toString(s1));
		System.out.println(Arrays.toString(s2.toArray()));
		System.out.println(s2.toString());
		
		HashSet<String> h1 = new HashSet<String>();
		h1.add("1");
		h1.add("2");
		h1.add(new String("1"));
		
		System.out.println(h1.toString());
		
	}
}
