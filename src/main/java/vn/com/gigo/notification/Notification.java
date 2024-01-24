package vn.com.gigo.notification;

import java.time.LocalDateTime;

public class Notification {
	private Object content;
	
	
	public Notification(Object content) {
		super();
		this.content = content;
	}

	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	
	
}
