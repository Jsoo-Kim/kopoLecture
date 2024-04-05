//package 알고리즘;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Iterator;
//
//public class ArrayListEx {
//	public static void main(String[] args) {
//		ArrayList<> myList = new ArrayList();
//		myList.add(2);
//		myList.add(3);
//		myList.add(1);
//		myList.add(4);
//		
//		// 정렬
//////		System.out.println(myList.get(0)); 
//////		
//////		for (int i = 0; i < myList.size(); i++) {
//////			System.out.print(myList.get(i) + " ");
//////		}
//////		System.out.println();
//////		
//////		Collections.sort(myList);
//////		System.out.println(Arrays.toString(myList.toArray()));
//////		
//////		Collections.sort(myList, Collections.reverseOrder());
//////		System.out.println(Arrays.toString(myList.toArray()));
////		
//		// 실무유형1
//		Iterator it = myList.iterator();
//		while (it.hasNext()) {
//			int value = (int) it.next();
//			System.out.println(value);
//		};
//		
//		// 실무유형2 - for each 문 스타일
//		for (Object i : myList) {
//			System.out.println(i);
//		}
//		
//		for (int i = 0; i < myList.size(); i++) {
//			System.out.println(myList.get(i));
//		}
//		
//		
//		ArrayList<String> strList = new ArrayList<String>();
//		strList.add("apple");
//		strList.add("banana");
//		System.out.println(Arrays.deepToString(strList.toArray())); // [apple, banana]
//		
//		Collections.sort(strList);
//		System.out.println(Arrays.toString(strList.toArray())); // [apple, banana]
//		
//		Collections.sort(strList, Collections.reverseOrder());
//		System.out.println(Arrays.deepToString(strList.toArray())); // [banana, apple]
//
//	}
//}
