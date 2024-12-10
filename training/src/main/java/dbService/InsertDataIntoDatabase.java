package dbService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import com.google.gson.JsonObject;

public class InsertDataIntoDatabase implements AutoCloseable {
    private Connection con;

    public InsertDataIntoDatabase() {
        PropertyFileConnection conDTO = new PropertyFileConnection();
        con = conDTO.getConnection();
    }

    public Connection getConnection() {
        return con;
    }

    @Override
    public void close() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(JsonObject data) {
        String insertSQL = "INSERT INTO participant (timestamp, email_address, participant_name, registered_email_id, mobile_no, training_programme_code, query_type, query_details, upload_screenshot_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(insertSQL)) {
            // Convert ISO 8601 timestamp to MySQL datetime format
            String isoTimestamp = data.get("Timestamp").getAsString();
            String mysqlTimestamp = convertISOToMySQLTimestamp(isoTimestamp);

            // Set parameters
            pstmt.setString(1, mysqlTimestamp);
            pstmt.setString(2, data.get("Email Address").getAsString());
            pstmt.setString(3, data.get("Name of Participant").getAsString());
            pstmt.setString(4, data.get("Registered Email id (Participant User id(email) in the Training Portal)-if any").getAsString());
            pstmt.setString(5, data.get("Mobile No.").getAsString());
            pstmt.setString(6, data.get("Training Programme Code (if any)").getAsString());
            pstmt.setString(7, data.get("Type of Query").getAsString());
            pstmt.setString(8, data.get("Your Query/ Details").getAsString());
            pstmt.setString(9, data.get("Upload screenshots or other detail (if any)").getAsString());

            pstmt.executeUpdate();
            System.out.println("Data inserted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String convertISOToMySQLTimestamp(String isoTimestamp) {
        try {
            // Parse ISO 8601 date-time and convert to MySQL datetime format
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            java.util.Date parsedDate = isoFormat.parse(isoTimestamp);
            SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return mysqlFormat.format(parsedDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
