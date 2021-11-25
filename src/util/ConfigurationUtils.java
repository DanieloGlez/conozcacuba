package util;

import dto.fun.UserDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ConfigurationUtils {
    // Database connection
    private static String server;
    private static String database;
    private static String user;
    private static String password;

    // Active User
    private static UserDto activeUser;

    public static void initializeDatabaseHost(boolean remoteConnection) throws IOException, ParseException {
        // Create parser
        JSONParser jsonParser = new JSONParser();

        // Read json file
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("./config.json"));

        // Read host jsonobject information
        JSONObject hostsJsonObject = (JSONObject) jsonObject.get("hosts");

        // Get the required host and use his values
        JSONObject requiredHostJsonObject = (JSONObject) hostsJsonObject.get(remoteConnection ? "remotehost" : "localhost");
        server = (String) requiredHostJsonObject.get("server");
        database = (String) requiredHostJsonObject.get("database");
        user = (String) requiredHostJsonObject.get("user");
        password = (String) requiredHostJsonObject.get("password");
    }

    public static String getServer() {
        return server;
    }

    public static String getDatabase() {
        return database;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static UserDto getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(UserDto activeUser) {
        ConfigurationUtils.activeUser = activeUser;
    }
}
