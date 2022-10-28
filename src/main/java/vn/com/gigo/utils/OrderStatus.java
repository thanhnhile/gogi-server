package vn.com.gigo.utils;

public enum OrderStatus {
	IN_PROGRESS(0), DELIVERING(1), SUCCESS(2), CANCELED(3);

	private int value;

	private OrderStatus(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
