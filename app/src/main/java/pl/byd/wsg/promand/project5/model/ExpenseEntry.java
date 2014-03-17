package pl.byd.wsg.promand.project5.model;

import java.text.NumberFormat;

/**
 * Created by Le on 3/17/14.
 */
public class ExpenseEntry {
    /* when you're defining a model class--that is a class that represents a single
     data entity-- and you want to represent an integer value to match up with the Android API,
      you should always data type that property in the class as a long and not an int.*/

    private long id;
    private String project;
    private String category;
    //private double amount; //for testing, use string for simplicity
    private String amount;
    private String comment;
    private String photo;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setProject(String project) {
        this.project = project;
    }
    public String getProject() {
        return project;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    /*public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }*/
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAmount() {
        return amount;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        //return project + "\n("+nf.format(amount)+")";
        return project+ "\n"+amount;
    }
}
