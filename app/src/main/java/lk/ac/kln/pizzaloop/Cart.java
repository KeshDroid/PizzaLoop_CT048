package lk.ac.kln.pizzaloop;

public class Cart {

    private String pizName;
    private Integer qty;
    private Float price;
    private Float total;

    public String getPizName() {
        return pizName;
    }

    public void setPizName(String pizName) {
        this.pizName = pizName;
    }



    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
