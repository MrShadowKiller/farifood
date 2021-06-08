package ir.ac.kntu.models.order;

public enum OrderStatus {
    IN_PROGRESS(0, "In progress"), DELIVERING(1, "Delivering"), COMPLETED(2, "Completed");

    private int rate;

    private String name;

    OrderStatus(int rate, String name) {
        this.rate = rate;
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
