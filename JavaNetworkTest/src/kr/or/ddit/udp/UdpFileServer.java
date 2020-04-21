package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileServer {
	public static final int BUFFER_SIZE = 10000;

	public static void main(String[] args) {
		String serverIP = "127.0.0.1";
		int port = 8888;

		File file = new File("d:/D_Other/Tulips.jpg");
		DatagramSocket ds = null;
		if (!file.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			System.exit(0);
		}
		long fileSize = file.length();
		long totalReadyBytes = 0;

		double startTime = 0;
		try {
			ds = new DatagramSocket();
			InetAddress serverAddr = InetAddress.getByName(serverIP);
			startTime = System.currentTimeMillis();
			String str = "start"; // 전송시작을 알려주기 위해서...
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);

			ds.send(dp);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[BUFFER_SIZE];

			str = String.valueOf(fileSize); // 총 파일크기 정보
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);

			ds.send(dp);

			while (true) {
				Thread.sleep(10); // 패킷 전송간의 간격을 주기 위해서...
				int readBytes = fis.read(buffer, 0, buffer.length);
				if (readBytes == -1) {
					break;
				} 
				// 읽어온 파일내용 패킷에 담기
				dp = new DatagramPacket(buffer, readBytes, serverAddr, port);

				ds.send(dp);
				totalReadyBytes += readBytes;
				System.out.println("진행상태 : " + totalReadyBytes + "/" + fileSize + " Bytes("
						+ (totalReadyBytes * 100 / fileSize) + " %)");
			}

			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime) / 1000;
			double transferSpeed = (fileSize / 1000) / diffTime;

			System.out.println("time : " + diffTime + " seconds");
			System.out.println("평슌 전송 속도 : " + transferSpeed + " KB/s");

			str = "end"; // 전송이 완료되었음을 알리기 위한 문자열
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);

			ds.send(dp);
			System.out.println("전송 완료...");
			fis.close();
			ds.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
