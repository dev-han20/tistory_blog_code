package study.blog.codingknowjam.algorithm.programmers;

class Solution_backend_second_half_2 {
	static int[] days;
	static int[] target;
	static int result = 0;

	public static void main(String[] args) {
		Solution_backend_second_half_2 s = new Solution_backend_second_half_2();
		int[] arr = {1, 2, 3, 4, 28, 29, 30};

		System.out.println(s.solution(30, "MON", arr));
	}

	public int solution(int leave, String day, int[] holidays) {
		days = new int[31];
		if (day.equals("SAT") || day.equals("SUN")) {
			days[1] = 1;
		}

		int st = 0;
		if (day.equals("MON")) {
			st = 6;
		} else if (day.equals("TUE")) {
			st = 5;
		} else if (day.equals("WED")) {
			st = 4;
		} else if (day.equals("THU")) {
			st = 3;
		} else if (day.equals("FRI")) {
			st = 2;
		} else if (day.equals("SAT")) {
			st = 1;
		} else {
			st = 7;
		}

		for (int i = st; i < days.length; i= i+7) {
			days[i] = 1;
			if (i+1 < days.length) {
				days[i+1] = 1;
			}
		}

		for (int i = 0; i < holidays.length; i++) {
			days[holidays[i]] = 1;
		}

		int tempCount = 0;
		for (int i = 1; i < days.length; i++) {
			if (days[i] == 1) {
				tempCount++;
			}
		}
		if (30 - tempCount < leave) {
			leave = 30 - tempCount;
		}
		target = new int[leave];

		DFS(0, 1,leave);
		return result;
	}

	void DFS(int len, int index, int limit) {

		if (len == target.length) {
			int tempResult = 1;
			int[] tempDays = new int[days.length];

			for (int i = 1; i < days.length; i++) {
				tempDays[i] = days[i];
			}
			for (int i = 0; i < target.length; i++) {
				tempDays[target[i]] = 1;
			}
			for (int i = 1; i < tempDays.length-1; i++) {
				if (tempDays[i] == tempDays[i + 1] && tempDays[i] == 1) {
					tempResult++;
				} else {
					result = Math.max(result, tempResult);
					tempResult = 1;
				}
			}
			result = Math.max(result, tempResult);
			return;
		}

		for (int i = index; i < days.length; i++) {
			if (days[i] == 1) {
				continue;
			}
			target[len] = i;
			DFS(len + 1, i + 1, limit);
		}
	}
}