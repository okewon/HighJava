package assignment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String choice = null;
		
		do {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 : ");
			choice = s.nextLine();
			
			switch(choice){
			case "1":
				Lotto.lotto();
				break;
			case "2":
				System.out.println("감사합니다.");
				return;
			}
			
			
			
		}while(!choice.equals("2"));
		
	}
	
	public static void lotto() {
		Scanner s = new Scanner(System.in);
		int money, divide, num;
		TreeSet<Integer> set = new TreeSet<>();
		TreeSet<Integer> answer = new TreeSet<>();
		
		System.out.println("Lotto 구입 시작");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		money = Integer.parseInt(s.nextLine());
		
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		
		while(answer.size() < 6) {
			answer.add((int)(Math.random() * 45 + 1));
		}
		System.out.println("answer : " + answer);
		
		for(int i = 0; i < money / 1000; i++) {
			while(set.size() < 6) {
				num = (int) (Math.random() * 45 + 1);
				set.add(num);
			}
			System.out.println("로또번호 " + (i + 1) + set); 
			Lotto.fx(set, answer);
			set.clear();
		}
		
		divide = money % 1000;
		
		System.out.println("받은 금액은 "+ money + "원이고 거스름돈은 " + divide + "원입니다.");
	}
	
	public static void fx(Set<Integer> set, Set<Integer> answer) {
		ArrayList<Integer> setList = new ArrayList<>(set);
		ArrayList<Integer> answerList = new ArrayList<>(answer);
		int count = 0;
		
		for(int i = 0; i < setList.size(); i++) {
			for(int j = 0; j < answerList.size(); j++) {
				if(setList.get(i) == answerList.get(j)) {
					count++;
				}
			}
		}
		
		switch(count) {
		case 6:
			System.out.println("1등");
			break;
		case 5:
			System.out.println("2등");
			break;
		case 4:
			System.out.println("3등");
			break;
		case 3:
			System.out.println("4등");
			break;
		case 2:
		case 1:
		case 0:
			System.out.println("꽝");
			break;
		}
	}
}
