package test.service;

import test.container.Container;
import test.dao.MemberDao;
import test.dto.Member;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}
	
	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId,loginPw,name);
		 
	}

	public int getLastId() {
		
		return memberDao.getLastId();
	}

	public Member getLoginId(String loginId) {
		
		return memberDao.getLoginId(loginId);
	}

	public boolean lgoinIdOver(String loginId) {
		
		Member member =  memberDao.getLoginId(loginId);
		if(member != null) {
			return false;
		}
		return true;
	}

	public Member getLoginMemberId(int id) {
		
		return memberDao.getLoginMemberId(id);
	}
	
	
}
