# Travel-Ticket-Booking-System
The partial implementation of a travel ticket booking system that includes unit & integration tests.
- [Prerequites] (## Pre-requisites)

## Pre-requisites
* Java 18.0
* JUnit 4.0
* JUnitParams
* Mockito

## This System Includes
* User and Guest data saving as txt file formats.
* Total Fare calculation based on distance.
* Discounted fare calculation based on passenger type, passenger quantity, day and time of travel.
* Payment amount calculation based on payment method.

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