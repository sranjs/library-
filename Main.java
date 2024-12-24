public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        BookService bookService = new BookService();
        TransactionService transactionService = new TransactionService();

        // Register a new user
        userService.registerUser("john_doe", "password123", "User");

        // Add a new book
        bookService.addBook("Java Programming", "John Doe");

        // Borrow a book
        transactionService.borrowBook(1, 1);

        // Return a book
        transactionService.returnBook(1);
    }
}
