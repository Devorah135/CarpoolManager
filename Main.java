package code;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner kb = new Scanner(System.in);
		CarpoolManager carpool = new CarpoolManager();
//		initializeCarpoolForTesting(carpool);
		int choice = 0;
		final int TOTAL_MENU_OPTIONS = 8;

		do {
			choice = doSomething(carpool, kb, TOTAL_MENU_OPTIONS);
		} while (choice != TOTAL_MENU_OPTIONS);

	}

	private static void initializeCarpoolForTesting(CarpoolManager carpool) {
		Address add = new Address("135 harb e", "Lawrence", "NY", "11559");
		Member m1 = new Member("Devorah", "5166662702", add, 5, 1, false);
		Member m2 = new Member("Penina", "5162393991", add, 5, 2, true);
		Member m3 = new Member("Elisheva", "5166662702", add, 5, 1, true);
		carpool.addMember(m1);
		carpool.addMember(m2);
		carpool.addMember(m3);
		m1.addCost(new Cost2("gas", 40));
		m2.addCost(new Cost2("gas", 30));
		m3.addCost(new Cost2("gas", 30));
	}

	/**
	 * Method to display menu
	 *
	 * @return int choice - users choice of which menu item they want to do
	 */
	public static int displayMenu() {
		Scanner kb = new Scanner(System.in);
		System.out.println("1. View member list");
		System.out.println("2. Log expenses");
		System.out.println("3. Add a member");
		System.out.println("4. Split costs");
		System.out.println("5. Delete member");
		System.out.println("6. Add children to carpool");
		System.out.println("7. Remove children from carpool");
		System.out.println("8. Exit");

		int choice = -1;
		System.out.print("Enter your choice: ");
		try {
			choice = kb.nextInt();
			kb.nextLine();
		} catch (InputMismatchException e) {
			return -1;
		}

		return choice;
	}

	/**
	 * Method to do something based on user choice from displayMenu() includes input
	 * validation here
	 */
	public static int doSomething(CarpoolManager carpool, Scanner kb, int TOTAL_MENU_OPTIONS) {
		int choice = displayMenu();
		// input validation
		while (choice < 1 || choice > TOTAL_MENU_OPTIONS) {
			System.out.println("Invalid entry. Please enter a number from 1-6: ");
			choice = displayMenu();
		}

		switch (choice) {
		case 1:
			try {
				viewMemberList(carpool);
			} catch (NoMembersAddedException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
				logExpenses(carpool, kb);
			} catch (MemberNotFoundException | NoMembersAddedException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			addMember(carpool, kb);
			break;
		case 4:
			try {
				splitCosts(carpool, kb);
			} catch (NoMembersAddedException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 5:
			try {
				deleteMember(kb, carpool);
			} catch (NoMembersAddedException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 6:
			try {
				addChildrenToCarpool(kb, carpool);
			} catch (MemberNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 7:
			try {
				removeChildrenFromCarpool(kb, carpool);
			} catch (MemberNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 8:
			exit();
			break;
		default:
			System.out.println("Invalid choice.");
			displayMenu();
		}
		return choice;
	}

	/**
	 * Method that displays a list of all members
	 *
	 * @param carpool
	 */
	private static void viewMemberList(CarpoolManager carpool) {
		ArrayList<Member> members = carpool.getMembers();

		if (members.isEmpty()) {
			throw new NoMembersAddedException();
		} else {
			System.out.println("\nCarpool Members\n-------------------");
			for (int i = 0; i < members.size(); i++) {
				System.out.println((i + 1) + ": " + members.get(i).toString());
			}

			System.out.println();
		}
	}

	/**
	 * Method to add a child to the carpool
	 *
	 * @param carpool
	 * @param keyboard the Scanner input
	 */
	private static void addChildrenToCarpool(Scanner keyboard, CarpoolManager carpool) {
		int id = 0;
		int numChildren = 0;
		boolean repeat = true;
		while (repeat) {
			try {
				System.out.print("Enter ID for the member you would like to add children to in carpool: ");
				id = keyboard.nextInt();
				while (id < 0)
					id = keyboard.nextInt();
				System.out.print("Enter number of children you would like to add: ");
				numChildren = keyboard.nextInt();
				while (numChildren < 0)
					numChildren = keyboard.nextInt();

				repeat = false;
			} catch (InputMismatchException e) {
				System.out.println("Invalid ID number.");
			}
		}

		boolean foundID = false;
		for (Member m : carpool.getMembers()) {
			if (m.getID() == id) {
				foundID = true;
			}
		}

		if (foundID) {
			checkForPrevCost(carpool, keyboard, "adde");
			carpool.addChildren(id, numChildren);
			System.out.println("Children added successfully.");
		} else {
			throw new MemberNotFoundException();
		}
	}

	/**
	 * Method to remove children from the carpool
	 *
	 * @param carpool
	 * @param keyboard the Scanner input
	 */
	private static void removeChildrenFromCarpool(Scanner keyboard, CarpoolManager carpool) {
		int id = 0;
		int numChildren = 0;
		boolean repeat = true;
		while (repeat) {
			try {
				System.out.print("Enter ID for the member you would like to add children to in carpool: ");
				id = keyboard.nextInt();
				while (id < 0)
					id = keyboard.nextInt();
				
				repeat = false;
			} catch (InputMismatchException e) {
				System.out.println("Invalid ID number.");
			}
		}

		boolean foundID = false;
		for (Member m : carpool.getMembers()) {
			if (m.getID() == id) {
				foundID = true;
			}
		}

		boolean status = true;
		if (foundID) {
			do {
				try {
					System.out.print("Enter number of children you would like to remove: ");
					numChildren = keyboard.nextInt();
					while (numChildren < 0 || numChildren > carpool.getMemberByID(id).getNumChildren()) {
						System.out.print("Invalid number of children being remove. Please re-enter: ");
						numChildren = keyboard.nextInt();
					}
					status = false;
				}
				catch(InputMismatchException e) {
					System.out.println("Invalid number of children.");
					keyboard.nextLine();
				}
			}
			while(status);

			checkForPrevCost(carpool, keyboard, "remove");
			carpool.removeChildren(id, numChildren);
			System.out.println("Children removed successfully.");
		} else {
			throw new MemberNotFoundException();
		}
	}

	/**
	 * Method for a user to log their expenses
	 *
	 * @param carpool
	 * @param kb      the Scanner input
	 */
	private static void logExpenses(CarpoolManager carpool, Scanner kb) {
		int id = 0;
		// TODO add identifier for which member it is
		checkForEmptyCarpool(carpool);

		boolean repeat = true;
		while (repeat) {
			try {
				System.out.println("Enter ID for the member you would like to log an expense for: ");
				id = kb.nextInt();
				repeat = false;
			} catch (InputMismatchException e) {
				System.out.print("Invalid ID number.");
				kb.nextLine();
			}
		}

		boolean foundID = false;
		for (Member m : carpool.getMembers()) {
			if (m.getID() == id) {
				foundID = true;
				break;
			}
		}
		// if member is found in carpool, will add or update an expense
		if (foundID) {
			Member member = carpool.getMemberByID(id);
			kb.nextLine();
			System.out.print("Enter 1 to add an expense. Enter 2 to update an expense: ");

			int choice = 0;
			boolean validChoice = false;
			while (!validChoice) {
				try {
					choice = kb.nextInt();
					if (choice == 1 || choice == 2) {
						validChoice = true;
					} else { // for a number entered that isnt 1 or 2
						System.out.print("Invalid choice. Please enter 1 or 2: ");
					}
				} catch (InputMismatchException e) { // for an input entered that isnt an int
					System.out.print("Invalid choice. Please enter 1 or 2: ");
					kb.nextLine();
				}
			}

			if (choice == 1) {
				addExpenses(carpool, kb, member);
			} else {
				updateExpenses(carpool, kb, member);
			}
		} else {
			throw new MemberNotFoundException();
		}
	}

	private static void updateExpenses(CarpoolManager carpool, Scanner kb, Member member) {
		String costName;
		boolean status = true;
		double price = 0;
		System.out.print("Enter cost ID of cost you would like to update: ");

		do {
			try {
				int costID = kb.nextInt();
				kb.nextLine();

				while (costID < 0) {
					System.out.print("Invalid ID. Please re-enter: ");
					costID = kb.nextInt();
					kb.nextLine();
				}

				// Tries to find the cost by the given ID
				Cost2 cost = member.getCostByID(costID);

				if (cost != null) {
					boolean status1 = true;
					char reason = 0;

					do {
						try {
							System.out.print("Enter A for Toll, B for Gas, C for Emergency Expense/Other: ");
							String reasonInput = kb.nextLine().toUpperCase();

							// Checks if the input is not empty and take the first character
							if (!reasonInput.isEmpty()) {
								reason = reasonInput.charAt(0);

								// Validates the entered character
								while (reason != 'A' && reason != 'B' && reason != 'C') {
									System.out.println("Invalid choice. Please re-enter: ");
									reasonInput = kb.nextLine().toUpperCase();
									if (!reasonInput.isEmpty()) {
										reason = reasonInput.charAt(0);
									} else {
										System.out.println("No input entered. Please re-enter: ");
									}
								}
								status1 = false;
							} else {
								System.out.println("No input entered. Please re-enter: ");
							}
						} catch (InputMismatchException e) {
							System.out.println("Invalid data.");
							kb.nextLine();
						}
					} while (status1);

					// based on user input, will hold on to the full name of the expense
					if (reason == 'A') {
						costName = "Toll";
					} else if (reason == 'B') {
						costName = "Gas";
					} else {
						costName = "Emergency Expense/Other";
					}

					// Handles cost price input
					boolean validPrice = false;
					while (!validPrice) {
						try {
							System.out.print("Enter cost price (or 0 if none): ");
							price = kb.nextDouble();
							kb.nextLine();

							if (price < 0) {
								System.out.println("Invalid cost price. Please re-enter: ");
							} else {
								validPrice = true;
							}
						} catch (InputMismatchException e) {
							System.out.println("Invalid data entered. Please enter a valid number for the price.");
							kb.nextLine();
						}
					}

					// updates the cost object
					cost.setCost(price);
					cost.setCostName(costName);
					System.out.printf("%nMember's updated total cost is $%,.2f.%n", member.getCostTotal());

				} else {
					System.out.println(member.getName() + " has no logged cost with ID " + costID);
				}
				status = false;

			} catch (InputMismatchException e) {
				System.out.print("Invalid data entered. Please re-enter:");
				kb.nextLine();
			}
		} while (status);
	}

	private static void addExpenses(CarpoolManager carpool, Scanner kb, Member member) {
		// TODO add identifier for which member it is
		checkForEmptyCarpool(carpool);
		double price = 0;
		kb.nextLine();
		String costName;
		boolean status = true;

		boolean status1 = true;
		char reason = 0;

		do {
			try {
				System.out.print("Enter A for Toll, B for Gas, C for Emergency Expense/Other: ");
				String reasonInput = kb.nextLine().toUpperCase();

				// Checks if the input is not empty and take the first character
				if (!reasonInput.isEmpty()) {
					reason = reasonInput.charAt(0);

					// Validates the entered character
					while (reason != 'A' && reason != 'B' && reason != 'C') {
						System.out.println("Invalid choice. Please re-enter: ");
						reasonInput = kb.nextLine().toUpperCase();
						if (!reasonInput.isEmpty()) {
							reason = reasonInput.charAt(0);
						} else {
							System.out.println("No input entered. Please re-enter: ");
						}
					}
					status1 = false;
				} else {
					System.out.println("No input entered. Please re-enter: ");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid data.");
				kb.nextLine();
			}
		} while (status1);

		// Sets cost name based on the user's input
		if (reason == 'A') {
			costName = "Toll";
		} else if (reason == 'B') {
			costName = "Gas";
		} else {
			costName = "Emergency Expense/Other";
		}

		do {
			try {
				// Gets the cost price from the user
				System.out.print("Enter cost price (or 0 if none): ");
				price = kb.nextDouble();
				while (price < 0) {
					System.out.print("Invalid cost price. Please re-enter: ");
					price = kb.nextDouble();
				}
				status = false;
			} catch (InputMismatchException e) {
				System.out.println("Invalid data entered");
				kb.nextLine();
			}
		} while (status);
		// Creates a new cost and add it to the member
		Cost2 cost = new Cost2(costName, price);
		member.addCost(cost);
		System.out.print("Cost ID is " + cost.getCostID());

		System.out.printf("%nMember's updated total cost is $%,.2f.%n", member.getCostTotal());
	}

	/**
	 * Method to add a member to the carpool Will check to split costs first to make
	 * things even
	 *
	 * @param carpool
	 * @param kb
	 */
	private static void addMember(CarpoolManager carpool, Scanner kb) {
		if (checkForPrevCost(carpool, kb, "add"))
			return;

		// TODO Create method that adds a member to the carpool
		String name, phoneNumber;
		Address address;
		int numSeats = 0, numChildren = 0;
		System.out.println("Enter your name: ");
		name = kb.nextLine();
		while (name == null || name.trim().isEmpty()) {
			name = kb.nextLine();
		}
		System.out.println("Enter your phone number: ");
		phoneNumber = kb.nextLine();
		while (phoneNumber == null || phoneNumber.trim().isEmpty()) {
			phoneNumber = kb.nextLine();
		}
		address = getAddress(kb);

		boolean repeat = true;
		boolean hasCar = true;
		do {
			try {
				System.out.println("Enter the number of seats your car has: (Enter 0 if you don't have a car)");
				numSeats = kb.nextInt();
				kb.nextLine();
				if (numSeats == 0) {
					hasCar = false;
					System.out.println(
							"There is a fee for joining the carpool without a car. You will be charged an extra 10% of the total cost.");
				}

				// inner loop to validate number of children is greater than 0
				while (true) {
					System.out.println("Enter the number of children you have: ");
					numChildren = kb.nextInt();

					if (numChildren <= 0) {
						System.out.println("Error. Number of children must be at least 1.");
					} else {
						break;
					}
				}
				repeat = false;
				kb.nextLine();

				if (numChildren <= 0) {
					System.out.println("Error. Number of children must be at least 1.");
					kb.nextLine();
					numChildren = kb.nextInt();
				}

				repeat = false;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				kb.nextLine();
			}
		} while (repeat);

		Member member = new Member(name, phoneNumber, address, numSeats, numChildren, hasCar);
		carpool.addMember(member);

		System.out.printf("%nMember added successfully. %s's ID is: %d.%n", name.toUpperCase(), Member.getNumMembers());
	}

	/**
	 * Method to check if there is pre-existing cost
	 *
	 * @param carpool
	 * @param kb
	 * @return boolean - whether there is previous cost
	 */
	private static boolean checkForPrevCost(CarpoolManager carpool, Scanner kb, String msg) {
		boolean unsplitCost = false;
		for (Member m : carpool.getMembers()) {
			if (m.getCostTotal() > 0) {
				unsplitCost = true;
			}
		}
		int choice = 0;
		if (unsplitCost) {
			System.out.println("There is accumulated cost that has not been split yet. Would you like to "
					+ "split the cost before " + msg + "ing member/child? \n"
					+ "1 - split cost 2 - continue without splitting cost (member/child will be " + msg
					+ "d and costs will not be equally split)");
			boolean invalid = false;
			do {
				try {
					choice = kb.nextInt();
				} catch (NumberFormatException e) {
					System.out.println("Invalid entry. Please enter 1 or 2: ");
					invalid = true;
				}
			} while (invalid);
			kb.nextLine();

			switch (choice) {
			case 1:
				splitCosts(carpool, kb);
				return true;
			case 2:
				System.out.println("Continuing to " + msg + " member/child without splitting");
				return false;
			default:
				System.out.println("Invalid entry. Not " + msg + "ing member.");
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to delete a member from the carpool If there is prev cost, asks if the
	 * user wants to first split then delete member
	 *
	 * @param keyboard
	 * @param carpool
	 */
	public static void deleteMember(Scanner keyboard, CarpoolManager carpool) {
		checkForEmptyCarpool(carpool);

		if (checkForPrevCost(carpool, keyboard, "remove"))
			return;

		System.out.print("Enter name of member you would like to delete: ");
		String name = keyboard.nextLine();

		boolean removeSuccessful = carpool.deleteMember(name);
		if (removeSuccessful) {
			System.out.println(name + " removed.");
		} else {
			System.out.println("Member was not found. Could not remove.");
		}
	}

	/**
	 * Method to get address from user
	 * 
	 * @param kb - Scanner object
	 * @return address
	 */
	public static Address getAddress(Scanner kb) {
		Address addr = null;
		String street, city, state, zip;
		
		System.out.print("Enter street: ");
		street = kb.nextLine();
		while (street == null || street.trim().isEmpty()) {
			street = kb.nextLine();
		}
		System.out.print("Enter city: ");
		city = kb.nextLine();
		while (city == null || city.trim().isEmpty()) {
			city = kb.nextLine();
		}
		System.out.print("Enter state: ");
		state = kb.nextLine();
		while (state == null || state.trim().isEmpty()) {
			state = kb.nextLine();
		}		
		System.out.print("Enter zip code: ");
		zip = kb.nextLine();
		while (zip == null || zip.trim().isEmpty()) {
			zip = kb.nextLine();
		}

		// Keep allowing user to reenter if Address is invalid
		boolean repeat = true;
		while (repeat) {
			try {
				addr = new Address(street, city, state, zip);
				repeat = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.print("Enter street: ");
				street = kb.nextLine();
				System.out.print("Enter city: ");
				city = kb.nextLine();
				System.out.print("Enter state: ");
				state = kb.nextLine();
				System.out.print("Enter zip code: ");
				zip = kb.nextLine();
			}
		}

		// Return Address object created
		return addr;
	}

	/**
	 * Method to split the costs
	 *
	 * @param carpool
	 * @param kb      Scanner object
	 */
	private static void splitCosts(CarpoolManager carpool, Scanner kb) {

		int amountWithoutCar = 0;
		double noCarFee = carpool.getCarpoolTotalCost() * .10;
		checkForEmptyCarpool(carpool);
		double total = carpool.getCarpoolTotalCost();
		for (Member members : carpool.getMembers()) {
			if (!members.getHasCar()) {
				amountWithoutCar++;
			}
		}

		total -= noCarFee * amountWithoutCar;

		int totalChildren = carpool.getNumChildren();

		System.out.println("Member's Information:");
		printMemberInfo(carpool);
		getMemberConfirmation(carpool, kb);

		System.out.println("\n**********************");
		System.out.println("Final Payment Details:");
		double amtPerChild = total / (double) totalChildren;
		ArrayList<BalanceEntry> membersThatOwe = new ArrayList<>();
		ArrayList<BalanceEntry> membersThatAreOwed = new ArrayList<>();
		Map<Member, Double> balances = new HashMap<>();

		// LOOP THROUGH EACH MEMBER AND GET THEIR BALANCE, ADDING IT TO HASHMAP
		getEachMemberBalance(carpool, amtPerChild, balances);
		// loop through each balance entry and add either to creditors or debtors list
		addMemberAsCreditorOrDebtor(balances, membersThatAreOwed, membersThatOwe);

		// Now we can settle debts
		settleDebts(membersThatOwe, membersThatAreOwed);

		// resetting every members costs - costs object ArrayList will be null
		for (Member member : carpool.getMembers()) {
			member.resetCost();
		}
		System.out.println("All member cost reset to $0.00.");
		System.out.println("**********************\n");
	}

	private static void getEachMemberBalance(CarpoolManager carpool, double amtPerChild, Map<Member, Double> balances) {
		for (Member m : carpool.getMembers()) {
			// Their share is how much they laid out minus their share based on how many
			// children they have in the carpool
			double memberShare = amtPerChild * m.getNumChildren();
			if (!m.getHasCar()) {
				memberShare += carpool.getCarpoolTotalCost() * .10;
				}
			
			double paidAmount = m.getCostTotal();
			double balance = paidAmount - memberShare;
			balances.put(m, balance);
		}
	}

	private static void settleDebts(ArrayList<BalanceEntry> membersThatOwe,
			ArrayList<BalanceEntry> membersThatAreOwed) {
		for (BalanceEntry debtor : membersThatOwe) {
			if (debtor.amount < 0) {
				for (BalanceEntry creditor : membersThatAreOwed) {
					if (creditor.amount > 0) {
						double payment = Math.min(Math.abs(debtor.amount), creditor.amount);

						debtor.amount += payment; // reduce amt they owe
						creditor.amount -= payment; // reduce amt they are owed

						System.out.printf("%s (ID: %d) pays %s (ID: %d): $%,.2f%n", debtor.member.getName(),
								debtor.member.getID(), creditor.member.getName(), creditor.member.getID(), payment);
						if (debtor.amount == 0)
							break;
					}
				}
			}
		}
	}

	private static void addMemberAsCreditorOrDebtor(Map<Member, Double> balances,
			ArrayList<BalanceEntry> membersThatAreOwed, ArrayList<BalanceEntry> membersThatOwe) {
		for (Map.Entry<Member, Double> entry : balances.entrySet()) {
			Member member = entry.getKey();
			double balance = entry.getValue();
			if (balance > 0) {
				membersThatAreOwed.add(new BalanceEntry(member, balance));
			} else if (balance < 0) {
				membersThatOwe.add(new BalanceEntry(member, balance));
			}
		}
	}

	private static void getMemberConfirmation(CarpoolManager carpool, Scanner kb) {
		for (Member m : carpool.getMembers()) {
			boolean validResponse = false;
			while (!validResponse) {
				System.out.printf("Please confirm: Is the information for %s correct? (Yes/No)%n", m.getName());
				String confirmation = kb.nextLine().trim().toLowerCase(); // Trim and normalize input

				if (confirmation.equals("yes")) {
					System.out.println("Thank you for confirming.");
					validResponse = true; // Exit the loop for valid response
				} else if (confirmation.equals("no")) {
					System.out.println("Which information is incorrect?");
					validResponse = true; // Proceed after handling corrections
				} else {
					System.out.println("Invalid response. Please type 'Yes' or 'No'.");
				}
			}
		}
	}

	private static void printMemberInfo(CarpoolManager carpool) {
		for (Member m : carpool.getMembers()) {
			System.out.printf("%s | Cost: $%,.2f | Children: %d | Has Car: %s%n", m.getName(), m.getCostTotal(), m.getNumChildren(), (m.getHasCar() ? "Yes" : "No"));

		}
	}

	private static void checkForEmptyCarpool(CarpoolManager carpool) {
		if (carpool.getMembers().isEmpty()) {
			throw new NoMembersAddedException();
		}
	}

	/**
	 * Method to exit the program
	 */
	private static void exit() {
		// TODO Method for exiting the system
		System.out.println("Thank you for using Carpool Manager. Have a nice day!");
		System.exit(0);
	}
}
