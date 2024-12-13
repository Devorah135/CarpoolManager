package code;
import java.util.*;

public class CarpoolManager {
	
	private ArrayList<Member> members;
	private Member currMember; 
	private int turn;
	private int numChildren;

	public CarpoolManager() {
		this.members = new ArrayList<>();
		this.numChildren = 0;
	}
	
	public void setCurrMember(Member currMember) {
		this.currMember = currMember;
		this.numChildren += currMember.getNumChildren();
	}
	
	public Member getCurrMember() {
		return this.currMember;
	}
	
	public ArrayList<Member> getMembers() {
		return this.members;
	}
	
	public int getNumMembers() {
		return members.size();
	}

	public Member getMemberByID(int ID) {
		for(Member m : members) {
			if(m.getID() == ID) {
				return m;
			}
		}

		return null;
	}

	public void addMember(Member m) {
		members.add(m);
		this.numChildren += m.getNumChildren();
	}
	
	public boolean deleteMember(String name) {
		boolean isMember = false;
		int memberIndex = 0;
		for(int ii = 0; ii < members.size(); ii++) {
			if(name.equals(members.get(ii).getName())) {	
				isMember = true;
				memberIndex = ii;
				//if name of member was found, will exit loop and hold index of member
				break;
			}
		}
		
		if(isMember == true) {
			this.numChildren -= members.get(memberIndex).getNumChildren();
			members.remove(memberIndex);
			return true;
		}
		return false;
	}
	
	public void addChildren(int id, int numChildren) {
		if(id < 0) {
			throw new IllegalArgumentException();
		}
		
		boolean isMember = false;
		int memberIndex = 0;
		for(int ii = 0; ii < members.size(); ii++) {
			if(id == members.get(ii).getID()) {	
				isMember = true;
				memberIndex = ii;
				//if id of member was found, will exit loop and hold index of member
				break;
			}
		}
		
		if(isMember == true) {
			members.get(memberIndex).addChildToNumChildren(numChildren);
			this.numChildren += numChildren;
		}
	}
	
	public void removeChildren(int id, int numChildren) {
		if(id < 0) {
			throw new IllegalArgumentException();
		}
		
		boolean isMember = false;
		int memberIndex = 0;
		for(int ii = 0; ii < members.size(); ii++) {
			if(id == members.get(ii).getID()) {	
				isMember = true;
				memberIndex = ii;
				//if id of member was found, will exit loop and hold index of member
				break;
			}
		}
		
		if(isMember == true) {
			members.get(memberIndex).removeChildrenFromNumChildren(numChildren);
			this.numChildren -= numChildren;
		}
	}

	public int getNumChildren() {
		return numChildren;
	}

	public double getCarpoolTotalCost() {
		double total = 0;
		for(Member m : members) {
			total += m.getCostTotal();
		}

		return total;
	}
}
