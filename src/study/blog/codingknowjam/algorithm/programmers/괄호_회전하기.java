package study.blog.codingknowjam.algorithm.programmers;

import java.util.Stack;

public class 괄호_회전하기 {
	private static Stack<String> stack = new Stack();
	private static String[] brackets;
	private static int numberOfRightBrackets = 0;

	public int solution(String s) {
		brackets = s.split("");
		String rotationString = s;
		for (int i = 0; i < s.length(); i++) {
			ifRightBracketsCount();
			rotationString = rotateString(rotationString);
			brackets = rotationString.split("");
		}
		return numberOfRightBrackets;
	}

	private void ifRightBracketsCount() {
		for (int j = 0; j < brackets.length; j++) {
			if (validateBrackets()) {
				numberOfRightBrackets++;
				break;
			}
		}
	}

	private String rotateString(String s) {
		StringBuffer stringBuffer = new StringBuffer(s);
		char firstChar = stringBuffer.charAt(0);
		stringBuffer.deleteCharAt(0);
		stringBuffer.append(firstChar);
		return stringBuffer.toString();
	}

	private boolean validateBrackets() {
		for (int i = 0; i < brackets.length; i++) {
			if (stack.isEmpty()) {
				if (isCloseBracket(brackets[i])) {
					return false;
				}
				stack.push(brackets[i]);
				continue;
			}

			if (isOpenBracket(brackets[i])) {
				stack.push(brackets[i]);
				continue;
			}

			if (isRightBracket(brackets[i])) {
				stack.pop();
				continue;
			}
		}

		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean isRightBracket(String closeBracket) {
		if (stack.peek().equals("(") && closeBracket.equals(")")) {
			return true;
		}

		if (stack.peek().equals("{") && closeBracket.equals("}")) {
			return true;
		}

		if (stack.peek().equals("[") && closeBracket.equals("]")) {
			return true;
		}

		return false;
	}

	private boolean isCloseBracket(String bracket) {
		if (bracket.equals("]") || bracket.equals("}") || bracket.equals(")")) {
			return true;
		}
		return false;
	}

	private boolean isOpenBracket(String bracket) {
		if (bracket.equals("[") || bracket.equals("{") || bracket.equals("(")) {
			return true;
		}
		return false;
	}
}
