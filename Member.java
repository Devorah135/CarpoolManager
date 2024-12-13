package code;
import java.util.ArrayList;

public class Member {

	private static int numMembers = 0;
	private int id;
	private String name;
	private String phoneNumber;
	private Address address;
	private int availableSeats;
	private boolean isAvailable;
	private int numChildren;
	private boolean absent = false;
	private boolean hasCar;
	private ArrayList<Cost2> costs;
	
	public Member() {
		costs = new ArrayList<>();
	}
	
	public Member(String name, String phoneNumber, Address addr,
			int availableSeats, int numChildren, boolean hasCar) {
		numMembers++;
		id = numMembers;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = addr;
		this.availableSeats = availableSeats;
		this.isAvailable = true;
		this.numChildren = numChildren;
		this.hasCar = hasCar;
		this.absent = false;
		costs = new ArrayList<>();
	}

	public static int getNumMembers() { 
		return numMembers; 
	}

	public int getID() { 
		return this.id; 
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addChildToNumChildren(int numChildren) {
		if(numChildren < 0) {
			throw new IllegalArgumentException();
		}
		
		this.numChildren += numChildren;
	}
	
	public void removeChildrenFromNumChildren(int numChildren) {
		if(numChildren < 0 || numChildren > this.numChildren) {
			throw new IllegalArgumentException("Invalid number of children being removed.");
		}
		
		this.numChildren -= numChildren;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getHasCar() {
		return this.hasCar;
	}
	
	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}
	
	public int getNumChildren() {
		return this.numChildren;
	}
	
	public ArrayList<Cost2> getAllCosts() {
		//copy cost object to return 
		//Cost costCopy = new Cost(cost);
		//return costCopy;
		return costs;
	}
	
	public Cost2 getCostByID(int id) {
		for(int i = 0; i < costs.size(); i++) {
			if(id == costs.get(i).getCostID()) {
				return costs.get(i);
			}
		}
		return null;
	}
	
	public void addCost(Cost2 cost) {
		costs.add(cost);
	}
	
	public double getCostTotal() {
		double total = 0;
		if(costs == null) {
			return 0;
		}
		else { 	
			for(int ctr = 0; ctr < costs.size(); ctr++) {
				total += costs.get(ctr).getCost();
			}
			return total;
		}
	}
	
	
	public boolean isAbsent() {
		//have a method that lets u set a child to be absent - child arraylist?
		return absent;
	}
	
	public void deleteOneFromNumMembers() {
		numMembers--;
	}
	
	public void resetCost() {
		if(!costs.isEmpty()) {
			costs.removeAll(costs);
			Cost2.resetCostCounter();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(name);
		str.append("\n\tPhone #: ");
		str.append(phoneNumber + "\n\t");
		str.append(address);
		str.append("\n\tHas car: " + (hasCar ? "Yes" : "No"));
		str.append("\n\tNumber of Children: " + numChildren);
		if(costs != null) {
			for(int ii = 0; ii < costs.size(); ii++) {
				str.append("\n\tCosts " + (ii + 1) + ": " + costs.get(ii));
				str.append("\n");
			}
		} 
		return str.toString();
	}	
}
