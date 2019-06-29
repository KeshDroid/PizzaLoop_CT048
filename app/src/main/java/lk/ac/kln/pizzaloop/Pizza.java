package lk.ac.kln.pizzaloop;

public class Pizza {
    int pizzaId;
    String name;
    String description;
    float price;
    String imgurl;

   /* public Pizza(String name, String description, String imgurl) {
      //  this.pizzaId = pizzaId;
        this.name = name;
        this.description = description;
        //this.price = price;
        this.imgurl = imgurl;
    }*/

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

   // public void setPrice(String price) {
   // }
}
