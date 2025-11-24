import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<User> users;
    private List<IssueRecord> issueRecords;

    private int nextBookId = 1;
    private int nextUserId = 1;
    private int nextIssueId = 1;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        issueRecords = new ArrayList<>();
    }

    // ------------ Book Operations ------------

    public Book addBook(String title, String author, String isbn) {
        Book book = new Book(nextBookId++, title, author, isbn);
        books.add(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> searchBooksByTitle(String title) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Book getBookById(int id) {
        return books.stream()
                .filter(b -> b.getBookId() == id)
                .findFirst()
                .orElse(null);
    }

    // ------------ User Operations ------------

    public User addUser(String name, String email) {
        User user = new User(nextUserId++, name, email);
        users.add(user);
        return user;
    }

    public User getUserById(int id) {
        return users.stream()
                .filter(u -> u.getUserId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllUsers() {
        return users;
    }

    // ------------ Issue / Return Operations ------------

    public String issueBook(int bookId, int userId) {
        Book book = getBookById(bookId);
        if (book == null) {
            return "Book not found!";
        }
        if (book.isIssued()) {
            return "Book is already issued!";
        }

        User user = getUserById(userId);
        if (user == null) {
            return "User not found!";
        }

        IssueRecord record = new IssueRecord(nextIssueId++, book, user, LocalDate.now());
        issueRecords.add(record);
        book.setIssued(true);

        return "Book issued successfully. Issue ID: " + record.getIssueId();
    }

    public String returnBook(int issueId) {
        IssueRecord record = issueRecords.stream()
                .filter(r -> r.getIssueId() == issueId)
                .findFirst()
                .orElse(null);

        if (record == null) {
            return "Issue record not found!";
        }
        if (record.isReturned()) {
            return "Book is already returned!";
        }

        record.setReturnDate(LocalDate.now());
        record.getBook().setIssued(false);

        return "Book returned successfully.";
    }

    public List<IssueRecord> getAllIssueRecords() {
        return issueRecords;
    }

    public List<IssueRecord> getActiveIssueRecords() {
        return issueRecords.stream()
                .filter(r -> !r.isReturned())
                .collect(Collectors.toList());
    }

    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(b -> !b.isIssued())
                .collect(Collectors.toList());
    }

    public List<IssueRecord> getUserHistory(int userId) {
        return issueRecords.stream()
                .filter(r -> r.getUser().getUserId() == userId)
                .collect(Collectors.toList());
    }
}
