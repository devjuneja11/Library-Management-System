import java.time.LocalDate;

public class IssueRecord {
    private int issueId;
    private Book book;
    private User user;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public IssueRecord(int issueId, Book book, User user, LocalDate issueDate) {
        this.issueId = issueId;
        this.book = book;
        this.user = user;
        this.issueDate = issueDate;
        this.returnDate = null;
    }

    public int getIssueId() {
        return issueId;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    @Override
    public String toString() {
        return "IssueRecord{" +
                "IssueID=" + issueId +
                ", Book=" + book.getTitle() +
                ", User=" + user.getName() +
                ", IssueDate=" + issueDate +
                ", ReturnDate=" + (returnDate == null ? "Not Returned" : returnDate) +
                '}';
    }
}
