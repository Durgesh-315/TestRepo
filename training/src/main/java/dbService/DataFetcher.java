package dbService;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataFetcher {
    private static final Logger logger = Logger.getLogger(DataFetcher.class.getName());
    private static final String API_URL = "https://script.google.com/macros/s/AKfycbwQEuRehyl2CFiZGtnKG37wIDQkfgDlqL7Bz_ePOm7yo31Cos3lM6rMsgHW6kcY7Y9z/exec";
    private static final String LAST_FETCHED_ID_FILE = "lastFetchedId.properties";
    private static final String DEFAULT_ID = "0";

    public void fetchDataAndInsert() {
        String lastFetchedId = getLastFetchedId();
        String apiUrlWithParams = API_URL + "?rowNo=" + lastFetchedId;

        logger.log(Level.INFO, "Fetching data from API with URL: " + apiUrlWithParams);

        JsonObject apiData = FetchDataFromAPI.fetchData(apiUrlWithParams);

        logger.log(Level.INFO, "Raw API response: " + apiData);

        if (apiData != null) {
            JsonArray dataArray = apiData.getAsJsonArray("data");
            logger.log(Level.INFO, "Fetched data array from API: " + dataArray);

            if (dataArray != null) {
                try (InsertDataIntoDatabase db = new InsertDataIntoDatabase()) {
                    int newLastFetchedId = Integer.parseInt(lastFetchedId);

                    for (int i = 0; i < dataArray.size(); i++) {
                        JsonObject data = dataArray.get(i).getAsJsonObject();
                        logger.log(Level.INFO, "Processing data: " + data);

                        if (!isHeaderRow(data) && !isDataPresentInDatabase(data, db)) {
                            db.insertData(data);
                            logger.log(Level.INFO, "Inserted data into database: " + data);
                            
                            if (data.has("id") && !data.get("id").isJsonNull()) {
                                newLastFetchedId = Math.max(newLastFetchedId, data.get("id").getAsInt());
                            } else {
                                logger.log(Level.WARNING, "Missing 'id' field in data: " + data);
                            }
                        } else {
                            logger.log(Level.INFO, "Skipped data (header or already present): " + data);
                        }
                    }

                    updateLastFetchedId(String.valueOf(newLastFetchedId));
                    logger.log(Level.INFO, "Updated last fetched ID: " + newLastFetchedId);

                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error while inserting data", e);
                }
            } else {
                logger.log(Level.WARNING, "No data array in API response.");
            }
        } else {
            logger.log(Level.WARNING, "No data fetched from API. Last fetched ID is: " + lastFetchedId);
        }
    }

    private boolean isHeaderRow(JsonObject data) {
        // Define the expected header values
        String[] headerValues = {
            "Timestamp", "Email Address", "Name of Participant",
            "Registered Email id (Participant User id(email) in the Training Portal)-if any",
            "Mobile No.", "Training Programme Code (if any)",
            "Type of Query", "Your Query/ Details",
            "Upload screenshots or other detail (if any)"
        };

        // Check if the values in the row match the header values
        for (String header : headerValues) {
            if (data.has("Timestamp") && data.get("Timestamp").getAsString().equals(header)) {
                return true; // It is a header row
            }
        }
        return false; // Not a header row
    }

    private String getLastFetchedId() {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream(LAST_FETCHED_ID_FILE)) {
            props.load(input);
            String id = props.getProperty("lastFetchedId", DEFAULT_ID);
            logger.log(Level.INFO, "Read last fetched ID: " + id);
            return id;
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not read last fetched ID, defaulting to earliest ID", e);
            createDefaultIdFile();
            return DEFAULT_ID;
        }
    }

    private void createDefaultIdFile() {
        Properties props = new Properties();
        props.setProperty("lastFetchedId", DEFAULT_ID);

        try (FileOutputStream output = new FileOutputStream(LAST_FETCHED_ID_FILE)) {
            props.store(output, null);
            logger.log(Level.INFO, "Default ID file created with value: " + DEFAULT_ID);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating default ID file", e);
        }
    }

    private void updateLastFetchedId(String latestId) {
        Properties props = new Properties();
        props.setProperty("lastFetchedId", latestId);

        try (FileOutputStream output = new FileOutputStream(LAST_FETCHED_ID_FILE)) {
            props.store(output, null);
            logger.log(Level.INFO, "Updated ID file with value: " + latestId);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error updating last fetched ID", e);
        }
    }

    private boolean isDataPresentInDatabase(JsonObject data, InsertDataIntoDatabase db) {
        Connection con = db.getConnection();

        // Check if all required fields exist in the JsonObject
        String[] requiredFields = {
            "Email Address", "Name of Participant", "Registered Email id (Participant User id(email) in the Training Portal)-if any", 
            "Mobile No.", "Training Programme Code (if any)", "Type of Query", 
            "Your Query/ Details", "Upload screenshots or other detail (if any)"
        };

        for (String field : requiredFields) {
            if (!data.has(field)) {
                logger.log(Level.WARNING, "Missing required field in data: " + field);
                return false; // Return false or handle accordingly
            }
        }

        // Retrieve data from JsonObject
        String emailAddress = data.get("Email Address").getAsString();
        String participantName = data.get("Name of Participant").getAsString();
        String registeredEmailId = data.get("Registered Email id (Participant User id(email) in the Training Portal)-if any").getAsString();
        String mobileNo = data.get("Mobile No.").getAsString();
        String trainingProgrammeCode = data.get("Training Programme Code (if any)").getAsString();
        String queryType = data.get("Type of Query").getAsString();
        String queryDetails = data.get("Your Query/ Details").getAsString();
        String uploadScreenshotUrl = data.get("Upload screenshots or other detail (if any)").getAsString();

        // Adjust your query to match all fields
        String query = "SELECT COUNT(*) FROM participant WHERE email_address = ? AND participant_name = ? AND registered_email_id = ? AND mobile_no = ? AND training_programme_code = ? AND query_type = ? AND query_details = ? AND upload_screenshot_url = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, emailAddress);
            pstmt.setString(2, participantName);
            pstmt.setString(3, registeredEmailId);
            pstmt.setString(4, mobileNo);
            pstmt.setString(5, trainingProgrammeCode);
            pstmt.setString(6, queryType);
            pstmt.setString(7, queryDetails);
            pstmt.setString(8, uploadScreenshotUrl);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Return true if the record is found
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error checking data presence in database", e);
        }
        return false;
    }
}
