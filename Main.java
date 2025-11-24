import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addBookFlow();
                    break;
                case 2:
                    listBooksFlow();
                    break;
                case 3:
                    searchBookFlow();
                    break;
                case 4:
                    addUserFlow();
                    break;
                case 5:
                    listUsersFlow();
                    break;
                case 6:
                    issueBookFlow();
                    break;
                case 7:
                    returnBookFlow();
                    break;
                case 8:
                    viewActiveIssuesFlow();
                    break;
                case 9:
                    viewAvailableBooksFlow();
                    break;
                case 10:
                    viewUserHistoryFlow();
                    break;
                case 0:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

            System.out.println();

        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("===== Library Management System =====");
        System.out.println("1. Add Book");
        System.out.println("2. List All Books");
        System.out.println("3. Search Book by Title");
        System.out.println("4. Add User");
        System.out.println("5. List All Users");
        System.out.println("6. Issue Book");
        System.out.println("7. Return Book");
        System.out.println("8. View Active Issue Records");
        System.out.println("9. View Available Books");
        System.out.println("10. View User Borrow History");
        System.out.println("0. Exit");
        System.out.println("=====================================");
    }

    private static void addBookFlow() {
        System.out.println("---- Add New Book ----");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        Book book = library.addBook(title, author, isbn);
        System.out.println("Book added: " + book);
    }

    private static void listBooksFlow() {
        System.out.println("---- All Books ----");
        List<Book> books = library.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void searchBookFlow() {
        System.out.println("---- Search Book ----");
        System.out.print("Enter title keyword: ");
        String title = scanner.nextLine();

        List<Book> results = library.searchBooksByTitle(title);
        if (results.isEmpty()) {
            System.out.println("No matching books found.");
        } else {
            results.forEach(System.out::println);
        }
    }

    private static void addUserFlow() {
        System.out.println("---- Add New User ----");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User user = library.addUser(name, email);
        System.out.println("User added: " + user);
    }

    private static void listUsersFlow() {
        System.out.println("---- All Users ----");
        List<User> users = library.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void issueBookFlow() {
        System.out.println("---- Issue Book ----");
        int bookId = readInt("Enter Book ID: ");
        int userId = readInt("Enter User ID: ");

        String result = library.issueBook(bookId, userId);
        System.out.println(result);
    }

    private static void returnBookFlow() {
        System.out.println("---- Return Book ----");
        int issueId = readInt("Enter Issue ID: ");

        String result = library.returnBook(issueId);
        System.out.println(result);
    }

    private static void viewActiveIssuesFlow() {
        System.out.println("---- Active Issue Records ----");
        List<IssueRecord> records = library.getActiveIssueRecords();
        if (records.isEmpty()) {
            System.out.println("No active issues.");
        } else {
            records.forEach(System.out::println);
        }
    }

    private static void viewAvailableBooksFlow() {
        System.out.println("---- Available Books ----");
        List<Book> books = library.getAvailableBooks();
        if (books.isEmpty()) {
            System.out.println("No available books.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void viewUserHistoryFlow() {
        System.out.println("---- User Borrow History ----");
        int userId = readInt("Enter User ID: ");

        List<IssueRecord> records = library.getUserHistory(userId);
        if (records.isEmpty()) {
            System.out.println("No records for this user.");
        } else {
            records.forEach(System.out::println);
        }
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String line = scanner.nextLine();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
}
