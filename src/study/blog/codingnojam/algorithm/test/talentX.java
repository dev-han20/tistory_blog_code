package study.blog.codingnojam.algorithm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class talentX {
	public static void main(String[] args) {
		System.out.println(Math.pow(2, 3));
	}

	public static int minimumTime(List<Integer> ability, long processes) {
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		for (Integer tempInt : ability) {
			pq.offer(tempInt);
		}

		ArrayList<Integer> list = new ArrayList<>();
		list.add(2);





		return 1;
	}


}
