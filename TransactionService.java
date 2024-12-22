public class TransactionService {
    public void borrowBook(int bookID, int userID) {
        String updateBookQuery = "UPDATE Books SET status = 'Borrowed', borrowerID = ? WHERE bookID = ? AND status = 'Available'";
        String insertTransactionQuery = "INSERT INTO Transactions (userID, bookID, borrowDate) VALUES (?, ?, CURDATE())";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Update book status
            PreparedStatement bookStmt = conn.prepareStatement(updateBookQuery);
            bookStmt.setInt(1, userID);
            bookStmt.setInt(2, bookID);
            int rowsUpdated = bookStmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Insert transaction
                PreparedStatement transStmt = conn.prepareStatement(insertTransactionQuery);
                transStmt.setInt(1, userID);
                transStmt.setInt(2, bookID);
                transStmt.executeUpdate();

                System.out.println("Book borrowed successfully!");
            } else {
                System.out.println("Book is not available.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int bookID) {
        String updateBookQuery = "UPDATE Books SET status = 'Available', borrowerID = NULL WHERE bookID = ?";
        String updateTransactionQuery = "UPDATE Transactions SET returnDate = CURDATE() WHERE bookID = ? AND returnDate IS NULL";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Update book status
            PreparedStatement bookStmt = conn.prepareStatement(updateBookQuery);
            bookStmt.setInt(1, bookID);
            bookStmt.executeUpdate();

            // Update transaction
            PreparedStatement transStmt = conn.prepareStatement(updateTransactionQuery);
            transStmt.setInt(1, bookID);
            transStmt.executeUpdate();

            System.out.println("Book returned successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
