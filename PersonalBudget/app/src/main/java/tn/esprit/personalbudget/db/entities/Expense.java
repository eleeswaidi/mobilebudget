package tn.esprit.personalbudget.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Expense")
public class Expense {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String Name;
    @ColumnInfo(name = "categorie")
    private String categorie;

    @ColumnInfo(name = "Cost")
    private int Cost;
    @ColumnInfo(name = "Date")
    private String date;
    @ColumnInfo(name = "idUser")
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Expense(String name, int cost, String date,String categorie) {
        categorie=categorie;
        Name = name;
        Cost = cost;
        this.date = date;
    }
    public Expense(){

    }
}
