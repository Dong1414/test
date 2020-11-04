package test.container;

import java.util.Scanner;

import test.dao.ArticleDao;
import test.dao.MemberDao;
import test.service.ArticleService;
import test.service.MemberService;

public class Container {

	public static Scanner scanner;
	public static Session session;
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	
	
	
	static {
		scanner = new Scanner(System.in);
		session = new Session();
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
		memberService = new MemberService();
	}

}
