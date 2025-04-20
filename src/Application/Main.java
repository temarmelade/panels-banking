package Application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    public static String nameofUser;
    public static String cardNumber;
    public static String expiryDate;
    public static void main(String[] args) {
        Application.launch(args);
    }
    //Start
    @Override
    public void start(Stage primaryStage) {

        /*exit
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            AlertBox.displayAlert("ALERT!", "Are you sure to leave the program?");
        });
        */
        //window
        primaryStage.setScene(goToSceneHome(primaryStage));
        primaryStage.setMaxWidth(800);
        primaryStage.setTitle("TemBank");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    //Setting the Home Scene
    public static Scene goToSceneHome(Stage primaryStage) {
        GridPane layoutHome = new GridPane();
        //the main layout
        setDefault(layoutHome, primaryStage);
        //Top of the window(title of the Bank)


        //Right side of the window for Home (Card)
        layoutHome.add(displayCard("Nursultan Kasymbekov", "1234 5678 9101 1121", "02/30"), 2, 1, 3, 2);
        //Right side of the window for Home (Icons)
        layoutHome.add(setButtonDefault("Credits", 60, "More", goToCreditScene(primaryStage), primaryStage), 1, 3, 1, 1);
        layoutHome.add(setButtonDefault("Deposits", 0, "More", goToDepositScene(primaryStage), primaryStage), 3, 3, 1, 1);
        layoutHome.add(setButtonDefault("Get Card", -60, "More", goToGetCardScene(primaryStage), primaryStage), 5, 3, 1, 1);
        //Left side of the window (Main panel)




        //setting the size of columns
        setLayoutColumns(layoutHome);
        //setting the size of rows
        setLayoutRows(layoutHome);

        layoutHome.setBackground(Background.fill(Color.WHITESMOKE));
        //Home scene
        return new Scene(layoutHome, 800, 600);
    }
    private static StackPane displayCard(String nameofUser, String cardNumber, String expiryDate) {
        Rectangle card = new Rectangle(360, 230);
        card.setArcHeight(40);
        card.setArcWidth(40);
        card.setFill(Color.DARKBLUE);


        Text bankName = new Text("TemBank");
        bankName.setFill(Color.WHITE);
        bankName.setFont(Font.font("Arial", 18));

        Text cardNumberText = new Text(cardNumber);
        cardNumberText.setFill(Color.WHITE);
        cardNumberText.setFont(Font.font("Consolas", 20));

        Text cardHolder = new Text(nameofUser);
        cardHolder.setFill(Color.WHITE);
        cardHolder.setFont(Font.font("Arial", 14));

        Text expiryDateText = new Text(expiryDate);
        expiryDateText.setFill(Color.WHITE);
        expiryDateText.setFont(Font.font("Arial", 14));

        VBox textBox = new VBox();
        textBox.getChildren().addAll(bankName, cardNumberText, cardHolder, expiryDateText);
        textBox.setAlignment(Pos.CENTER_LEFT);
        textBox.setTranslateX(30);
        textBox.setSpacing(20);

        StackPane cardBox = new StackPane();
        cardBox.getChildren().addAll(card, textBox);
        return cardBox;
    }
    //Setting the History Scene
    private static Scene goToHistoryScene(Stage primaryStage) {
        GridPane layoutHistory = new GridPane();
        setDefault(layoutHistory, primaryStage);
        setLayoutRows(layoutHistory);
        setLayoutColumns(layoutHistory);

        // Заголовок
        Text title = new Text("История операций");
        title.setFont(Font.font("Arial", 20));
        layoutHistory.add(title, 1, 1, 3, 1);

        // Таблица с транзакциями (заглушка)
        TableView<Transaction> table = new TableView<>();

        // Колонки
        TableColumn<Transaction, String> dateCol = new TableColumn<>("Дата");
        TableColumn<Transaction, String> typeCol = new TableColumn<>("Тип");
        TableColumn<Transaction, Double> amountCol = new TableColumn<>("Сумма");

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        table.getColumns().addAll(dateCol, typeCol, amountCol);

        // Заполняем тестовыми данными
        ObservableList<Transaction> transactions = FXCollections.observableArrayList(
                new Transaction("01.01.2023", "Перевод", -5000),
                new Transaction("02.01.2023", "Пополнение", 10000)
        );
        table.setItems(transactions);

        layoutHistory.add(table, 1, 2, 3, 3);
        layoutHistory.setBackground(Background.fill(Color.WHITESMOKE));

        return new Scene(layoutHistory, 800, 600);
    }
    //Setting the Transfers Scene
    private static Scene goToTransfersScene(Stage primaryStage) {
        GridPane layoutTransfers = new GridPane();
        setDefault(layoutTransfers, primaryStage);
        setLayoutColumns(layoutTransfers);
        setLayoutRows(layoutTransfers);

        // Заголовок
        Text title = new Text("Переводы");
        title.setFont(Font.font("Arial", 20));
        layoutTransfers.add(title, 1, 1, 3, 1);

        // Поля формы
        TextField recipientField = new TextField();
        recipientField.setPromptText("Номер карты получателя");

        TextField amountField = new TextField();
        amountField.setPromptText("Сумма");

        Button sendButton = new Button("Отправить");
        sendButton.setOnAction(e -> {
            if (!recipientField.getText().isEmpty() && !amountField.getText().isEmpty()) {
                System.out.println("Перевод отправлен!");
                primaryStage.setScene(goToSceneHome(primaryStage));
            }
        });

        VBox form = new VBox(10);
        form.getChildren().addAll(
                new Label("Получатель:"), recipientField,
                new Label("Сумма:"), amountField,
                sendButton
        );
        form.setAlignment(Pos.CENTER);

        layoutTransfers.add(form, 1, 2, 3, 3);
        layoutTransfers.setBackground(Background.fill(Color.WHITESMOKE));

        return new Scene(layoutTransfers, 800, 600);
    }
    //Setting the Profile Scene
    private static Scene goToProfileScene(Stage primaryStage) {
        GridPane layoutProfile = new GridPane();
        setDefault(layoutProfile, primaryStage);
        setLayoutColumns(layoutProfile);
        setLayoutRows(layoutProfile);

        // Заголовок
        Text title = new Text("Профиль");
        title.setFont(Font.font("Arial", 20));
        layoutProfile.add(title, 1, 1, 3, 1);

        // Данные пользователя
        VBox profileInfo = new VBox(10);
        profileInfo.getChildren().addAll(
                new Label("Имя: " + nameofUser),
                new Label("Номер карты: " + cardNumber),
                new Label("Срок действия: " + expiryDate)
        );

        // Кнопка выхода
        Button logoutButton = new Button("Выйти");
        logoutButton.setOnAction(e -> {
            AccountWindow.displayAccountWindow(primaryStage);// Или открыть окно логина
        });

        VBox container = new VBox(20);
        container.getChildren().addAll(profileInfo, logoutButton);
        container.setAlignment(Pos.CENTER);

        layoutProfile.add(container, 1, 2, 3, 3);
        layoutProfile.setBackground(Background.fill(Color.WHITESMOKE));

        return new Scene(layoutProfile, 800, 600);
    }
    //Setting the Credit Scene
    private static Scene goToCreditScene(Stage primaryStage) {
        GridPane creditPane = new GridPane();
        setDefault(creditPane, primaryStage);
        setLayoutColumns(creditPane);
        setLayoutRows(creditPane);
        creditPane.add(displayTheInfo("Credits", "В нашем банке вы можете получить разные виды кредитов. И они все выгодны для вас!"), 1, 1, 3, 2);
        buttonPos(primaryStage, creditPane, "Пользовательский", "Онлайн", "Микро", "Take");
        return new Scene(creditPane, 800, 600);
    }

    //Setting the Deposit Scene
    private static Scene goToDepositScene(Stage primaryStage) {
        GridPane depositPane = new GridPane();
        setDefault(depositPane, primaryStage);
        setLayoutColumns(depositPane);
        setLayoutRows(depositPane);

        depositPane.add(displayTheInfo("Deposits", "Any deposit you want"), 1, 1, 3, 3);
        buttonPos(primaryStage, depositPane, "Deposit 1", "Deposit 2", "Deposit 3", "Dep");
        return new Scene(depositPane, 800, 600);
    }
    //Setting the GetCardScene
    private static Scene goToGetCardScene(Stage primaryStage) {
        GridPane getCardPane = new GridPane();
        setDefault(getCardPane, primaryStage);
        setLayoutColumns(getCardPane);
        setLayoutRows(getCardPane);

        getCardPane.add(displayTheInfo("Cards", "Special cards for special ones"),1, 1, 4, 3);
        buttonPos(primaryStage, getCardPane, "Gold", "Standard", "Premium", "Get Card");
        return new Scene(getCardPane, 800, 600);
    }

    private static void buttonPos(Stage primaryStage, GridPane gridPane, String nameToButton1, String nameToButton2, String nameToButton3, String nameOfByte) {
        gridPane.add(setButtonDefault(nameToButton1, 60, nameOfByte, ez(primaryStage), primaryStage), 1, 3, 1, 1);
        gridPane.add(setButtonDefault(nameToButton2, 0, nameOfByte, ez(primaryStage), primaryStage), 3, 3, 1, 1);
        gridPane.add(setButtonDefault(nameToButton3, -60, nameOfByte, ez(primaryStage), primaryStage), 5, 3, 1, 1);
    }
    //ex
    private static Scene ez(Stage primaryStage) {
        GridPane ezPane = new GridPane();
        setDefault(ezPane, primaryStage);
        setLayoutColumns(ezPane);
        setLayoutRows(ezPane);
        return new Scene(ezPane, 800, 600);
    }
    //Setting the main structure of the every Scene
    private static VBox displayMainPanel(Stage primaryStage) {
        VBox Panels = new VBox();
        Button Home = new Button("Home");
        Button History = new Button("History");
        Button Transfers = new Button("Transfers");
        Button Profile = new Button("Profile");
        Panels.getChildren().addAll(Home, History, Transfers, Profile);
        //настройка размеров панелек
        for (Node node : Panels.getChildren()) {
            if (node instanceof Button button) {
                button.setPrefSize(200 ,125);
                button.setBackground(Background.fill(Color.TEAL));
            }
        }

        Home.setOnAction(_ -> primaryStage.setScene(goToSceneHome(primaryStage)));
        History.setOnAction(_ -> primaryStage.setScene(goToHistoryScene(primaryStage)));
        Transfers.setOnAction(_ -> primaryStage.setScene(goToTransfersScene(primaryStage)));
        Profile.setOnAction(_ -> primaryStage.setScene(goToProfileScene(primaryStage)));

        return Panels;
    }
    private static HBox displayTopMenu(Stage avatarStage) {
        Stage primaryStage = new Stage();
        HBox topMenu = new HBox();
        HBox nameBank = new HBox();
        Text nameBankText = new Text("TemBank");
        nameBankText.setFill(Color.WHITE);
        nameBank.setAlignment(Pos.CENTER);
        nameBank.getChildren().add(nameBankText);
        nameBank.setPrefSize(400, 50);
        nameBank.setAlignment(Pos.CENTER_LEFT);
        nameBank.setPadding(new Insets(0, 0, 0, 20));
        Button avatar = new Button("Account");
        avatar.setOnAction(e -> {
            avatarStage.close();
            AccountWindow.displayAccountWindow(primaryStage);
        });
        avatar.setTextFill(Color.WHITE);
        avatar.setPrefSize(400, 50);
        avatar.setAlignment(Pos.CENTER_RIGHT);
        avatar.setPadding(new Insets(0, 20, 0, 0));
        avatar.setBackground(Background.fill(Color.NAVY));
        topMenu.setBackground(Background.fill(Color.NAVY));
        topMenu.getChildren().addAll(nameBank, avatar);
        return topMenu;
    }
    private static HBox displayFooter() {
        HBox footer = new HBox();
        footer.setBackground(Background.fill(Color.NAVY));
        Text titleCompanyText = new Text("(с) Alter");
        Text nameFounderText = new Text("Asanov Temirlan");
        titleCompanyText.setFill(Color.WHITE);
        nameFounderText.setFill(Color.WHITE);
        HBox titleCompany = new HBox(titleCompanyText);
        HBox nameFounder = new HBox(nameFounderText);

        footer.getChildren().addAll(titleCompany, nameFounder);
        for (Node node : footer.getChildren()) {
            if (node instanceof HBox hbox) {
                hbox.setAlignment(Pos.CENTER);
                hbox.setPrefSize(400, 50);
            }
        }
        return footer;
    }


    //Other methods to simplify the code
    private static VBox displayTheInfo(String title, String info) {
        HBox titleLabel = new HBox();
        titleLabel.setAlignment(Pos.CENTER);
        Text titleText = new Text(title);
        titleText.setFont(Font.font("Verdana", 24));
        titleText.setFill(Color.LIGHTBLUE);
        titleLabel.setPrefSize(360, 125);
        titleLabel.getChildren().add(titleText);

        HBox infoLabel = new HBox();
        Text infoText = new Text(info);
        infoText.setFont(Font.font("Arial", 14));
        infoLabel.setAlignment(Pos.CENTER);
        infoText.setFill(Color.BLACK);
        infoLabel.getChildren().add(infoText);


        VBox labelBox = new VBox();
        labelBox.setSpacing(100);
        labelBox.getChildren().addAll(titleLabel, infoLabel);
        return labelBox;
    }
    private static StackPane setButtonDefault(String buttonName, int translationX, String byteName, Scene sceneButton, Stage primaryStage) {
        Rectangle defaultRectangle = new Rectangle(110, 150);
        defaultRectangle.setArcHeight(30);
        defaultRectangle.setArcWidth(30);
        defaultRectangle.setFill(Color.TEAL);

        VBox defaultBox = new VBox();
        Text defaultText = new Text(buttonName);
        defaultText.setTranslateY(-20);
        defaultText.setFill(Color.WHITE);
        defaultText.setFont(Font.font("Times New Romance", 20));
        defaultBox.setPrefSize(110, 150);
        defaultBox.getChildren().addAll(defaultText);

        Button buttonGetCard = new Button(byteName);
        buttonGetCard.setTranslateY(30);
        buttonGetCard.setPrefSize(90, 20);
        buttonGetCard.setBackground(Background.fill(Color.WHITE));
        buttonGetCard.setStyle("-fx-background-radius: 30;");
        buttonGetCard.setTextFill(Color.TEAL);
        buttonGetCard.setOnMouseEntered(_ -> {
            buttonGetCard.setBackground(Background.fill(Color.NAVY));
            buttonGetCard.setTextFill(Color.WHITE);
            buttonGetCard.setStyle("-fx-background-color: navy;" + "-fx-background-radius: 30;");
        });
        buttonGetCard.setOnMouseExited(_ -> {
            buttonGetCard.setBackground(Background.fill(Color.WHITE));
            buttonGetCard.setTextFill(Color.TEAL);
            buttonGetCard.setStyle("-fx-background-color: white;" + "-fx-background-radius: 30;");
        });
        buttonGetCard.setOnAction(_ -> primaryStage.setScene(sceneButton));

        StackPane paneDefault = new StackPane();
        paneDefault.getChildren().addAll(defaultRectangle, defaultText, buttonGetCard);
        paneDefault.setTranslateY(55);
        paneDefault.setTranslateX(translationX);
        return paneDefault;
    }
    public static void setDefault(GridPane layoutHome, Stage primaryStage) {
        layoutHome.setGridLinesVisible(false);
        layoutHome.add(displayTopMenu(primaryStage), 0, 0, 6, 1);
        layoutHome.add(displayMainPanel(primaryStage), 0, 1, 1, 4);
        layoutHome.add(displayFooter(), 0, 5, 6, 1);
    }

    private static void setLayoutRows(GridPane layoutHome) {
        layoutHome.getRowConstraints().add(new RowConstraints(50));
        layoutHome.getRowConstraints().add(new RowConstraints(125));
        layoutHome.getRowConstraints().add(new RowConstraints(125));
        layoutHome.getRowConstraints().add(new RowConstraints(125));
        layoutHome.getRowConstraints().add(new RowConstraints(125));
        layoutHome.getRowConstraints().add(new RowConstraints(50));
    }
    private static void setLayoutColumns(GridPane layoutHome) {
        layoutHome.getColumnConstraints().add(new ColumnConstraints(200));
        layoutHome.getColumnConstraints().add(new ColumnConstraints(120));
        layoutHome.getColumnConstraints().add(new ColumnConstraints(120));
        layoutHome.getColumnConstraints().add(new ColumnConstraints(120));
        layoutHome.getColumnConstraints().add(new ColumnConstraints(120));
        layoutHome.getColumnConstraints().add(new ColumnConstraints(120));
    }

}