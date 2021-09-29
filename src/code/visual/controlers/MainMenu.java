package code.visual.controlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    @FXML
    private JFXButton connected_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://ec2-44-194-6-121.compute-1.amazonaws.com:5432/dho189ncehplp",
                    "vxwdrwradnlahk",
                    "5adc174d3fe736cfede769fad13ef3ddef00ff512a583d37784bb942035ea661"
            );
        } catch (SQLException e) {
            connected_button.setText("error while connecting to postgresql@heroku");
        }
    }
}
