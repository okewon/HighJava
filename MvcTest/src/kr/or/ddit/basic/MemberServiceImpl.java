package kr.or.ddit.basic;

import java.util.List;

public class MemberServiceImpl implements IMemberService {
	
	//사용할 DAO의 객체 변수를 선언한다.
	private IMemberDao memDao;
	
	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();
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
