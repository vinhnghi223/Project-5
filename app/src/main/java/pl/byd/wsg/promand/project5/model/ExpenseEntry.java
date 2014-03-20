package pl.byd.wsg.promand.project5.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.NumberFormat;

/**
 * Created by Le on 3/17/14.
 */
public class ExpenseEntry implements Parcelable{
    /* when you're defining a model class--that is a class that represents a single
     data entity-- and you want to represent an integer value to match up with the Android API,
      you should always data type that property in the class as a long and not an int.*/

    private long id;
    private String project;
    private String category;
    //private double amount; //for testing, use string for simplicity
    private String amount;
    private String date;
    private String comment;
    //private String photo;
    private byte[] photo;

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
    /*public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getPhoto() {
        return photo;
    }*/

    public byte[] getPhoto() {
        return photo;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    //For listview
    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        //return project + "\n("+nf.format(amount)+")";
        return "PROJECT: "+project+ "\nCATEGORY: "+category+"\nAMOUNT: "+amount+"\nDATE: " + date + "\nCOMMENT: " + comment;
    }
    //For detail view
    public String getText() {
        return "PROJECT: "+project+ "\nCATEGORY: "+category+"\nAMOUNT: "+amount+"\nDATE: "+date+"\nCOMMENT: "+comment;
    }
    public ExpenseEntry() {
    }

    public ExpenseEntry(Parcel in) {
        id= in.readLong();
        project=in.readString();
        category=in.readString();
        amount=in.readString();
        date=in.readString();
        comment=in.readString();
        //photo=in.readString();
        //in.readByteArray(photo);
        photo=in.createByteArray();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(project);
        dest.writeString(category);
        dest.writeString(amount);
        dest.writeString(date);
        dest.writeString(comment);
        //dest.writeString(photo);
        dest.writeByteArray(photo);
    }

    public static final Parcelable.Creator<ExpenseEntry> CREATOR =
            new Parcelable.Creator<ExpenseEntry>() {
                @Override
                public ExpenseEntry createFromParcel(Parcel source) {
                    return new ExpenseEntry(source);
                }
                @Override
                public ExpenseEntry[] newArray(int size) {
                    return new ExpenseEntry[size];
                }
            };
}
