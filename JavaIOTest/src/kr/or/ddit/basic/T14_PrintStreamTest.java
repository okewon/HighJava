package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/*
 * 프린터 기능 제공 보조 스트림
 */
public class T14_PrintStreamTest {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print2.txt");
		
		//PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 OutputStream의 서브클래스이다.
		//PrintStream은 IOException을 발생시키지 않는다.
		//println()과 print()등 메서드 호출시마다 autoflush 기능 제공됨
		PrintStream out = new PrintStream(fos);
		//PrintStream out = new PrintStream(System.out); //콘솔 출력스트림으로 사용됨.
		
		out.print("안녕하세요, PrintStream입니다.\n");
		out.println("안녕하세요, PrintStream입니다.2");
		out.println("안녕하세요, PrintStream입니다.3");
	
		out.close();
		
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos2, "UTF-8"));
		writer.print("안녕하세요. PrintWriter입니다.\n\n");
		writer.println("안녕하세요. PrintWriter입니다.2");
		writer.println("안녕하세요. PrintWriter입니다.3");
		
		writer.close();
	}
}
