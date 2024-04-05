//package 알고리즘;
//
//import java.util.Iterator;
//import java.util.PriorityQueue;
//import java.util.Random;
//
//public class PriorityQueueEx {
//	public static void main(String[] args) {
//		PriorityQueue<Integer> queue = new PriorityQueue<>();
//
//		queue.offer(1); // 실패시 false 반환
//		queue.add(2); // 실패시 throw 발생
//
//		System.out.println(queue.toString()); // [1, 2]
//		System.out.println(queue.peek()); // 1
//		System.out.println(queue.toString()); // [1, 2]
//		System.out.println("--------------------------");
//		System.out.println(queue.toString()); // [1, 2]
//		System.out.println(queue.poll()); // 1 ( 실패시 false 반환 <=> remove : 실패시 throw )
//		System.out.println(queue.toString()); // [2]
////		System.out.println(queue.remove()); // 2
////		System.out.println(queue.remove()); // throw (NoSuchElementException)
//		System.out.println("--------------------------");
//
//		// 조회
//		Iterator<Integer> it = queue.iterator();
//		while (it.hasNext()) {
//			System.out.print(it.next() + " "); // 2
//		}
//
//		// 전체 삭제
//		while (!queue.isEmpty()) {
//			System.out.println(queue.poll()); // 2 (remove도 가능)
//		}
//		System.out.println(queue.toString()); // []
//
//		// 랜덤 숫자 생성을 위한 Random 객체 생성
//		Random random = new Random();
//
//		// 1부터 1000 이하의 랜덤한 숫자를 생성하여 PriorityQueue에 추가
//		for (int i = 0; i < 10; i++) {
//			int randomNumber = random.nextInt(100) + 1;
//			queue.offer(randomNumber);
//		}
//
//		printPriorityQueueBinaryTree(queue);
//
//	}
//
//	private static void printPriorityQueueBinaryTree(PriorityQueue<Integer> queue) {
//		Object[] heapArray = queue.toArray();
//		int n = heapArray.length;
//		for (int i = 0; i < n / 2; i++) {
//			// 노드 값 출력
//			System.out.print(heapArray[i] + " ");
//
//			// 왼쪽 자식 노드 인덱스 계산
//			int leftChildIndex = 2 * i + 1;
//			// 오른쪽 자식 노드 인덱스 계산
//			int rightChildIndex = 2 * i + 2;
//
//			// 왼쪽 자식 노드 출력
//			if (leftChildIndex < n) {
//				System.out.print("Left: " + heapArray[leftChildIndex] + " ");
//			}
//
//			// 오른쪽 자식 노드 출력
//			if (rightChildIndex < n) {
//				System.out.print("Right: " + heapArray[rightChildIndex] + " ");
//			}
//
//			System.out.println();
//		}
//	}
//
//}
