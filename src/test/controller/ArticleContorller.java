package test.controller;

import java.util.List;

import test.container.Container;
import test.dto.Article;
import test.dto.Member;
import test.service.ArticleService;

public class ArticleContorller extends Controller{

	private ArticleService articleService;

	public ArticleContorller() {
		articleService = Container.articleService;
	}

	public void doCommed(String com) {
		
		if (com.equals("article add")) {
			if (!Container.session.isLoginId()) {
				System.out.println("로그인 후 이용해 주세요.");
				return;
			}
			add();
		} else if (com.equals("article list")) {

			if (articleService.articleSize() <= 0) {
				System.out.println("게시물이 존재하지 않습니다.");
				return;
			}
			list();

		} else if (com.startsWith("article delete ")) {
			int input = Integer.parseInt(com.split(" ")[2]);
			if (articleService.articleSize() <= 0 || articleService.getLastId() < input) {
				System.out.println("게시물이 존재하지 않습니다.");
				return;
			}
			articleService.remove(input);
			System.out.println(input + "번째 글이 삭제되었습니다.");
		} else if (com.startsWith("article modify ")) {
			int input = Integer.parseInt(com.split(" ")[2]);
			modify(input);

		}

	}

	private void modify(int input) {
		
		System.out.printf("새 제목: ");
		String title = Container.scanner.nextLine().trim();
		System.out.printf("새 내용: ");
		String body = Container.scanner.nextLine().trim();
		
		int  i  = articleService.modify(title,body,input);
		System.out.println(i + "번째 글이 수정되었습니다.");
		
	}

	private void list() {
		List<Article> articles = articleService.getArticles();
		for (Article article : articles) {
			Member member = Container.memberService.getLoginMemberId(article.loginId);
			System.out.println(article.id + " / " + article.title + " / " + member.name);
		}

	}

	private void add() {
		System.out.printf("제목: ");
		String title = Container.scanner.nextLine().trim();

		System.out.printf("내용: ");
		String body = Container.scanner.nextLine().trim();

		articleService.add(Container.session.loginMemberId, title, body);

		System.out.println(articleService.getLastId() + "번째 글이 생성되었습니다.");

	}

}
