import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

class Room {
    int roomNumber;
    String type;
    String size;
    float pricePerNight;
    int floor;
    boolean isBooked;

    public Room(int roomNumber, String type, String size, float pricePerNight, int floor) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.size = size;
        this.pricePerNight = pricePerNight;
        this.floor = floor;
        this.isBooked = false;
    }

    public static void displayAvailableRooms(List<Room> rooms) {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room Number: " + room.roomNumber + " | Type: " + room.type + " | Size: " + room.size +
                        " | Price per Night: $" + room.pricePerNight + " | Floor: " + room.floor + " | Status: Available");
            }
        }
    }

    public static void displayBookedRooms(List<Room> rooms) {
        System.out.println("Booked Rooms:");
        for (Room room : rooms) {
            if (room.isBooked) {
                System.out.println("Room Number: " + room.roomNumber + " | Type: " + room.type + " | Size: " + room.size +
                        " | Price per Night: RS." + room.pricePerNight + " | Floor: " + room.floor + " | Status: Booked");
            }
        }
    }
}

class Guest {
    String name;
    String phoneNumber;
    String address;
    String identityProof;
    List<Integer> roomNumbers;
    int persons;
    int nights;
    float bill;
    String bookingTime;

    public Guest(String name, String phoneNumber, String address, String identityProof, List<Integer> roomNumbers, int persons, int nights, float bill) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.identityProof = identityProof;
        this.roomNumbers = roomNumbers;
        this.persons = persons;
        this.nights = nights;
        this.bill = bill;
        this.bookingTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}

public class HotelManagement {
    private static List<Room> rooms = new Vector<>();  // Use Vector here
    private static List<Guest> guests = new Vector<>();  // Use Vector here
    private static List<Guest> pastGuests = new Vector<>();  // Use Vector here

