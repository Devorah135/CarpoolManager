package code;

public class NoMembersAddedException extends RuntimeException {
    public NoMembersAddedException() {
        super("No members added yet. Please add members to continue.\n");
    }

    public NoMembersAddedException(String message) {
        super(message);
    }
}
