package tn.esprit.personalbudget.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.personalbudget.db.entities.Expense;
import tn.esprit.personalbudget.db.entities.Income;

@Dao
public interface IncomeDao {
    @Insert
    void insertOne(Income income);
    @Delete
    void delete(Income income);

    @Query("SELECT * FROM Income")
    List<Income> getAll();

    @Query("DELETE FROM Income")
    void deleteAll();
}
