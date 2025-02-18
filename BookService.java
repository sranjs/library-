import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookService {
    public void addBook(String title, String author) {
        String query = "INSERT INTO Books (title, author) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
