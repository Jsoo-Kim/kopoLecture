package 알고리즘;

import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class TreeSetEx {
	public static void main(String[] args) {

		int arr[] = {1,1,2,2,4,4};
		int n = 1;
		
		System.out.println(myFunc1(arr,n));
		System.out.println(myFunc2(arr,n));
			
	}
	
	// HashSet 풀이
	public static int myFunc1(int arr[], int n) {
		HashSet<Integer> set = new HashSet<Integer>();

//		Arrays.stream(arr).filter(element -> element != n).forEach(element -> set.add(element));
//		Arrays.stream(arr).filter(element -> element != n).forEach(set::add);
		for(int i=0; i<arr.length; i++) {
			if(arr[i] != n) set.add(arr[i]);
		}
		
		System.out.println("Elements in set: " + set); //Elements in set: [2, 4]
		return set.size();
	}
	
	// TreeSet 풀이
	public static int myFunc2(int arr[], int n) {
		Comparator<Integer> reverseOrdering = new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return b - a;
			}
		};
		TreeSet<Integer> set = new TreeSet<Integer>(reverseOrdering);

//		TreeSet<Integer> set = new TreeSet<Integer>((a,b)->{
//			return b-a;
//		});

		//Arrays.stream(arr).filter(element -> element != n).forEach(element -> set.add(element));
		//Arrays.stream(arr).filter(element -> element != n).forEach(set::add);
		for(int i=0; i<arr.length; i++) {
			if(arr[i] != n) set.add(arr[i]);
		}
		
		System.out.println("Elements in set: " + set);
		return set.size();
	}
}
