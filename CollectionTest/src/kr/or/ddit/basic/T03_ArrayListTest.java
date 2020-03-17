package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *  문제) 5명의 사람 이름을 입력하여 ArrayList에 저장하고 이 중에 '김'씨 성의 이름을 출력하시오.
 *  	 (단, 입력은 Scanner를 이용하여 입력받는다.)
 */
public class T03_ArrayListTest {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		String name = null;
		int i = 0;
		
		while(i < 5) {
			System.out.print(i + "번째 이름을 입력하시오. >> ");
			name = s.nextLine();
			list.add(name);
			
			i++;
		}
		
		for(String str : list) {
			if(str.charAt(0) == '김') {
				System.out.println(str);
			}
		}
	}
}
