package test.dao;

import java.util.ArrayList;
import java.util.List;

import test.dto.Article;

public class ArticleDao {
	private int lastId;
	List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
		lastId = 0;

		for (int i = 0; i < 10; i++) {
			add(i % 2 == 0 ? 1 : 2,"제목" + i, "내용" + i);
		}
	}

	public void add(int memberid, String title, String body) {
		Article article = new Article();

		article.id = lastId + 1;
		article.loginId = memberid;
		article.title = title;
		article.body = body;

		articles.add(article);
		lastId = article.id;
	}

	public int articleSize() {

		return articles.size();
	}

	public void List(int page) {

		if (page <= 1)
			page = 1;

		int term = 10;
		int start = articles.size() - 1;
		start -= (page - 1) * term;
		if (page > (start / term) + 1) {
			System.out.println(page + "페이지는 존재하지 않습니다.");
			return;
		}
		int end = start - (term - 1);
		if (end <= 0)
			end = 0;

		for (int i = start; i >= end; i--) {
			System.out.println(articles.get(i).id + " / " + articles.get(i).title);
		}

	}

	public int getLastId() {

		return lastId;
	}

	public void remove(int input) {		
		for(int i = 0; i < articles.size(); i++) {
			if(articles.get(i).id == input) {
				articles.remove(i);
				for(Article article : articles) {
					if(article.id >= input) {
						article.id -= 1;
						articles.set(i, article);
						i++;
					}
				}
				lastId--;
			}
		}

	}

	public List<Article> getArticles() {
		return articles;
	}

	public Article getArticle(int input) {
		
		return articles.get(input);
	}

	public int modify(String title, String body, int input) {
		Article article = new Article();
		article.id = articles.get(input-1).id;
		article.title = title;
		article.body = body;
		article.loginId = articles.get(input-1).loginId;
		articles.set(input-1, article);
		
		return article.id;
	}

}
