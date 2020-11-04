package test.dao;

import java.util.ArrayList;
import java.util.List;

import test.dto.Member;

public class MemberDao {
	private int lastId;
	private List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
		lastId = 0;
		
		join("1", "1", "길동");
		join("2", "2", "명진");
	}

	public int join(String loginId, String loginPw, String name) {
		Member member = new Member();

		member.id = lastId + 1;
		member.loginId = loginId;
		member.loginPw = loginPw;
		member.name = name;

		members.add(member);
		lastId = member.id;
		return member.id;
	}

	public int getLastId() {

		return lastId;
	}

	public Member getLoginId(String loginId) {

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public boolean loginIdOver(String loginId) {

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	public Member getLoginMemberId(int id) {
		for(Member member : members) {
			if(member.id == id) {
				return member;
			}
		}
		return null;
	}

}
