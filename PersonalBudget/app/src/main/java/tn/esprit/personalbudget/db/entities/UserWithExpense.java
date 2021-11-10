package tn.esprit.personalbudget.db.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithExpense {
    @Embedded public User user ;
    @Relation(
            parentColumn = "id",
            entityColumn = "idUser"
    )
    public List<Expense> expenses ;

    public UserWithExpense() {
        this.user = user;
        this.expenses = expenses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "UserWithExpense{" +
                "user=" + user +
                ", expenses=" + expenses +
                '}';
    }
}
