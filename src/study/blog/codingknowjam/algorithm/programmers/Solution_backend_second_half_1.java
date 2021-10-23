package study.blog.codingknowjam.algorithm.programmers;

import java.util.HashSet;

class Solution_backend_second_half_1 {
	public String solution(String[] registered_list, String new_id) {
		boolean eq = false;
		HashSet<String> idSet = new HashSet<>();
		for (int i = 0; i < registered_list.length; i++) {
			idSet.add(registered_list[i]);
		}

		while (true) {
			for (int i = 0; i < idSet.size(); i++) {
				if (idSet.contains(new_id)) {
					eq = true;
					idSet.remove(new_id);
					break;
				}
			}

			if (!eq) {
				break;
			} else {
				int N = -1;
				String S = "";
				int numberIndex = 0;
				for (int i = 2; i < new_id.length(); i++) {
					if ((int)new_id.charAt(i) < 97) {
						numberIndex = i;
						break;
					}
				}
				if (numberIndex == 0) {
					N = 0;
				} else {
					N = Integer.parseInt(new_id.substring(numberIndex, new_id.length()));
				}
				S = new_id.substring(0, numberIndex==0 ? new_id.length() : numberIndex);
				N++;
				new_id = S + String.valueOf(N);
				eq = false;
			}
		}

		return new_id;
	}
}