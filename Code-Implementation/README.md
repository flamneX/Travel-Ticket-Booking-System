## Getting Started

Welcome to the partial code implementation of the Travel Ticket Booking System

This System includes:
- Create booking:
  - To select from 3 options (Existing user, Register as new user, Proceed as guest).
  - To select day and time of the travel.
  - To select travel from and to station.
  - To select payment method.
- Store details of the user (registered as new user and existing user).
- Add a new user with name, id, email and phone number and store details to a text file.
- Reads the user details from a text file.
- Add a guest details into a text file (name and email).
- Calculate fare based on passenger type, quantity(ies), travel time & day, and distance.
  - Rate according to distance is shown below.
  - Distance should get from route info module.
  - Discount/Surcharge should get from apply discount/surcharge module.
- Get route distance based on start and end station.
- Apply eligible discounts.
  - Discount according to passenger type.
  - Discount and Surcharge according to time and day.
- Apply surcharge/discount based on payment method.

## Conditions of Calculation
### Fare Based on Distance (km)
-  1 -  5   : RM  2.00
-  6 - 10   : RM  5.00
- 11 - 15   : RM 10.00
- 16 - 20   : RM 15.00
- 21 - 30   : RM 20.00

### Passenger Type Adjustment
- Senior Citizen    : -  50%
- Adult             : -   0%
- Student           : -  70%
- Child             : -  50%
- Child (< 5km)     : - 100%

### Day Time Adjustment
- After 10pm (2200)     : + RM 2 flat
- Weekend               : - 10%
- Weekday (630 - 930)   : + 20%
- Weekday (1700 - 2000) : + 20%
- Weekday (non rush)    : +  0%

### Payment Method Adjustment
- e-Wallet          : - 0%
- Crefit Card       : + 5%
- Online Banking    : - 5%
  
## Folder Structure

The workspace contains two folders by default, where:

- `src\main`: the folder to maintain sources
- `src\test`: the folder to maintain test files
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `testclasses` folder by default.

> To run the system, open the project folder in an IDE and run the test classes individually.
