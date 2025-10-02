import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class Main extends Application {

    private TextField tempField = new TextField();
    private Label resultLabel = new Label();
    private double fahrenheit;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        tempField.setPromptText("Enter temperature");

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("°C -> F","F -> °C","K -> °C","°C -> K");

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> {
            String selected = (String) comboBox.getValue();
            if ("°C -> F".equals(selected)) {
                celsiusTofahrenheit();
            } else if ("F -> °C".equals(selected)) {
                fahrenheiToCelsius();
            } else if ("K -> °C".equals(selected)) {
                kelvinToCelsius();
            } else if ("°C -> K".equals(selected)) {
                celsiusToKelvin();
            }
        });


        Button saveButton = new Button("Save to DB");
        saveButton.setOnAction(e -> Database.saveTemperature(
                Double.parseDouble(tempField.getText()), fahrenheit, resultLabel));

        VBox root = new VBox(10, tempField, comboBox,convertButton, resultLabel, saveButton);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Temperature converter");
        stage.setScene(scene);
        stage.show();
    }

    private void celsiusTofahrenheit() {
        try {
            double celsius = Double.parseDouble(tempField.getText());
            fahrenheit = (celsius * 9 / 5) + 32;
            resultLabel.setText(String.format("Fahrenheit: %.2f", fahrenheit));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    private void fahrenheiToCelsius() {
        try {
            double f = Double.parseDouble(tempField.getText());
            double celsius = (f-32)*5/9;
            resultLabel.setText(String.format("Celsius: %.2f", celsius));
        }catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    private void kelvinToCelsius(){
        try{
            double k = Double.parseDouble(tempField.getText());
            double celsius = k-273.15;
            resultLabel.setText(String.format("Celsius: %.2f", celsius));
        }catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    private void celsiusToKelvin(){
        try{
            double c = Double.parseDouble(tempField.getText());
            double k = c+273.15;
            resultLabel.setText(String.format("Kelvin: %.2f", k));
        }catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }
}