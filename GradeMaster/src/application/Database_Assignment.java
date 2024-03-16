//package application;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;

//public class Database_Assignment {

    //private static final String url = "grademaster-mysql.mysql.database.azure.com";
    //private static final String username = "GradeMaster";
    //private static final String password = "Justice_League";

    /*public static void insertAssignment(AssData assignment) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, username, password))
            String sql = "INSERT INTO assignments (Ass, Type, Weight) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, assignment.getAss());
                statement.setString(2, assignment.getType());
                statement.setString(3, assignment.getWeight());
                statement.executeUpdate();
    }
}*/

/*    public static AssData[] retrieveAssignments() throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, username, password))
            String sql = "SELECT Ass, Type, Weight FROM assignments";
            try (PreparedStatement statement = conn.prepartStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    ArrayList<AssData> assignmentsList = new ArrayList<>();
                        while (resultSet.next()) {
                            String ass = resultSet.getString("Ass");
                            String ass = resultSet.getString("Type");
                            int weight = resultSet.getInt("Weight");
                            AssData assignment = new AssData(ass, type, weight);
                            assignmentList.add(assignment);
                         }
                         return assignmentList.toArray(new AssData[0]);
                     }
                    }
                   }
                  }
                 }*/