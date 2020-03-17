package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 *  문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버변수로 갖는 Student클래스를 만든다
 *       생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 *       
 *       이 Student객체들은 List에 저장하여 관리한다.
 *       List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 *       (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 *       (학번의 정렬기준은 Student 클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */
public class T08_StudentTest {

	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("20170926", "옥혜원", 100, 80, 60));
		stuList.add(new Student("20170927", "옥경수", 100, 100, 100));
		stuList.add(new Student("20170928", "박미영", 90, 90, 90));
		stuList.add(new Student("20170929", "옥혜린", 80, 80, 80));
		stuList.add(new Student("20170930", "옥준원", 60, 60, 60));
		
		for(int i = 0; i < stuList.size(); i++) {
			stuList.get(i).setTotal(stuList.get(i).getKor() + stuList.get(i).getEng() + stuList.get(i).getMath());
		}
		
		System.out.println("정렬 전");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		System.out.println("===========================================================");
		
		System.out.println("정렬 후");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		
	}
}

class SortNum implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

class Student implements Comparable<Student>{
	
	private String num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private int rank;
	
	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", total=" + total + ", rank=" + rank + "]";
	}

	public Student(String num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(Student stu) {
		int compare = getNum().compareTo(stu.getNum());
		if(compare == 1) {
			return compare;
		}else if(compare == 0) {
			return 0;
		}else {
			return -1;
		}
	}
	
	
	
	
}