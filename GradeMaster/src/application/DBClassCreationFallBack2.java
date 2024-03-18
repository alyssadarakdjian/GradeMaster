package application;

// importing all needed for java.sql in this case
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.SQLException;

public class DBClassCreationFallBack2 extends TeacherClassCreationSceneController {
    public static void main (String[] args) {
        String url = "grademaster-mysql-server.mysql.database.azure.com";

        // database creds
        String databaseUser = "GradeMaster";
        String databasePassword = "Justice_League";

        try {
            Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword);

            if (connection != null){
                System.out.println("Connection to the database successful!");
                //Statement stmt= connection.createStatement();
                String sql = "INSERT INTO courses ('Course Name', 'Course Number') VALUES (?, ?)";

                //prepared statement
                PreparedStatement statement = connection.prepareStatement(sql);
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

                // Close the connection and statement
                statement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            }
    }

//}
