package tn.esprit.personalbudget.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.personalbudget.db.entities.Expense;

@Dao
public interface ExpenseDao {
    @Insert
    void insertOne(Expense expense);
    @Delete
    void delete(Expense expense);

    @Query("DELETE FROM Expense")
    void deleteAll();

    @Query("SELECT * FROM Expense")
    List<Expense> getAll();

    @Query("SELECT * FROM Expense where name LIKE '%'|| :nameFiltred ||'%' " )
    List<Expense> getAllFiltred(String nameFiltred);
}
