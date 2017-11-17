package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller{

        @FXML private TextArea textArea;
        @FXML private TextField nameInput;
        @FXML private TextField emailInput;
        @FXML private TextField textInput;

        @FXML private Button logInButton;
        @FXML private Button logOutButton;
        @FXML private Button enterButton;

        String textAreaString = "";
        int random;

        public void logInButtonClicked(){
            if(!nameInput.getText().isEmpty() && !emailInput.getText().isEmpty()){
                textArea.clear();
                logInButton.setDisable(true);
                nameInput.setDisable(true);
                emailInput.setDisable(true);
                random = (int) (Math.random() * 3) + 1;
                switch (random){
                    case 1:
                        textAreaString += "Bot: Salut " + String.format("%s!%n", nameInput.getText());
                        textArea.setText(textAreaString);
                        break;
                    case 2:
                        textAreaString += "Bot: Buna " + String.format("%s!%n", nameInput.getText());
                        textArea.setText(textAreaString);
                        break;
                    case 3:
                        textAreaString += "Bot: Salutare " + String.format("%s!%n", nameInput.getText());
                        textArea.setText(textAreaString);
                }
            } else {
                textArea.setText("Nu ati introdus datele!");
            }
        }

        public void enterButtonClicked(){
            textAreaString += "Tu: " + String.format("%s%n",textInput.getText());
            textArea.setText(textAreaString + "\n");
        }

        public void logOutButtonClicked(){
            logInButton.setDisable(false);
            nameInput.setDisable(false);

            emailInput.setDisable(false);
            emailInput.clear();
            textInput.clear();

            textAreaString += "Bot: La revedere " + String.format("%s!%n", nameInput.getText());
            textArea.setText(textAreaString);

            nameInput.clear();
        }

}
