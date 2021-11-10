package tn.esprit.personalbudget.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.personalbudget.dao.ExpenseDao;
import tn.esprit.personalbudget.dao.IncomeDao;
import tn.esprit.personalbudget.dao.UserDao;
import tn.esprit.personalbudget.db.entities.Expense;
import tn.esprit.personalbudget.db.entities.Income;
import tn.esprit.personalbudget.db.entities.User;

@Database(entities = {Expense.class, Income.class, User.class}, version = 4, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public abstract ExpenseDao expenseDao();
    public abstract IncomeDao incomeDao();
    public abstract UserDao userDao();
    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "personalbudget_db")

                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }

}