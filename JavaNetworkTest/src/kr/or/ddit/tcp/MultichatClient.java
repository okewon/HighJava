package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultichatClient {
	Scanner scan = new Scanner(System.in);
	private String nickName; //대화명
	
	//client 시작
	public void clientStart() {
		//대화명 입력받기
		System.out.println("대화명 >> ");
		nickName = scan.next();
		
		Socket socket = null;
		try {
			String serverIP = "192.168.206.2";
			socket = new Socket(serverIP, 7777);
			
			System.out.println("서버에 연결되었습니다.");
			
			//송신용 쓰레드 생성
			ClientSender sender = new ClientSender(socket, nickName);
			
			//수신용 쓰레드 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//메세지를 전송하는 Thread
	class ClientSender extends Thread{
		Socket socket;
		DataOutputStream dos;
		String name;
		Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket, String name) {
			this.socket = socket;
			this.name = name;
			
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				//시작하자마자 자신의 대화명을 서버로 전송
				if(dos != null) {
					dos.writeUTF(name);
				}
				
				while(dos != null) {
					//키보드로 입력받은 메세지를 서버로 전송
					dos.writeUTF("[" + name + "]" + scan.nextLine());
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}//송신용 쓰레드 끝...
	//수신용 Thread 클래스 정의
	class ClientReceiver extends Thread{
		Socket socket;
		DataInputStream dis;
		
		//생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				dis = new DataInputStream(socket.getInputStream());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dis != null) {
				try {
					//서버로부터 수신한 메세지 출력하기
					System.out.println(dis.readUTF());
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MultichatClient().clientStart();
	}
}
