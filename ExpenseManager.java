package src;

import java.io.*;
import java.util.*;

public class ExpenseManager {
    public List<Expense> expenses;
    public final String FILE_NAME = "expenses.txt";

    public ExpenseManager() {
        expenses = new ArrayList<>();
        loadExpensesFromFile();
    }

    public void addExpense(Expense e) {
        expenses.add(e);
        saveExpensesToFile();
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }
        System.out.println("\nID | Date | Amount | Category | Note");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    public void updateExpense(int id, double amount, String date, String category, String note) {
        for (Expense e : expenses) {
            if (e.getId() == id) {
                e.setAmount(amount);
                e.setDate(date);
                e.setCategory(category);
                e.setNote(note);
                saveExpensesToFile();
                System.out.println("Expense updated successfully!");
                return;
            }
        }
        System.out.println("Expense ID not found.");
    }

    public void deleteExpense(int id) {
        Iterator<Expense> it = expenses.iterator();
        while (it.hasNext()) {
            Expense e = it.next();
            if (e.getId() == id) {
                it.remove();
                saveExpensesToFile();
                System.out.println("Expense deleted successfully!");
                return;
            }
        }
        System.out.println("Expense ID not found.");
    }

    public int generateId() {
        if (expenses.isEmpty()) return 1;
        return expenses.get(expenses.size() - 1).getId() + 1;
    }

    private void saveExpensesToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                bw.write(e.toFileFormat());
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Error saving data: " + ex.getMessage());
        }
    }

    private void loadExpensesFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Expense e = Expense.fromFileFormat(line);
                if (e != null) expenses.add(e);
            }
        } catch (IOException ex) {
            System.out.println("Error loading data: " + ex.getMessage());
        }
    }

    // ✅ NEW: Getter for expenses
    public List<Expense> getExpenses() {
        return expenses;
    }
}
