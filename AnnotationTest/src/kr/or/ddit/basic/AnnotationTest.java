package kr.or.ddit.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
/*
 * Java Reflection에 대하여...
 * 
 * 1.리플렉션은 클래스 또는 멤버변수, 메서드 생성자에 대한 정보를 가져오거나 수정할 수 있다.
 * 2.Reflection API는 java.lang.reflect패키지와 java.lang.Class를 통해 제공된다.
 * 3.java.lang.Calss의 주요 메서드
 * 	 - getName, getClass, getInterfaces(), getModifiers()
 * 4.java.lang.reflect패키지의 주요 클래스
 * 	 - Field, Method, Constructor, Modifier 등
 * 5.Reflection API를 이용하면 클래스의 private메서드나 변수에 접근 가능함(보안위험)
 * 6.Reflection API의 기능은 뛰어나지만, 약간의 오버헤드가 발생한다.(느린수행속도, 보안취약성, 권한문제 등) 그러므로, 가급적 수단으로 사용하도록 고려되어야함
 */
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		System.out.println(PrintAnnotation.id);
		
		//reflection기능을 이용한 메서드 실행
		Method[] declareMethods = Service.class.getDeclaredMethods();
		
		for(Method m : declareMethods) {
			System.out.println(m.getName()); //메서드명 출력
			for(int i = 0; i < m.getDeclaredAnnotation(PrintAnnotation.class).count(); i++) {
				System.out.println(m.getDeclaredAnnotation(PrintAnnotation.class).value());
			}
			System.out.println();
			
			Class<Service> clazz = Service.class;
			
			try{
				Service service = (Service) clazz.newInstance();
				m.invoke(service);
			}catch (InstantiationError e) {
				e.printStackTrace();
			}
		}
	}
}
