package de.eventon.services;

import java.util.Stack;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class NavigationService {
	
	private Stack<Pages> pageStack;
	
	public NavigationService() {
		pageStack = new Stack<Pages>();
	}
	
	public String home(){
		return Pages.HOME.toString();
	}
	
	public String searchEvents() {
		return Pages.STAY.toString();
	}
	
	public String login(){
		return Pages.LOGIN.toString();
	}
	
	public String loginSuccessful() {
		return Pages.HOME.toString();
	}
	
	public String loginFailed() {
		return Pages.LOGIN.toString();
	}
	
	public String cancelLogin() {
		return Pages.HOME.toString();
	}
	
	public String logout() {
		return Pages.STAY.toString();
	}
	
	public String register(){
		return Pages.REGISTER.toString();
	}
	
	public String userProfile(){
		return Pages.USERPROFILE.toString();
	}
	
	public Stack<Pages> getPageStack() {
		return pageStack;
	}

	public void setPageStack(Stack<Pages> pageStack) {
		this.pageStack = pageStack;
	}

	public enum Pages {
		STAY("#"), LOGIN("login.jsp"), REGISTER("register.jsp"), HOME("index.jsp"), USERPROFILE("user.jsp");

		private String value;

		private Pages(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}
}
