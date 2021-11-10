package tn.esprit.personalbudget.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import tn.esprit.personalbudget.db.entities.User;
import tn.esprit.personalbudget.db.entities.UserWithExpense;

@Dao
public interface UserDao {
    @Insert
     void register(User u);
    @Query("SELECT * FROM user where user_name=(:login) AND password=(:password)")
    User login(String login, String password);

    @Transaction
    @Query("SELECT * FROM User")
    public List<UserWithExpense> getUsersWithExpenses();

}
