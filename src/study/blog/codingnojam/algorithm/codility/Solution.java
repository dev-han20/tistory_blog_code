package study.blog.codingnojam.algorithm.codility;

import java.util.Stack;

class Solution {
    public int solution(String S) {
        int result = -1;
        if (S.length() == 0) {
            return 1;
        }

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            if (st.isEmpty()) {
                st.push(S.charAt(i));
            } else {
                char c = st.peek();
                if (c == '(') {
                    c = ')';
                }else if (c == '{') {
                    c = '}';
                } else if (c == '[') {
                    c = ']';
                }else{
                    result = 0;
                    break;
                }

                if (c == S.charAt(i)) {
                    st.pop();
                } else {
                    st.push(S.charAt(i));
                }
            }
        }


        result = st.isEmpty() ? 1 : 0;

        return result;

    }
}
