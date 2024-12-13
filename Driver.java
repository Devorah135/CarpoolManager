package code;

public class Driver {

	private String name; 
	private int phoneNumber;
	private String vehicleDetails;
	private int availableSeats;
	private boolean isAvailable;
	//Add/Remove passengers.
	
	public Driver(String name, int phoneNumber, String vehicleDetails, int availableSeats, boolean isAvailable) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.vehicleDetails = vehicleDetails;
		this.availableSeats = availableSeats;
		this.isAvailable = isAvailable;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getVehicleDetails() {
		return vehicleDetails;
	}
	public void setVehicleDetails(String vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	 
	
	
}
