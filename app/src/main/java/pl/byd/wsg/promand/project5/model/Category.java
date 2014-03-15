package pl.byd.wsg.promand.project5.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by sergio on 3/14/14.
 */
public class Category {

    @DatabaseField(id = true)
    int _id;

    @DatabaseField
    String _category_name;


    public Category(){}

    public Category(int id, String category_name){
        this._id = id;
        this._category_name = category_name;
    }

    public int getID(){


        return this._id;
    }

    public void setID(int id){

        this._id = id;
    }

    public String getCategoryName(){
        return this._category_name;
    }

    public void setCategoryName(String name){
        this._category_name = name;
    }

}
