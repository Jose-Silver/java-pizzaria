import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    UUID id = UUID.randomUUID();
    private String orderSnack;
    private String description;
    private int quantity;
    private LocalDateTime orderTime;
    private LocalDateTime  deliveredTime;
    private boolean finishOrder;
    private boolean makingOrder;
    private pessoa pessoa;

    public Order(){}
    public Order(String orderSnack, String description, int quantity, LocalDateTime  orderTime, LocalDateTime  deliveredTime, boolean finishOrder, boolean makingOrder, pessoa pessoa) {
        this.orderSnack = orderSnack;
        this.description = description;
        this.quantity = quantity;
        this.orderTime = orderTime;
        this.deliveredTime = deliveredTime;
        this.finishOrder = finishOrder;
        this.makingOrder = makingOrder;
        this.pessoa = pessoa;
    }
    public Order(UUID id, String orderSnack, String description, int quantity, LocalDateTime  orderTime, pessoa pessoa) {
        this.id = id;
        this.orderSnack = orderSnack;
        this.description = description;
        this.quantity = quantity;
        this.orderTime = orderTime;
        this.pessoa = pessoa;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOrderSnack() {
        return orderSnack;
    }

    public void setOrderSnack(String orderSnack) {
        this.orderSnack = orderSnack;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime  getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime  orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDateTime  getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(LocalDateTime  deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public pessoa getpessoa() {
        return pessoa;
    }

    public void setpessoa(pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public boolean isFinishOrder() {
        return finishOrder;
    }

    public void setFinishOrder(boolean finishOrder) {
        this.finishOrder = finishOrder;
    }

    public boolean isMakingOrder() {
        return makingOrder;
    }

    public void setMakingOrder(boolean makingOrder) {
        this.makingOrder = makingOrder;
    }
}