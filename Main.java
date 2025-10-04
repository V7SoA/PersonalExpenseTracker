package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private ExpenseManager manager = new ExpenseManager();

    @Override
    public void start(Stage stage) {
        stage.setTitle(" Personal Expense Tracker");

        VBox root = new VBox(15);
        root.setStyle("-fx-padding: 20; -fx-background-color: #e6881cff;");

        Label title = new Label("Personal Expense Tracker");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Button addBtn = new Button("Add Expense");
        Button viewBtn = new Button("View Expenses");
        Button updateBtn = new Button("Update Expense");
        Button deleteBtn = new Button("Delete Expense");

        addBtn.setStyle("-fx-font-size: 14px; -fx-background-color: lightgreen;");
        viewBtn.setStyle("-fx-font-size: 14px; -fx-background-color: lightblue;");
        updateBtn.setStyle("-fx-font-size: 14px; -fx-background-color: lightyellow;");
        deleteBtn.setStyle("-fx-font-size: 14px; -fx-background-color: lightcoral;");

        addBtn.setOnAction(e -> addExpenseUI());
        viewBtn.setOnAction(e -> viewExpensesUI());
        updateBtn.setOnAction(e -> updateExpenseUI());
        deleteBtn.setOnAction(e -> deleteExpenseUI());

        root.getChildren().addAll(title, addBtn, viewBtn, updateBtn, deleteBtn);

        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void addExpenseUI() {
        Stage window = new Stage();
        window.setTitle("Add Expense");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-padding: 20;");

        TextField amountField = new TextField();
        TextField dateField = new TextField();
        TextField categoryField = new TextField();
        TextField noteField = new TextField();

        amountField.setPromptText("Amount");
        dateField.setPromptText("YYYY-MM-DD");
        categoryField.setPromptText("Category");
        noteField.setPromptText("Note");

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();
                String category = categoryField.getText();
                String note = noteField.getText();

                int id = manager.generateId();
                manager.addExpense(new Expense(id, amount, date, category, note));
                window.close();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input!");
                alert.showAndWait();
            }
        });

        grid.add(new Label("Amount:"), 0, 0);
        grid.add(amountField, 1, 0);
        grid.add(new Label("Date:"), 0, 1);
        grid.add(dateField, 1, 1);
        grid.add(new Label("Category:"), 0, 2);
        grid.add(categoryField, 1, 2);
        grid.add(new Label("Note:"), 0, 3);
        grid.add(noteField, 1, 3);
        grid.add(addBtn, 1, 4);

        Scene scene = new Scene(grid, 300, 250);
        window.setScene(scene);
        window.showAndWait();
    }

    private void viewExpensesUI() {
        Stage window = new Stage();
        window.setTitle("View Expenses");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20;");

        TextArea area = new TextArea();
        area.setEditable(false);

        StringBuilder sb = new StringBuilder("ID | Date | Amount | Category | Note\n");
        for (Expense e : manager.getExpenses()) { // fixed variable
            sb.append(e).append("\n");
        }

        area.setText(sb.toString());
        layout.getChildren().add(area);

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.show();
    }

    private void updateExpenseUI() {
        Stage window = new Stage();
        window.setTitle("Update Expense");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-padding: 20;");

        TextField idField = new TextField();
        TextField amountField = new TextField();
        TextField dateField = new TextField();
        TextField categoryField = new TextField();
        TextField noteField = new TextField();

        idField.setPromptText("ID");
        amountField.setPromptText("Amount");
        dateField.setPromptText("YYYY-MM-DD");
        categoryField.setPromptText("Category");
        noteField.setPromptText("Note");

        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();
                String category = categoryField.getText();
                String note = noteField.getText();

                manager.updateExpense(id, amount, date, category, note);
                window.close();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input!");
                alert.showAndWait();
            }
        });

        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Amount:"), 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(new Label("Date:"), 0, 2);
        grid.add(dateField, 1, 2);
        grid.add(new Label("Category:"), 0, 3);
        grid.add(categoryField, 1, 3);
        grid.add(new Label("Note:"), 0, 4);
        grid.add(noteField, 1, 4);
        grid.add(updateBtn, 1, 5);

        Scene scene = new Scene(grid, 300, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    private void deleteExpenseUI() {
        Stage window = new Stage();
        window.setTitle("Delete Expense");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20;");

        TextField idField = new TextField();
        idField.setPromptText("ID");

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                manager.deleteExpense(id);
                window.close();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input!");
                alert.showAndWait();
            }
        });

        layout.getChildren().addAll(new Label("Enter Expense ID:"), idField, deleteBtn);

        Scene scene = new Scene(layout, 250, 150);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
