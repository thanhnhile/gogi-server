package vn.com.gigo.utils;

public enum RoleType {
	ROLE_USER(1),
	ROLE_ADMIN(2),
	ROLE_EMPLOYEE(3);
	private Integer value;
	private RoleType(Integer value) {
		this.setValue(value);
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
