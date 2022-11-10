package vn.com.gigo.exception;

public class AccountException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ACCOUNT NOT FOUND
	public AccountException(Long id) {
		super(String.format("Account with Id %l not found", id));
	}

}
