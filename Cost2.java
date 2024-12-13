package code;

public class Cost2 {
	private String costName;
	private int costID;
	private static int numCost = 0;
	private double cost;
	
	public Cost2(String name,double cost) {
		this.costName = name;
		costID = ++numCost;
		setCost(cost);
	}
	
	//setters
	public void setCost(double cost) {
		if(cost < 0) {
			throw new IllegalArgumentException("Invalid cost.");
		}
		this.cost = cost;
	}
	
	public void setCostName(String name) {
		this.costName = name;
	}
	
	//getters
	public double getCost() {
		return cost;
	}
	
	public int getCostID() {
		return costID;
	}
	
	public String getCostName() {
		return costName;
	}
	
	//static method to reset the costID so will reset when all cost are deleted
    public static void resetCostCounter() {
        numCost = 0;  
    }
	
	/** @return Overriden toString() method */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Cost ID: " + costID);
		str.append(", Cost name: " + costName);
		str.append(String.format(", Cost value: $%.2f",cost));
		return str.toString();
	}
}
