package test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import org.junit.Before;
import org.junit.Test;

public class advertisement {
	List<Node> src = new ArrayList();
	List<Integer> timePoint = new ArrayList();

	@Before
	public void init() {
		Node n1 = new Node(1, 1, 2, 20);
		Node n2 = new Node(2, 2, 6, 50);
		Node n3 = new Node(3, 2, 6, 10);
		Node n4 = new Node(4, 1, 2, 60);
		Node n5 = new Node(5, 2, 5, 70);
		Node n6 = new Node(6, 3, 5, 85);
		Node n7 = new Node(6, 4, 6, 40);

		src.add(n1);
		src.add(n2);
		src.add(n3);
		src.add(n4);
		src.add(n5);
		src.add(n6);
		src.add(n7);
		
		timePoint.add(5);
		timePoint.add(2);
	}
	
	@Test
	public void testAdvertisement() {
		HashMap<Integer, Integer> res = putAds(timePoint, src);
		for (Entry<Integer, Integer> entry : res.entrySet()) {
			System.out.println(entry.getKey() + " => " + entry.getValue());
		}
	}
	
	Comparator<Node> listComparator = new Comparator<Node>(){
		@Override
		public int compare(Node o1, Node o2) {
			if (o1.score > o2.score) {
				return -1;
			} else if (o1.score == o2.score) {
				return 0;
			} else {
				return 1;
			}
		}};
	
	public HashMap<Integer, Integer> putAds(List<Integer> timePoint, List<Node> src) {
		HashMap<Integer, Integer> res = new HashMap();
		HashSet<Node> visited = new HashSet();
		for (int i = 0; i < timePoint.size(); i++) {
			PriorityQueue<Node> heap = new PriorityQueue(src.size(), listComparator);
			int timepoint = timePoint.get(i);
			for (Node n : src) {
				if (check(n, timepoint) && !visited.contains(n)) {
					heap.add(n);
				}
			}
			Node n = heap.poll();
			res.put(i, n.score);
			visited.add(n);
		}
		return res;	
	}


	private boolean check(Node n, int timepoint) {
		if (timepoint >= n.timeStart && timepoint <= n.timeEnd) {
			return true;
		}
		return false;
	}


//	class Point {
//		int id;
//		int score;
//		Point (int id, int score) {
//			this.id = id;
//			this.score = score;
//		}
//	}
//	
//	Comparator<Point> listComparator = new Comparator<Point>(){
//
//		@Override
//		public int compare(Point o1, Point o2) {
//			if (o1.score > o2.score) {
//				return 1;
//			} else if (o1.score == o2.score) {
//				return 0;
//			} else {
//				return -1;
//			}
//		}};
//		
//	@Test
//	public void testScore() {
//		Point p1 = new Point(0, 43);
//		Point p2 = new Point(1, 23);
//		Point p3 = new Point(2, 34);
//		Point p4 = new Point(3, 8);
//		List<Point> list =new ArrayList();
//		list.add(p1);
//		list.add(p2);
//		list.add(p3);
//		list.add(p4);
//		
//		PriorityQueue<Point> heap = new PriorityQueue(list.size(), listComparator);
//		heap.add(p1);
//		heap.add(p2);
//		heap.add(p3);
//		heap.add(p4);
//		
//		while (!heap.isEmpty()) {
//			Point p = heap.poll();
//			System.out.println(p.score);
//		}
//	}
	
}
