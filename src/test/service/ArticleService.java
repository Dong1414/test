package test.service;

import java.util.List;

import test.container.Container;
import test.dao.ArticleDao;
import test.dto.Article;

public class ArticleService {
		private ArticleDao articleDao;
	public ArticleService() {
		articleDao = Container.articleDao;
	}
	public void add(int memberid, String title, String body) {
		articleDao.add(memberid,title, body);
		
	}
	public int articleSize() {
		
		return articleDao.articleSize();
	}
	public void List(int page) {
		articleDao.List(page);
		
	}
	public int getLastId() {
		
		return articleDao.getLastId();
	}
	public void remove(int input) {
		articleDao.remove(input);
		
	}
	public  List<Article> getArticles() {
		return articleDao.getArticles();
		
	}
	public Article getArticleIndex(int input) {
		
		return articleDao.getArticle(input);
	}
	public int modify(String title, String body, int input) {
		return articleDao.modify(title,body,input);
		
	}
	
	

}
