package study.blog.codingknowjam.algorithm.programmers;

class Solution_5 {

    // 2021 웹 백앤드 상반기 행렬 테두리 문제
    public static void main(String[] args) {

        int rows = 5;
        int columns = 5;
        int[][] arr = new int[rows + 1][columns + 1];

        for (int i = 1; i < rows+1; i++) {
            for (int j = 1; j < columns+1; j++) {
                arr[i][j] = (i - 1) * columns + j;
            }
        }

        for (int i = 1; i < rows+1; i++) {
            System.out.println();
            for (int j = 1; j < columns+1; j++) {
                System.out.print(arr[i][j] + " ");
            }
        }

    }

    public static int[] solution(int rows, int columns, int[][] queries) {

        int[][] matrix = new int[rows + 1][columns + 1];

        for (int i = 1; i < rows+1; i++) {
            for (int j = 1; j < columns+1; j++) {
                matrix[i][j] = (i - 1) * columns + j;
            }
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];

            int right = y2 - y1;
            int down = x2 - x1;
            int left = y2 - y1;
            int up = x2 - x1;

            int nextChangeValue = 0;
            int nowChangeValue = matrix[x1][y1];
            int result = nowChangeValue;
            while (right > 0) {
                nextChangeValue = matrix[x1][y1 + 1];
                matrix[x1][y1 + 1] = nowChangeValue;
                y1++;
                right--;
                nowChangeValue = nextChangeValue;
                result = Math.min(result, nowChangeValue);
            }

            while (down > 0) {
                nextChangeValue = matrix[x1+1][y1];
                matrix[x1+1][y1] = nowChangeValue;
                x1++;
                down--;
                nowChangeValue = nextChangeValue;
                result = Math.min(result, nowChangeValue);
            }

            while (left > 0) {
                nextChangeValue = matrix[x1][y1-1];
                matrix[x1][y1-1] = nowChangeValue;
                y1--;
                left--;
                nowChangeValue = nextChangeValue;
                result = Math.min(result, nowChangeValue);
            }
            while (up > 0) {
                nextChangeValue = matrix[x1-1][y1];
                matrix[x1-1][y1] = nowChangeValue;
                x1--;
                up--;
                nowChangeValue = nextChangeValue;
                result = Math.min(result, nowChangeValue);
            }

            answer[i] = result;

        }
        return answer;
    }

}