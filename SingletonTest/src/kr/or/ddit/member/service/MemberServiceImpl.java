package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	//사용할 DAO의 객체 변수를 선언한다.
	private MemberDaoImpl memDao;
	private static MemberServiceImpl instance;
	
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(instance == null) {
			instance = new MemberServiceImpl();
		}
		return instance;
	}

	@Override
	public int insetMember(MemberVO mv) {
		return memDao.insetMember(mv);
	}

	@Override
	public boolean getMember(String memId) {
		return memDao.getMember(memId);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return memDao.getAllMemberList();
	}

	@Override
	public int updateMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

}
