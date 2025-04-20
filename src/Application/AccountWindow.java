package Application;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class AccountWindow {
    public static void displayAccountWindow(Stage primaryStage) {
        Stage window = new Stage();
        window.setTitle("Account");
        window.setResizable(false);

        // Создание элементов интерфейса
        Label titleLabel = new Label("Welcome! Please log in or register");
        Label userLabel = new Label("Username:");
        TextField userInput = new TextField();
        userInput.setPromptText("Enter username");

        Label passLabel = new Label("Password:");
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("Enter password");

        Button loginButton = new Button("Log In");
        Button registerButton = new Button("Register");
        Label messageLabel = new Label();

        // Обработчики событий для кнопок
        loginButton.setOnAction(e -> {
            if (authenticate(userInput.getText(), passInput.getText())) {
                messageLabel.setText("Login successful!");
                window.close();
                Main.goToSceneHome(primaryStage);
            } else {
                messageLabel.setText("Invalid username or password!");
            }
        });

        registerButton.setOnAction(e -> {
            if (register(userInput.getText(), passInput.getText())) {
                messageLabel.setText("Registration successful! Please log in.");
            } else {
                messageLabel.setText("Registration failed. Username might be taken.");
            }
        });

        // Настройка layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                titleLabel,
                userLabel,
                userInput,
                passLabel,
                passInput,
                loginButton,
                registerButton,
                messageLabel
        );

        Scene accountScene = new Scene(layout, 300, 400);
        window.setScene(accountScene);
        window.show();
    }

    // Метод для аутентификации (заглушка - в реальном приложении нужно подключить базу данных)
    private static boolean authenticate(String username, String password) {
        // Здесь должна быть проверка с базой данных
        return !username.isEmpty() && !password.isEmpty();
    }

    // Метод для регистрации (заглушка - в реальном приложении нужно подключить базу данных)
    private static boolean register(String username, String password) {
        // Здесь должна быть запись в базу данных
        return !username.isEmpty() && !password.isEmpty();
    }
}
