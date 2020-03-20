package kr.or.ddit.Basic;

public class T07_WildCardTest {
/*
 * 	와일드 카드 예제
 * 	
 * 	<? extends T> => 와일드 카드의 상한 제한. T와 그 자손들만 가능
 * 	<? super T> => 와일드 카드의 하한 제한. T와 그 조상들만 가능 <Object 제외>
 * 	<?>			=> 모든 타입이 가능 <? extends Object>와 동일
 */
}
//일반인
class Person{
	String name;
	
	public Person(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "이름 : " + name;
	}
	
	
}
//근로자
class Worker extends Person{
	public Worker(String name) {
		super(name);
	}
}
//학생
class Student extends Person{
	public Student(String name) {
		super(name);
	}
}
//고등학생
class HighStudent extends Student{
	public HighStudent(String name) {
		super(name);
	}
}
//수강코드
class Course<T>{
	private String name;//코드명
	private T[] students;//수강생(제너릭 배열)
	
	public Course(String name, int capacity) {
		this.name = name;
		//타입 파라미터로 배열을 생성시 오브젝트 배열을 생성후, 타입 파라미터 배열로 캐스팅 처리해야함.
		students = (T[])(new Object[capacity]);
	}

	//코스명 조회
	public String getName() {
		return name;
	}
	//수강생 조회
	public T[] getStudents() {
		return students;
	}
	
	//수강생 등록
	public void add(T t) {
		for(int i = 0; i < students.length; i++) {
			if(students[i] == null) {//아직 등록되지 않은(빈) 자리인지 확인
				students[i] = t;
				break;
			}
		}
	}
	
}
