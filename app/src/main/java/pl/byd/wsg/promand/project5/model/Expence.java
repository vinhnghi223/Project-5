package pl.byd.wsg.promand.project5.model;

/**
 * Created by sergio on 3/14/14.
 */
public class Expence {
    int _id;
    String _amount;
    String _date;
    String _photo; //path to directory


    public Expence(){}

    public Expence(int id, String amount, String date, String photo){
        this._id = id;
        this._amount = amount;
        this._date = date;
        this._photo = photo;
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

    public String getPhoto(){ return this._photo; }
    public void setPhoto(String photo){ this._photo = photo;}
}
