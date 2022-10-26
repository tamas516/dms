package hu.nye.dms.dms.exception;

public class LoginFailedException extends RuntimeException{

    public String message;

  public LoginFailedException() {
  }

  public LoginFailedException(String message, String message1) {
    super(message);
    this.message = message1;
  }
}
