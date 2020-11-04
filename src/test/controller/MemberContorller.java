package test.controller;

import test.container.Container;
import test.dto.Member;
import test.service.MemberService;

public class MemberContorller extends Controller{

	private MemberService memberService;

	public MemberContorller() {
		memberService = Container.memberService;
	}

	public void doCommed(String com) {
		if (com.equals("member join")) {
			join();
		} else if (com.equals("member login")) {
			login();
		}
		else if (com.equals("member whoami")) {
			whoami();
		}
		else if (com.equals("member logout")) {
			logout();
		}

	}

	private void logout() {
		if(!Container.session.isLoginId()) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		Container.session.loginMemberId = 0;
		System.out.println("로그아웃 되었습니다.");
		
	}

	private void whoami() {
		if(!Container.session.isLoginId()) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		Member member = memberService.getLoginMemberId(Container.session.loginMemberId);
		System.out.println("로그인 아이디: " + member.loginId);
		System.out.println("이름: " + member.name);
	}

	private void login() {
		if(Container.session.isLoginId()) {
			System.out.println("이미 로그인 중 입니다.");
			return;
		}
		String loginId;
		String loginPw;

		int loginIdMax = 3;
		int loginIdCount = 0;
		boolean loginIdValid = false;

		Member member = null;
		while (true) {
			if (loginIdCount >= loginIdMax) {
				System.out.println("로그인 실패");
				break;
			}
			System.out.printf("로그인 아이디: ");
			loginId = Container.scanner.nextLine().trim();
			member = memberService.getLoginId(loginId);
			if (member == null) {
				System.out.println("일치하는 아이디가 없습니다.");
				loginIdCount++;
				continue;
			}
			loginIdValid = true;
			break;
		}
		if (loginIdValid == false) {
			return;
		}

		int loginPwMax = 3;
		int loginPwCount = 0;
		boolean loginPwValid = false;

		while (true) {
			if (loginPwCount >= loginPwMax) {
				System.out.println("로그인 실패");
				break;
			}
			System.out.printf("로그인 비번: ");
			loginPw = Container.scanner.nextLine().trim();
			if (!member.loginPw.equals(loginPw)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				loginPwCount++;
				continue;
			}
			loginPwValid = true;
			break;

		}
		if (loginPwValid == false) {
			return;
		}
		System.out.println(member.loginId + "님이 로그인하였습니다.");
		Container.session.loginMemberId = member.id;
	}

	private void join() {
		String loginId = "";
		String loginPw = "";
		String name = "";

		int loginIdMax = 3;
		int loginIdCount = 0;

		while (true) {
			if (loginIdCount >= loginIdMax) {
				System.out.println("다음에 다시 시도해주세요.");
				return;
			}
			System.out.printf("로그인 아이디: ");
			loginId = Container.scanner.nextLine().trim();
			if (loginId.length() == 0) {
				System.out.println("아이디를 입력하세요.");
				loginIdCount++;
				continue;
			} else if (memberService.lgoinIdOver(loginId) == false) {
				System.out.println("이미 존재하는 아이디 입니다.");
				loginIdCount++;
				continue;
			}
			break;
		}
		int loginPwMax = 3;
		int loginPwCount = 0;
		while (true) {
			if (loginPwCount >= loginPwMax) {
				System.out.println("다음에 다시 시도해주세요.");
				return;
			}
			System.out.printf("로그인 비번: ");
			loginPw = Container.scanner.nextLine().trim();
			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력하세요.");
				loginPwCount++;
				continue;
			}
			break;
		}
		int loginNameMax = 3;
		int loginNameCount = 0;
		while (true) {
			if (loginNameCount >= loginNameMax) {
				System.out.println("다음에 다시 시도해주세요.");
				return;
			}
			System.out.printf("이름: ");
			name = Container.scanner.nextLine().trim();
			if (name.length() == 0) {
				System.out.println("비밀번호를 입력하세요.");
				loginNameCount++;
				continue;
			}
			break;
		}

		memberService.join(loginId, loginPw, name);
		System.out.println(memberService.getLastId() + "번째 회원이 생성되었습니다.");

	}

}
