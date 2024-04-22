import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	public Node rootNode;
	public ArrayList<Node> nodes = new ArrayList();
//	public int[][] adjMatrix;// Edges will be represented as adjacency Matrix
	public Map<Node, List> adjMatrix = new HashMap<>(); // 추가
//	int size;

	public void setRootNode(Node n) {
		this.rootNode = n;
	}

	public Node getRootNode() {
		return this.rootNode;
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	// This method will be called to make connect two nodes
	public void connectNode(Node start, Node end) {
		
		List nodeList = adjMatrix.get(start); // 추가
		if (nodeList == null) {
//			size = nodes.size();
//			adjMatrix = new int[size][size];
			nodeList = new ArrayList();
		}

//		int startIndex = nodes.indexOf(start);
//		int endIndex = nodes.indexOf(end);
//		adjMatrix[startIndex][endIndex] = 1;
//		adjMatrix[endIndex][startIndex] = 1;
		
		nodeList.add(end); // 추가
		adjMatrix.put(start, nodeList); // 추가 
		
	}

	private Node getUnvisitedChildNode(Node n) {

//		int index = nodes.indexOf(n);
//		int j = 0;
//		while (j < size) {
//			if (adjMatrix[index][j] == 1 && ((Node) nodes.get(j)).visited == false) {
//				return (Node) nodes.get(j);
//			}
//			j++;
//		}
//		
//		return null;
		
		// -- 추가 --
		List nodeList = adjMatrix.get(n);
		for (int i = 0; ((nodeList != null) && (i < nodeList.size())); i++) {
			Node checkNode = (Node) nodeList.get(i);
			if (checkNode.visited == false) {
				return checkNode;
			}
		}
		return null;
	}

	// BFS traversal of a tree is performed by the bfs() function
	public void bfs() {

		// BFS uses Queue data structure
		Queue q = new LinkedList();
		q.add(this.rootNode);
		printNode(this.rootNode);
		rootNode.visited = true;
		while (!q.isEmpty()) {
			Node n = (Node) q.remove();
			Node child = null;
			while ((child = getUnvisitedChildNode(n)) != null) {
				child.visited = true;
				printNode(child);
				q.add(child);
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}

	// DFS traversal of a tree is performed by the dfs() function
	public void dfs() {
		// DFS uses Stack data structure
		Stack s = new Stack();
		s.push(this.rootNode);
		rootNode.visited = true;
		printNode(rootNode);
		while (!s.isEmpty()) {
			Node n = (Node) s.peek();
			Node child = getUnvisitedChildNode(n);
			if (child != null) {
				child.visited = true;
				printNode(child);
				s.push(child);
			} else {
				s.pop();
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}

	// Utility methods for clearing visited property of node
	private void clearNodes() { // visited false로 초기
//		int i = 0;
//		while (i < size) {
//			Node n = (Node) nodes.get(i);
//			n.visited = false;
//			i++;
//		}
		
		/*
		for (Object element : nodes) {
			Node n = (Node) element;
			n.visited = false;
		}
		 */
		
		/*
		for (int i = 0; i < nodes.size(); i++) {
			Node n = (Node) nodes.get(i);
			n.visited = false;
		}
		*/
		
		/*
		nodes.forEach((element) -> {
			Node n = (Node) element;
			n.visited = false;
		});
		*/
		
		/*
		Iterator<Node> it = nodes.iterator();
		while (it.hasNext()) {
			Node n = (Node) it.next();
			n.visited = false;
		}
		*/
		
		nodes.stream().forEach((element) -> {
			Node n = (Node) element;
			n.visited = false;
		});
		
		/*
		nodes = nodes.stream().map(element -> {
			Node n = new Node(element.label);
			n.visited = false;
			return n;
		}).toList(); // 자바 8 이후부터만 ArrayList도 map() 사용 가능! 그 전은 List는 되고 ArrayList는 안 됨
		*/
		
	}

	// Utility methods for printing the node's label
	private void printNode(Node n) {
		System.out.print(n.label + " ");
	}

}
