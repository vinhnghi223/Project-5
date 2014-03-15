package pl.byd.wsg.promand.project5.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by sergio on 3/14/14.
 */

public class Project {

    @DatabaseField(id = true)
    int _id;

    @DatabaseField
    String _project_name;

    public Project(){}

    public Project(int id, String project_name){
        this._id = id;
        this._project_name = project_name;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getProjectName(){
        return this._project_name;
    }

    public void setProjectName(String name){
        this._project_name = name;
    }
}
