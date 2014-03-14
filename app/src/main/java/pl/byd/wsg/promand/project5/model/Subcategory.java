package pl.byd.wsg.promand.project5.model;

/**
 * Created by sergio on 3/14/14.
 */
public class Subcategory {
    int _id;
    String _subcategory_name;


    public Subcategory(){}

    public Subcategory(int id, String subcategory_name){
        this._id = id;
        this._subcategory_name = subcategory_name;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getSubcategoryName(){
        return this._subcategory_name;
    }

    public void setSubcategoryName(String name){
        this._subcategory_name = name;
    }
}
