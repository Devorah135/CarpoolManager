package code;

public class BalanceEntry {
    Member member;
    double amount;

    public BalanceEntry(Member member, double amount) {
        this.member = member;
        this.amount = amount;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
