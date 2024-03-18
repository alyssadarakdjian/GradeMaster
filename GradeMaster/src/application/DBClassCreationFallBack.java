package application;

// importing all needed for java.sql in this case
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBClassCreationFallBack {
    private static final String url = "grademaster-mysql-server.mysql.database.azure.com";
    private static final String databaseUser = "GradeMaster";
    private static final String databasePassword = "Justice_League";

    public static void saveCourse(String courseName, int courseNumText) {
        Connection connection = null; // Declare the connection outside the try block
        try {
            connection = DriverManager.getConnection(url, databaseUser, databaseUser);
            connection.setAutoCommit(false); // recommended if we have a InnoDB type database in MySQL
            String sql = "INSERT INTO courses (Course Name, Course Number) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, courseName);
                statement.setInt(2, courseNumText);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0){
                    System.out.println("Course inserted successfully!");
                } else {
                    System.out.println("Failed to insert course");
                }
                connection.commit();
            }
        } catch (SQLException e) {
            System.err.println("Error inserting course: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback the transaction in case of error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
/*do not have editing rights to database*/
