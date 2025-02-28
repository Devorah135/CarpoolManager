# Carpool Manager

## Overview

Carpool Manager is a console-based application designed to help manage carpooling for a group of people. The application tracks members, manages expenses (such as tolls, gas, and emergency expenses), and helps split costs evenly among all participants.

## Features

- Add new members to the carpool, including personal details and car information.
- Record various types of expenses (Toll, Gas, Emergency Expense/Other) and associate them with members.
- Split expenses evenly among all members, taking into account whether they have a car or not.
- Remove members from the carpool.
- Reset all member costs after expenses are split.

## Usage

1. **Adding a Member**: You can add a member to the carpool by entering their details such as name, phone number, car information, and the number of children.
2. **Adding Expenses**: After a member is added, you can enter various expenses (Toll, Gas, Emergency) and associate them with the member. You can then split the total cost among the group.
3. **Splitting Costs**: If there is an accumulated cost, you can choose to split the cost among all members before performing further operations.
4. **Removing a Member**: You can delete a member from the carpool, and the application will check if any costs need to be settled first.

## Classes

### CarpoolManager

Handles the main functionality of managing the carpool, such as adding/removing members, adding expenses, and splitting costs.

### Member

Represents an individual member of the carpool. Holds information such as name, phone number, address, car details, and total cost.

### Address

Represents the address of a member, including street, city, state, and zip code.

### Cost2

Represents a single expense (e.g., toll, gas) associated with a member.

### BalanceEntry

Represents an entry in the balance sheet, used to track the amount a member owes or is owed after splitting costs.

## Example

```
Enter your name: John Doe
Enter your phone number: 123-456-7890
Enter the number of seats your car has: 4
Enter the number of children you have: 2
Member added successfully. JOHN DOE's ID is: 1.

Enter A for Toll, B for Gas, C for Emergency Expense/Other: A
Enter cost price (or 0 if none): 10.50
Cost ID is 1
Member's updated total cost is $10.50.

Would you like to split costs? (yes/no): yes
Final Payment Details:
- Member 1 owes: $20.50
- Member 2 is owed: $20.50
...
```
