// this file will use the TeacherClassCreationSceneController to try to write the data to the database

package application;

// importing all needed for java.sql in this case
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBClassCreation {
    private static final String url = "grademaster-mysql-server.mysql.database.azure.com";
    private static final String databaseUser = "GradeMaster";
    private static final String databasePassword = "Justice_League";

    public static void saveCourse(String courseName, int courseNum) {
        try (Connection connection = DriverManager.getConnection(url, databaseUser, databaseUser)) {
            String sql = "INSERT INTO courses (Course Name, Course Number) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, courseName);
                statement.setInt(2, courseNum);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0){
                    System.out.println("Course inserted successfully!");
                } else {
                    System.out.println("Failed to insert course");
                }
            }
        }   catch (SQLException e) {
            System.err.println("Error inserting course: " + e.getMessage());
        }
    }
}
