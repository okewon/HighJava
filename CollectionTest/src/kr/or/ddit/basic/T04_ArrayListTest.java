package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *  문제1) 5명의 별명을 입력하여 ArrayList에 저장하고 별명의 길이가 제일 긴 별명을 출력하시오.
 *  	  (단, 각 별명의 길이는 모두 다르게 입력한다.)
 *  문제2) 문제 1에서 별명의 길이가 같은 것을 여러개 입력했을 때에도 처리되도록 하시오.
 */
public class T04_ArrayListTest {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String alias = null;
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		
		for(int i = 1; i <= 5; i++) {
			System.out.print(i + "번째 사람의 별명을 입력하세요.");
			alias = s.nextLine();
			
			list.add(alias);
		}
		
		String temp = list.get(0);
		int max = 0;
		
		for(int i = 0; i < list.size() - 1; i++) {
			if(temp.length() < list.get(i + 1).length()) {
				max = list.get(i + 1).length();
			}
		}
		
		System.out.println(max);
		
		list.clear();
		
		for(int i = 1; i <= 5; i++) {
			System.out.print(i + "번째 사람의 별명을 입력하세요.");
			alias = s.nextLine();
			
			list.add(alias);
		}
		
		temp = list.get(0);
		max = 0;

		for(int i = 0; i < list.size() - 1; i++) {
			if(temp.length() == list.get(i + 1).length()) {
				max = list.get(i + 1).length();
			}
		}
		
		System.out.println(max);
	}
}
