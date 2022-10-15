package vn.com.gigo.exception;

public class AccountException extends RuntimeException {
	// ACCOUNT NOT FOUND
	public AccountException(Long id) {
		super(String.format("Account with Id %l not found", id));
	}

}
