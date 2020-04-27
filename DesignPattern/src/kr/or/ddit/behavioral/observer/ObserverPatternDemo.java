package kr.or.ddit.behavioral.observer;

public class ObserverPatternDemo {
	public static void main(String[] args) {
		Subject subject = new Subject();
		
		new HexaObserver(subject);
		new OctalObserver(subject);
		new BinaryObserver(subject);
		
		System.out.println("처음 상태 변경하기 : 15");
		subject.setState(15);
		System.out.println("두번째 상태 변경하기 : 10");
		subject.setState(10);
	}
}
