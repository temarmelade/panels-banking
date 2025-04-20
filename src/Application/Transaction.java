package Application;

public class Transaction {
    private final String date;
    private final String type;
    private final double amount;

    public Transaction(String date, String type, double amount) {
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    // Геттеры (нужны для TableView)
    public String getDate() { return date; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
}
