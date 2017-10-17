package br.senac.rj.exercicio.exception;

public class ApoliceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3622399088129083911L;

	public ApoliceException() {
	}

	public ApoliceException(String message) {
		super(message);
	}

	public ApoliceException(Throwable cause) {
		super(cause);
	}

	public ApoliceException(String message, Throwable cause) {
		super(message, cause);
	}

}
