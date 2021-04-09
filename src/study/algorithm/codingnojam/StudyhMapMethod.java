package study.algorithm.codingnojam;

import java.util.HashMap;
import java.util.Map;

public class StudyhMapMethod {

	public static void main(String[] args) {
		//https://codingnojam.tistory.com/39 포스팅
		
		Map<String, String> map = new HashMap<>();

		map.compute("coding", (key, oldValue) -> oldValue == null ? "0" : oldValue + "1");
		System.out.println(map.get("coding"));	
		// 실행결과 0
		// key의 값이 있는게 확실 한 경우에만 
		map.compute("coding", (key, oldValue) -> oldValue + "1");
		System.out.println(map.get("coding"));	
		// 실행결과 01		


		String value = map.computeIfAbsent("nojam", key -> "Coding" + key );
		System.out.println(value);
		// 실행결과 : Codingnojam
		value = map.computeIfAbsent("nojam", key -> "Hi" + key );
		System.out.println(value);
		// 실행결과 : Codingnojam
		
		map.put("Coding", "NoJam");
		String str = map.computeIfPresent("Coding", (key, oldValue) -> key + oldValue + "Hello");
		System.out.println(str);
		String str_null = map.computeIfPresent("none", (key, oldValue) -> key + oldValue + "Hello");
		System.out.println(str_null);
		
		
		map.put("Java", "NoJam");
		// Java 키의 매핑 된 값이 존재하므로 NoJam 반환
		str = map.getOrDefault("Java", "Hi");
		System.out.println(str); // NoJam 출력
		
		// Map이라는 키에 매핑 된 값이 없으므로 default값으로 Interface 반환
		str = map.getOrDefault("Map", "Interface");
		System.out.println(str); //Interface 출력
		// HashMap이라는 키에 매핑 된 값이 없으므로 default값으로 Interface 반환
		str = map.getOrDefault("HashMap", "Interface");
		System.out.println(str); //Interface 출ㅓ력
		
		// 내부에서 key와 value를 쌍으로 하나씩 꺼내서 출력
		map.forEach((k, v) -> System.out.println(k + " : " + v));
		/* 출력 예시
		 * coding : 01
		 * Java : NoJam
		 * Coding : CodingNoJamHello
		 * nojam : Codingnojam 
		 */
		
		
		// kakao라는 키에 매핑된 value값이 없으므로 very good을 값으로 매핑
		map.putIfAbsent("kakao", "very good");
		System.out.println(str);  // very good 출력
		// nvaer라는 키에 매핑된 value값이 없으므로 so good을 값으로 매핑
		map.putIfAbsent("naver", "so good");
		System.out.println(str);
		
		// kakao 키가 존재하므로 매핑 된 value 값 리턴
		str = map.putIfAbsent("kakao", "bad");
		System.out.println(str);
		// naver 키가 존재하므로 매핑 된 value 값 리턴
		str = map.putIfAbsent("naver", "not bad");
		System.out.println(str);
	}

}
