package tn.esprit.personalbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import tn.esprit.personalbudget.dao.ExpenseDao;
import tn.esprit.personalbudget.database.AppDataBase;
import tn.esprit.personalbudget.db.entities.Expense;
import tn.esprit.personalbudget.db.entities.Income;

public class AddexpenseActivity extends AppCompatActivity {
      Button save;
      EditText cost;
      EditText nameexpense;
      DatePicker date;
      EditText errortext2;
    private AppDataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);

        save=(Button) findViewById(R.id.save);
        cost=(EditText) findViewById(R.id.cost);
        errortext2=(EditText) findViewById(R.id.errortext2);
        nameexpense=(EditText)findViewById(R.id.nameexpense);
        date=(DatePicker) findViewById(R.id.date);

        database= AppDataBase.getAppDatabase(this.getApplicationContext());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouter();
            }
        });


        //navbar
        TextView navincome1=(TextView) findViewById(R.id.navincome1);
        navincome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), IncomeActivity.class);
                startActivity(i);
            }
        });

        TextView navexpense1=(TextView) findViewById(R.id.navexpense1);
        navexpense1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListExpenses.class);
                startActivity(i);
            }
        });

        TextView navdeconnexion1=(TextView) findViewById(R.id.navdeconnexion1);
        navdeconnexion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });




    }

    public  void ajouter(){
        List<Expense> expenses=database.expenseDao().getAll();
        List<Income> incomes=database.incomeDao().getAll();
        Date dateaujourdhui=new Date();
        int rest=0;
        int realrest=0;
        for(Income income:incomes) {
            if (dateaujourdhui.getMonth() + 1 == income.getMonth()) {
                rest=income.getIncome();
                realrest=income.getIncome();
                //calculer rest
                int calculexpense=0;
                for(Expense expense:expenses){
                    calculexpense+=expense.getCost();
                }
                realrest=income.getIncome()-calculexpense;
                rest=income.getIncome()-calculexpense-Integer.parseInt(cost.getText().toString());
            }
        }
        if(rest>=0) {
            Expense expense = new Expense();
            expense.setName(nameexpense.getText().toString());
            expense.setCost(Integer.parseInt(cost.getText().toString()));

            Date dt = new Date(date.getYear(), date.getMonth(), date.getDayOfMonth());
            expense.setDate(dt.toString());
            database.expenseDao().insertOne(expense);
            Intent i = new Intent(getApplicationContext(), ListExpenses.class);
            startActivity(i);
        }else
        {
            errortext2.setVisibility(View.VISIBLE);
            errortext2.setText("*YOU ONLY HAVE : "+realrest);
        }
    }
}