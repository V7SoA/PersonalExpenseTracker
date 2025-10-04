package src;

public class Expense {
    private int id;
    private double amount;
    private String date;
    private String category;
    private String note;

    public Expense(int id, double amount, String date, String category, String note) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.note = note;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public String getCategory() { return category; }
    public String getNote() { return note; }

    public void setAmount(double amount) { this.amount = amount; }
    public void setDate(String date) { this.date = date; }
    public void setCategory(String category) { this.category = category; }
    public void setNote(String note) { this.note = note; }

    @Override
    public String toString() {
        return id + " | " + date + " | ₹" + amount + " | " + category + " | " + note;
    }

    public String toFileFormat() {
        return id + "," + date + "," + amount + "," + category + "," + note;
    }

    public static Expense fromFileFormat(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) return null;
        try {
            return new Expense(
                Integer.parseInt(parts[0]),
                Double.parseDouble(parts[2]),
                parts[1],
                parts[3],
                parts[4]
            );
        } catch (Exception e) {
            return null;
        }
    }
}
