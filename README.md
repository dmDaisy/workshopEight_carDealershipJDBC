# Car Dealership CLI Application

## Overview

This is a simple CLI java application for managing cars for dealers, each entry records their values of VIN, year, make, model, type, color, mileage and price.
All enties are read from a saved to a CSV file when the dealer runs and exits the program. The built-in CSV file has existing entries for testing purpose.
The application supports several features, users can modify the car list, or search/filter by specific property values. 

## Features

- **Search/Filter**: View and filter vehicles in the following ways:
  - Price
  - Make/model
  - Year
  - Color
  - Mileage
  - Type
  - Display all
- **Modify**: change the vehicle list in the following ways:
  - Add
  - Remove
- **Auto save**: When exiting the program.

## Requirements

- Java 8 or higher

## Setup

1. Clone this repository to your local machine.

    ```bash
    git clone https://github.com/dmDaisy/workshopFour_carDealership
    cd workshopFour_carDealership
    ```

2. Compile the Java files.

    ```bash
    javac -d out src/main/java/com/ps/*.java
    ```

3. Run the application.

    ```bash
    java -cp out com.ps.Main
    ```

## Screenshots

Below are a few screenshots of the application in action:

### Home Screen

![Screenshot 2025-05-09 at 11 05 59 PM](https://github.com/user-attachments/assets/3f7bec05-d060-43fe-bae0-bee079606fab)


### Search by features example 1

![Screenshot 2025-05-09 at 9 45 21 PM](https://github.com/user-attachments/assets/8679b20c-9fbe-457d-8cbf-49162aff6dea)


### Search by features example 2

![Screenshot 2025-05-09 at 9 46 12 PM](https://github.com/user-attachments/assets/162bb91a-eed3-4425-91d7-5cb7b5ad0454)


### Search by features example 3

![Screenshot 2025-05-09 at 9 46 40 PM](https://github.com/user-attachments/assets/060ed825-f33d-4daa-9609-37e42db69a3e)


### Invalid input handling

![Screenshot 2025-05-09 at 10 45 46 PM](https://github.com/user-attachments/assets/1cf2aee3-d138-468e-9239-f0ab36f6b917)



### Interesting Piece of Code
**Foolproof design to get user int input**: Instaed of throwing an exception and crashing the program, the invalid input can be handled gracefully. 
For example, the `getUserInt()` helper method shown below prints an error message and prompts the user to try again until getting a valid int.
Besides, it's also reponsible for consuming the redundant "\n" after `scanner.nextInt()` is called, which is a common issue to be handled when scanning number input.

```java
    // helper method to get user int input and consumes redundant \n
    private int getUserInt(){
        while( ! scanner.hasNextInt()){
            System.out.println("Invalid input, enter an integer: ");
            scanner.nextLine(); // scanner.next()?
        }
        int result = scanner.nextInt();
        scanner.nextLine(); // consumes redundant \n

        return result;
    }
