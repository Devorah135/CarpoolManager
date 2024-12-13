package code;

public class MemberNotFoundException extends RuntimeException {
	public MemberNotFoundException() {
		super("Member does not exist.\n");
	}
	
	public MemberNotFoundException(String msg) {
		super(msg);
	}
}
