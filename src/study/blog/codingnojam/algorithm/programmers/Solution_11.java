package study.blog.codingnojam.algorithm.programmers;

import java.util.*;

// 찾아라 프로그래밍 마에스터 - 폰켓몬
public class Solution_11 {

    public int solution(int[] nums) {
        int limit = nums.length / 2;

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        return limit >= set.size() ? set.size() : limit;
    }
}
