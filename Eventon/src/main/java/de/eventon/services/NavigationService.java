package de.eventon.services;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean
public class NavigationService {
	
	public NavigationService() {
		// TODO Auto-generated constructor stub
	}
	
	public String home(){
		return Pages.HOME.toString();
	}
	
	public String login(){
		return Pages.LOGIN.toString();
	}
	
	public String register(){
		return Pages.REGISTER.toString();
	}
	
	public String userProfile(){
		return Pages.USERPROFILE.toString();
	}
	
	public enum Pages {
		LOGIN("login.jsp"), REGISTER("register.jsp"), HOME("index.jsp"), USERPROFILE("user.jsp");

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
