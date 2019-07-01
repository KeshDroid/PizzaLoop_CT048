package lk.ac.kln.pizzaloop;

public class Cart {
    private Integer id;
    private String pizName;
    private Float cPrice;
    private Integer qty;
    private Float total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPizName() {
        return pizName;
    }

    public void setPizName(String pizName) {
        this.pizName = pizName;
    }

    public Float getcPrice() {
        return cPrice;
    }

    public void setcPrice(Float cPrice) {
        this.cPrice = cPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
