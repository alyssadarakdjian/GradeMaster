/*package application;

// importing all needed for java.sql in this class
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBClassCreationFallBack2 extends TeacherClassCreationSceneController {
    public static void main(String[] args) {

        String url = "grademaster-mysql-server.mysql.database.azure.com";

        // database creds
        String databaseUser = "GradeMaster";
        String databasePassword = "Justice_League";

        // instance of the controller for class access
        TeacherClassCreationSceneController controller = new TeacherClassCreationSceneController();
        // Access the text fields using the controller instance
        String courseName = controller.getCourseNameTextField().getText().trim();
        int courseNum = Integer.parseInt(controller.getCourseNumTextField().getText().trim());

        try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
            if (connection != null) {
                System.out.println("Connection to the database successful!");
                // adding auto-commit
                connection.setAutoCommit(true);
                // Define the SQL query
                String sql = "INSERT INTO courses (`Course Name`, `Course Number`) VALUES (?, ?)";

                // Create a PreparedStatement with the SQL query
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Set values for the placeholders
                    statement.setString(1, courseName);
                    statement.setInt(2, courseNum);

                    // Execute the query
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Course inserted successfully!");
                    } else {
                        System.out.println("Failed to insert course");
                    }
                }
            }
            // changing so that it could print an error out for us to see
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

 */
