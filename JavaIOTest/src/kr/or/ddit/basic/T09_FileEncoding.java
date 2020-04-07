package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncoding {
	public static void main(String[] args) {
		//파일 인코딩을 이용하여 읽어오기
		//InputStreamReader스트림 => 바이트기반 스트림을 문자기반 스트림으로 변환해주는 보조 스트림
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			/*
			 * FileInputStream객체를 생성한 후 이 객체를 매개변수로 받는 InputStreamReader객체를 생성한다.
			 */
			//fis = new FileInputStream("d:/D_Other/test_ansi.txt");
			fis = new FileInputStream("d:/D_Other/test_utf8.txt");
			
			//isr = new InputStreamReader(fis, "cp949");
			//isr = new InputStreamReader(fis, "utf-8");
			//isr = new InputStreamReader(fis, "euc-kr");
			isr = new InputStreamReader(fis, "ms949");
			
			int c;
			
			while((c = isr.read()) != -1) {
				System.out.println((char) c);
			}
			System.out.println();
			System.out.println("출력 끝...");
			
			isr.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
