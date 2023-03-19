package vn.com.gigo.notification;

public class Notification {
	private Long receiverId;
	private Object content;
	
	
	public Notification(Long receiverId, Object content) {
		super();
		this.receiverId = receiverId;
		this.content = content;
	}
	
	
	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	
	
}
