package test;

import java.util.Scanner;

import test.container.Container;
import test.controller.ArticleContorller;
import test.controller.Controller;
import test.controller.MemberContorller;

public class App {
	MemberContorller memberContorller;
	ArticleContorller articleContorller;
	
	public App() {
		memberContorller = new MemberContorller();
		articleContorller = new ArticleContorller();
	}
	
	public void run() {
		Scanner scan = Container.scanner;
		
		while (true) {
			System.out.printf("명령어) ");
			String com = scan.nextLine();

			if (com.equals("system exit")) {
				System.out.println("프로그램 종료");
				break;
			}
			Controller controller = getControllerBycmd(com);
			controller.doCommed(com);
			
			
		}
		scan.close();
	}

	private Controller getControllerBycmd(String com) {
		if (com.startsWith("member ")) {
			return memberContorller;
		} else if (com.startsWith("article ")) {
			return articleContorller;
		}
		return null;
	}

}
