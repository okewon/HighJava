package kr.or.ddit.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberMain {
	//Service 객체 변수를 선언한다.
	private IMemberService service = new MemberServiceImpl();
	private Scanner s = new Scanner(System.in);

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 자료 검색.");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					diplayMemberAll();
					break;
				case 5 :  // 자료 검색
					getSearchMember();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	/*
	 * 회원 정보를 검색하는 메서드
	 */
	private void getSearchMember() {
		//검색할 회원 ID, 회원 이름, 전화번호, 주소 등을 입력하면 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
		//주소는 입력한 값이 포함만 되어도 검색 되도록 한다.
		//입력을 하지 않을 자료를 엔터키로 다음 입력으로 넘긴다.
		scan.nextLine(); //입력버퍼 지우기
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		System.out.print("회원 ID >> ");
		String memId = scan.nextLine().trim();
		
		System.out.print("회원 이름 >> ");
		String memName = scan.nextLine().trim();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.nextLine().trim();
		
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine().trim();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		//입력한 정보로 검색한 내용을 출력하는 부분...
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("ID\t이름\t전화번호\t주소");
		System.out.println("---------------------------------------------");

		List<MemberVO> memList = service.getSearchMember(mv);
		
		for(MemberVO mv2 : memList) {
			System.out.println(mv2.getMem_name() + "\t" + mv2.getMem_name() + "\t" + mv2.getMem_tel() + "\t" + mv2.getMem_addr());
			System.out.println("---------------------------------------------");
			System.out.println("출력 작업 끝");			
		}
	}

	/*
	 * 회원정보 삭제하는 메서드
	 */
	private void deleteMember() {
		int cnt = 0;
		System.out.println();
		System.out.print("삭제할 아이디를 입력하세요 >> ");
		String memId = scan.next();
		
		cnt = service.deleteMember(memId);
		
		if(cnt > 0) {
			System.out.println(memId + "회원 삭제 성공!");
		}else {
			System.out.println(memId + "회원 삭제 실패!!!");
		}
	}

	private void updateMember() {
		boolean chk = false; //중복여부 체크
		String memId;
		int cnt = 0;
		
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요.");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			chk = service.getMember(memId);
			if(!chk) {
				System.out.println("회원 ID가 " + memId + "인 회원은 이미 존재하지 않습니다.");
				System.out.println("다시 입력하세요.");
			}
		}while(!chk);
		
		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("새로운 회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();
		System.out.print("새로운회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		cnt = service.updateMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId + "회원의 정보를 수정했습니다.");
		}else {
			System.out.println(memId + "회원의 정보 수정 실패!!");
		}
	}

	/*
	 * 전체 회원을 출력하는 메서드
	 */
	private void diplayMemberAll() {
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("ID\t이름\t전화번호\t주소");
		System.out.println("---------------------------------------------");

		List<MemberVO> memList = service.getAllMemberList();
		
		for(MemberVO mv : memList) {
			System.out.println(mv.getMem_name() + "\t" + mv.getMem_name() + "\t" + mv.getMem_tel() + "\t" + mv.getMem_addr());
			System.out.println("---------------------------------------------");
			System.out.println("출력 작업 끝");			
		}
		
	}

	/*
	 * 회원 추가하는 메서드
	 */
	private void insertMember() {
		int cnt = 0;
		boolean chk = false; //중복여부 체크
		String memId;
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			chk = service.getMember(memId);
			
			if(chk) {
				System.out.println("회원 ID가 " + memId + "인 회원은 이미 존재합니다.");
				System.out.println("다시 입력하세요.");
			}
		}while(chk == true);
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		cnt = service.insetMember(mv);
		
		if(cnt > 0) {
			System.out.println(memId + "회원 추가 작업 성공!");
		}else {
			System.out.println(memId + "회원 추가 작업 실패!!!");
		}
	}

	public static void main(String[] args) {
		MemberMain memObj = new MemberMain();
		memObj.start();
	}
}
