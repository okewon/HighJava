package kr.or.ddit.basic;

public class T02_LambdaTest {
	public static void main(String[] args) {
		//람다식을 사용하지 않았을 경우
		LambdaTestInterface1 lam1 = new LambdaTestInterface1() {
			
			@Override
			public void test() {
				// TODO Auto-generated method stub
				System.out.println("안녕하세요.");
				System.out.println("익명 구현 객체 방식입니다.");
			}
		};
		lam1.test();//메서드 호출
		
		LambdaTestInterface1 lam2 = ()->{
			System.out.println("반가워요\n람다식으로 처리하는 방식입니다.");
		};
		lam2.test();
		System.out.println("--------------------------------------------------------------");
		//===================================================================================
/*
 	람다식의 작성 방법
 	기본형식)(자료형 이름 매개변수명,...) -> {실행문들; ...}
 	
 	1)매개변수의 '자료형 이름'은 생략할 수 있다.
 		예) (int a) -> { System.out.println(a); }
 		   (a) -> { System.out.println(a); }
 		   
 	2)매개변수가 1개일 경우에는 괄호'()'를 생략할 수 있다.
 	(단, '자료형 이름'을 지정할 경우에는 괄호를 생략할 수 없다.)
 		예) a -> { System.out.println(a); }
 		
 	3)'실행문'이 1개일 경우애눈 '{}'를 생략할 수 있다.
 	(이때 문장의 끝을 나타내는 세미콜론(;)도 생략한다)
 		예) a -> System.out.println(a)
 	
 	4)매개변수가 하나도 없으면 괄호 '()'를 생략할 수 없다.
 		예) () -> System.out.println("안녕")
 		
 	5)반환값이 있을 경우에는 return 명령을 사용한다.
 		예) (a, b) -> { return a+b; }
 		   (a, b) -> return a+b;
 		   
 	6) 실행문에 return문만 있을 경우 return 명령과 '{}'를 생략 할 수 있다.
     	예) (a,b) -> a+b     
 	
 */
	}
}
