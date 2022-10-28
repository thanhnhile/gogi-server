package vn.com.gigo.utils;

public enum OrderType {
	OFFLINE(1),
	ONLINE(2);
	
	private int value;
	
	private OrderType(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
