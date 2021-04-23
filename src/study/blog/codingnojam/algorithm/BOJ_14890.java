package study.blog.codingnojam.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14890 {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		
		int[][] map = new int[Integer.parseInt(info[0])][Integer.parseInt(info[0])];
		int L = Integer.parseInt(info[1]);
		
		for (int i = 0; i < map.length; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		int[][] rotateMap = new int[Integer.parseInt(info[0])][Integer.parseInt(info[0])];
		
		for (int i = 0; i < rotateMap.length; i++) {
			for (int j = 0; j < rotateMap[i].length; j++) {
				rotateMap[i][j] = map[Integer.parseInt(info[0])-1-j][0+i];
			}
		}
		
		int value = 0;
		value = value + roadCount(L, map);
		value = value + roadCount(L, rotateMap);
		System.out.println(value);
//		
//		for (int i = 0; i < rotateMap.length; i++) {
//			for (int j = 0; j < rotateMap.length; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (int i = 0; i < rotateMap.length; i++) {
//			for (int j = 0; j < rotateMap.length; j++) {
//				System.out.print(rotateMap[i][j] + " ");
//			}
//			System.out.println();
//		}
	}


	private static int roadCount(int slopeLength, int[][] map) {
		int count = 0;
		
		for (int i = 0; i < map.length; i++) {
			boolean[] slopeChk = new boolean[map.length];
			boolean roadChk = false;
			
			for (int j = 0; j < map[i].length-1; j++) {
				if(map[i][j] == map[i][j+1]) {
					roadChk = true;
				}else if(map[i][j] - map[i][j+1] == -1) {
					if(j - slopeLength+1 >= 0) {
						boolean tempChk = false;
						for(int k=0; k<slopeLength; k++) {
							if(map[i][j-k] == map[i][j] && !slopeChk[j-k]) {
								slopeChk[j-k] = true;
							}else {
								tempChk = true;
							}
						}
						if(tempChk) {
							roadChk = false;
							break;
						}else {
							roadChk = true;
						}
					}else {
						roadChk = false;
						break;
					}
				}else if(map[i][j] - map[i][j+1] == 1) {
					if(j + slopeLength < map.length) {
						boolean tempChk = false;
						int tempIndex =0;
						for(int k=1; k<=slopeLength; k++) {
							if(map[i][j+k] == map[i][j+1]) {
								slopeChk[j+k] = true;
								tempIndex = j+k;
							}else {
								tempChk = true;
							}
						}
						if(tempChk) {
							roadChk = false;
							break;
						}else {
							roadChk = true;
							j = tempIndex-1;
						}
					}else {
						roadChk = false;
						break;
					}
				}else {
					roadChk = false;
					break;
				}
			}
			if(roadChk) {
//				System.out.println(i + " ");
				count++;
			}
		}
		
		
		return count;
		
	}
	
}
