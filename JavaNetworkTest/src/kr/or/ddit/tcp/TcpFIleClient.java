package kr.or.ddit.tcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TcpFIleClient {
	//클라이언트는 서버에 접속하여 서버가 보내주는 파일을 D 드라이브의 C_Lib폴더에 저장한다.
	private Socket socket;
	private InputStream in;
	private FileOutputStream fout;
	
	public void clientStart() {
		File file = new File("d:/C_Lib/Tulips.jpg");
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("파일 다운로드 시작...");
			
			fout = new FileOutputStream(file);
			in = socket.getInputStream();
			
			byte[] tmp = new byte[1024];
			int length = 0;
			while((length = in.read(tmp)) != -1) {
				fout.write(tmp, 0, length);
			}
			
			fout.flush();
			System.out.println("파일 다운로드 완료...");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(in != null) {
				try {
					in.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fout != null) {
				try {
					fout.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket != null) {
				try {
					socket.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new TcpFIleClient().clientStart();
	}
}
