package study.blog.codingknowjam.algorithm.boj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BOJ_1107 {
	private static final Scanner scanner = new Scanner(System.in);
	// 리모컨 숫자버튼 누른 횟수
	private static int numberPadClickCount = 0;
	private static int currentMinValue = Integer.MIN_VALUE;

	//백준온라인저지 1107번 리모컨 문제풀이
	public static void main(String[] args) {
		// 리모컨
		RemoteController remoteController = RemoteController.create();
		// 현재 채널
		Channel currentChannel = Channel.of(100);
		// 이동해야 할 채널
		Channel targetChannel = Channel.of(scanner.nextInt());
		// 리모컨 버튼 망가짐
		breakButton(remoteController, scanner);

		// 현재 채널과 타켓 채널이 동일한 경우 -> 버튼 안눌러도 되므로 0이 최소값
		if (targetChannel.equals(currentChannel)) {
			System.out.println(numberPadClickCount);
			return;
		}

		// 리모컨 숫자버튼 눌러서 타켓 채널로 이동 가능한 경우 -> 숫자버튼 누른 횟수가 최소값
		int count1 = Integer.MAX_VALUE;
		if (remoteController.isPress(targetChannel.value())) {
			count1 = numberPadClickCount;
		}

		// 리모컨 +, - 버튼만 눌러서 채널 이동한 경우 -> +, - 버튼 누른 횟수가 최소값
		int count2 = currentChannel.getDifferenceValue(targetChannel);

		// 현재 리모컨으로 이동할 수 있는 채널 중 타켓채널에 가장 근접한 작은 채널로 이동한 경우
		// -> 숫자버튼 누른 횟수 + "+, -" 버튼 누른 횟수가 최소 값
		int count3 = currentChannel.moveLessTargetChannel(targetChannel, remoteController);

		// 현재까지 중 최소값 구하기
		currentMinValue = getMinValue(count1, count2, count3);

		// 현재 리모컨으로 이동할 수 있는 채널 중 타켓채널에 가장 근접한 큰 채널로 이동한 경우
		// -> -> 숫자버튼 누른 횟수 + "+, -" 버튼 누른 횟수가 최소 값
		// ## 채널이 무한대이므로 currentMinValue 값보다 버튼 누른 횟수 많으면 종료
		int count4 = currentChannel.moveGreaterTargetChannel(targetChannel, remoteController);

		// 최종 최소값 비교해서 결과 출력
		System.out.println(Math.min(currentMinValue, count4));
	}

	private static int getMinValue(int value1, int value2, int value3){
		return Math.min(value1, Math.min(value2, value3));
	}

	private static void breakButton(RemoteController remoteController, Scanner scanner) {
		IntStream.range(0, scanner.nextInt())
			.forEach(ignore -> remoteController.breakButton(scanner.nextInt()));
	}

	static class RemoteController {
		private final HashMap<Integer, Integer> numberPad = new HashMap<>();

		private RemoteController() {
			IntStream.range(0, 10)
				.forEach(number -> numberPad.put(number, 1));
		}

		public static RemoteController create() {
			return new RemoteController();
		}

		public void breakButton(int buttonNumber) {
			numberPad.put(buttonNumber, 0);
		}

		public boolean isPress(int channel) {
			String[] digitsArray = String.valueOf(channel).split("");
			numberPadClickCount = digitsArray.length;
			long matchCount = Arrays.stream(digitsArray)
				.map(Integer::parseInt)
				.filter(integer -> numberPad.get(integer) == 1)
				.count();
			return matchCount == digitsArray.length;
		}
	}

	static class Channel {
		private int channel;

		private Channel(final int channel) {
			this.channel = channel;
		}

		public static Channel of(final int channel) {
			return new Channel(channel);
		}

		private int moveLessTargetChannel(Channel targetChannel, RemoteController remoteController) {
			boolean end = false;
			channel = targetChannel.value();
			while (!end) {
				if (channel <= 0) {
					numberPadClickCount = 99999999;
					break;
				}
				channel--;
				end = remoteController.isPress(channel);
			}
			return targetChannel.value() - channel + numberPadClickCount;
		}

		private int moveGreaterTargetChannel(Channel targetChannel, RemoteController remoteController) {
			boolean end = false;
			channel = targetChannel.value();
			while (!end) {
				if (channel >= targetChannel.value() + currentMinValue) {
					numberPadClickCount = 99999999;
					break;
				}
				channel++;
				end = remoteController.isPress(channel);
			}

			return channel - targetChannel.value() + numberPadClickCount;
		}

		public int getDifferenceValue(Channel closeChannel) {
			return Math.abs(this.channel - closeChannel.channel);
		}

		public int value() {
			return this.channel;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Channel channel1 = (Channel)o;
			return channel == channel1.channel;
		}

		@Override
		public int hashCode() {
			return Objects.hash(channel);
		}
	}
}



