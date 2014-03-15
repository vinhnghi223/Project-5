package pl.byd.wsg.promand.project5.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by sergio on 3/14/14.
 */
public class Expense {

    @DatabaseField(id = true)
    int _id;

    @DatabaseField
    String _amount;

    @DatabaseField
    String _currency;

    @DatabaseField
    String _date;

    //@DatabaseField
    //String _photo; //path to directory

    public Expense(){}

    public Expense(int id, String amount, String date/*, String photo*/){
        this._id = id;
        this._amount = amount;
        this._date = date;
        //this._photo = photo;
    }

    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }

    public String getAmount(){ return this._amount; }
    public void setAmount(String amount){ this._amount = amount;}

    public String getDate(){ return this._date; }
    public void setDate(String date){ this._date = date;}

    //public String getPhoto(){ return this._photo; }
    //public void setPhoto(String photo){ this._photo = photo;}
}
