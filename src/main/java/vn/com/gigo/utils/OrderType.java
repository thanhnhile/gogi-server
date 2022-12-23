package vn.com.gigo.utils;

public enum OrderType {
	OFFLINE(0),
	ONLINE(1);
	
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
