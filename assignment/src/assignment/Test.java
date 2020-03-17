package assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Test {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		Scanner s = new Scanner(System.in);
		String name = null;
		int i = 0;
		
		do {
			System.out.println("이름을 입력하시오. (종료 : Q) >> ");
			name = s.nextLine();
			
			if(i % 2 == 0) {
				list.add(i, name);
			} else {
				list.add(name);
			}

		} while(!name.equals("Q"));
		
		list2.addAll(list);
		
		list.add("옥혜원");
		list.add("신나라");
		list.add("나창수");
		list.add("곽녕은");
		
		System.out.println(list.indexOf("신나라"));
		System.out.println(list.lastIndexOf("옥혜원"));
		
		
		for(int j = 0; j < list.size(); j++) {
			String str = list.get(j);
			System.out.println("이름 : " + str);
		}
		
		System.out.println("삭제하고자 하는 사람의 이름을 입력하세요.");
		name = s.nextLine();
		
		for(int j = 0; j < list.size(); i++) {
			if(name.equals(list.get(i))) {
				System.out.println(name + "이 삭제되었습니다.");
				list.remove(i);
			}
		}
		 
		System.out.println("변경하고자 하는 사람의 이름을 입력하세요.");
		name = s.nextLine();
		
		for(int j = 0; j < list.size(); i++) {
			if(name.equals(list.get(i))) {
				list.set(i, name);
				System.out.println(i + "번째 index의 이름이 " + name + "으로 변경되었습니다.");
			}
		}
		
		List<String> list3 = new ArrayList<>();
		list3 = list2.subList(0, 0);
		
	}
}