    public static void main(String[] args) {
        // Initialize some rooms
        rooms.add(new Room(101, "Standard", "1BHK", 100.0f, 1));
        rooms.add(new Room(102, "Deluxe", "2BHK", 200.0f, 2));
        rooms.add(new Room(103, "Standard", "1BHK", 150.0f, 1));
        rooms.add(new Room(104, "Deluxe", "2BHK", 250.0f, 2));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Are you an Admin or a Guest? (Enter 'admin' or 'guest' to continue, or 'exit' to quit): ");
            String userType = scanner.nextLine().trim().toLowerCase();

            if (userType.equals("exit")) {
                break;
            }

            if (userType.equals("admin")) {
                System.out.print("Enter admin password: ");
                String password = scanner.nextLine();
                if (password.equals("admin123")) {
                    adminMenu(scanner);
                } else {
                    System.out.println("Incorrect password! Access denied.");
                }
            } else if (userType.equals("guest")) {
                guestMenu(scanner);
            } else {
                System.out.println("Invalid input! Please enter 'admin' or 'guest'.");
            }
        }
        scanner.close();
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View Booked Room");
            System.out.println("2. View All Guests");
            System.out.println("3. View Total Earnings");
            System.out.println("4. Exit Admin Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    Room.displayBookedRooms(rooms);  // Show all booked rooms
                    break;
                case 2:
                    viewAllGuests();
                    break;
                case 3:
                    viewTotalEarnings();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Please select again.");
            }
        }
    }

    private static void guestMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nGuest Menu:");
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Check-Out");
            System.out.println("4. Exit Guest Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    Room.displayAvailableRooms(rooms);
                    break;
                case 2:
                    bookRoom(scanner);
                    break;
                case 3:
                    checkOut(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Please select again.");
            }
        }
    }

    private static void viewAllGuests() {
        System.out.println("All Guests:");
        for (Guest guest : guests) {
            System.out.println("Guest Name: " + guest.name + " | Phone Number: " + guest.phoneNumber +
                    " | Address: " + guest.address + " | Identity Proof: " + guest.identityProof +
                    " | Room Numbers: " + guest.roomNumbers + " | Booking Time: " + guest.bookingTime);
        }
        // Show past guests
        System.out.println("\nPast Guests:");
        for (Guest pastGuest : pastGuests) {
            System.out.println("Guest Name: " + pastGuest.name + " | Phone Number: " + pastGuest.phoneNumber +
                    " | Address: " + pastGuest.address + " | Identity Proof: " + pastGuest.identityProof +
                    " | Room Numbers: " + pastGuest.roomNumbers + " | Booking Time: " + pastGuest.bookingTime);
        }
    }

    private static void viewTotalEarnings() {
        float totalEarnings = 0.0f;
        for (Guest guest : guests) {
            totalEarnings += guest.bill;
        }
        System.out.println("Total Earnings: RS." + totalEarnings);
    }

    private static void bookRoom(Scanner scanner) {
        // Ask for guest details
        String guestName;
        while (true) {
            System.out.print("Enter guest name (letters only): ");
            guestName = scanner.nextLine();
            if (guestName.matches("[a-zA-Z ]+")) {
                break;  // Valid name
            } else {
                System.out.println("Invalid name! Please enter letters only.");
            }
        }

        String phoneNumber;
        while (true) {
            System.out.print("Enter phone number (10 digits): ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.matches("\\d{10}")) {
                break;  // Valid phone number
            } else {
                System.out.println("Invalid phone number! It must be exactly 10 digits.");
            }
        }

        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter identity proof (e.g., Passport, ID): ");
        String identityProof = scanner.nextLine();

        List<Integer> roomNumbers = new Vector<>();
        Room selectedRoom = null;

        // Loop until a valid, available room number is entered
        while (true) {
            System.out.print("Enter room number to book: ");
            int roomNumber = scanner.nextInt();
            scanner.nextLine(); // consume the leftover newline

            // Check if the room is available
            for (Room room : rooms) {
                if (room.roomNumber == roomNumber && !room.isBooked) {
                    selectedRoom = room;
                    roomNumbers.add(roomNumber); // Add the room number to the list
                    break;
                }
            }
            if (selectedRoom != null) {
                break; // room is available
            } else {
                System.out.println("Room is not available or invalid room number. Please choose another room.");
            }
        }

        // Ask for additional booking details
        System.out.print("Enter number of persons: ");
        int persons = scanner.nextInt();
        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();

        // Calculate the total bill for all booked rooms
        float totalBill = selectedRoom.pricePerNight * nights;

        // Mark the selected room as booked
        selectedRoom.isBooked = true;

        // Add the guest to the list and the past guests list upon checkout
        Guest newGuest = new Guest(guestName, phoneNumber, address, identityProof, roomNumbers, persons, nights, totalBill);
        guests.add(newGuest);
        System.out.println("Room booked successfully for " + persons + " person(s)!");
        System.out.println("Guest Name: " + guestName + " | Room Number: " + selectedRoom.roomNumber);
        System.out.println("Total Bill: $" + totalBill);
    }

    private static void checkOut(Scanner scanner) {
        System.out.print("Enter your name for check-out: ");
        String guestName = scanner.nextLine();

        // Search for the guest by name
        for (Guest guest : guests) {
            if (guest.name.equalsIgnoreCase(guestName)) {
                System.out.println("You have to check out room numbers " + guest.roomNumbers + " (yes/no)?");
                String confirmCheckOut = scanner.nextLine();

                if (confirmCheckOut.equalsIgnoreCase("yes")) {
                    // Free up the rooms
                    for (int roomNumber : guest.roomNumbers) {
                        for (Room room : rooms) {
                            if (room.roomNumber == roomNumber) {
                                room.isBooked = false; // Mark the room as available
                                break;
                            }
                        }
                    }
                    // Move the guest to the past guests list
                    pastGuests.add(guest);
                    // Remove the guest from the current list
                    guests.remove(guest);
                    System.out.println("Check-out successful for " + guestName + " from room numbers " + guest.roomNumbers + ".");
                } else {
                    System.out.println("Check-out canceled.");
                }
                return; // Exit the method after processing the check-out
            }
        }
        System.out.println("No booking found for the name: " + guestName);
    }
}
