package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * 	문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오
 * 		 컴퓨터의 숫자는 난수를 이용하여 구한다.
 * 		 (스트라이크는 'S', 볼은 'B'로 출력한다.)
 * 
 * 		 컴퓨터의 난수가 9 5 7일 때 실행 예시)
 * 			숫자입력 => 3 5 6
 * 			3 5 6 => 1S 0B
 * 			숫자입력 => 7 8 9
 * 			7 8 9 => 0S 2B
 * 			...
 * 			숫자입력 => 9 5 7
 * 			9 5 7 => 3D 0B
 * 
 * 			5번째 만에 맞췄군요.
 */
public class T11_BaseBallTest {
	
	public static void main(String[] args) {
		
		Set<Integer> set = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		int input, strike = 0, ball = 0, num;
		Scanner s = new Scanner(System.in);
		
		while(set.size() < 3) {
			num = (int) ((Math.random() * 9 + 1));
			boolean isAdd = set.add(num);
			
			if(isAdd) {
				list2.add(num);
			}
		}
		
		System.out.println(set);
		System.out.println(list2);
		
		
		
		int count = 0;
		do {
			strike = 0;
			ball = 0;
			count++;
			int i = 1;
			while(list.size() < 3) {
				System.out.print(i + "번째 숫자 입력 >> ");
				input = Integer.parseInt(s.nextLine());
				list.add(input);
				i++;
			}
			
			if(list.get(0) == list2.get(0)) {
				strike++;
			}else if(list.get(0) == list2.get(1) || list.get(0) == list2.get(2)) {
				ball++;
			}
			
			if(list.get(1) == list2.get(1)) {
				strike++;
			}else if(list.get(1) == list2.get(0) || list.get(1) == list2.get(2)) {
				ball++;
			}
			
			if(list.get(2) == list2.get(2)) {
				strike++;
			}else if(list.get(2) == list2.get(0) || list.get(2) == list2.get(1)) {
				ball++;
			}
			
			System.out.println(strike +"S, " + ball + "B");
			
			list.clear();
		}while(strike != 3);
		
		System.out.println(count + "번의 시도 끝에 맞추셨습니다!");
	}
}
