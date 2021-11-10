package tn.esprit.personalbudget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tn.esprit.personalbudget.database.AppDataBase;
import tn.esprit.personalbudget.db.entities.Expense;
import tn.esprit.personalbudget.db.entities.ExpenseAdapter;
import tn.esprit.personalbudget.db.entities.Income;

public class ListExpenses extends AppCompatActivity {
    RecyclerView recyclerViewExpenses;

    ExpenseAdapter expenseAdapter;
    List<Expense> expenses=new ArrayList<>();
    private AppDataBase database;
    ImageButton imageButton;
    SearchView filtersearch;
    EditText incomedisplay;
    EditText restincome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_expenses);
        imageButton=(ImageButton) findViewById(R.id.imageButton);
        incomedisplay=(EditText) findViewById(R.id.incomedisplay);

        recyclerViewExpenses=(RecyclerView) findViewById(R.id.recyclerViewExpenses);
        restincome=(EditText) findViewById(R.id.restincome);
        database= AppDataBase.getAppDatabase(this.getApplicationContext());



       //remplir recycleview
        expenses=database.expenseDao().getAll();
        expenseAdapter=new ExpenseAdapter(expenses,this);
        recyclerViewExpenses.setAdapter(expenseAdapter);

        //remplir income text
        fillIncome();

        //filtrer
        filtersearch=(SearchView) findViewById(R.id.filtersearch);
        filtersearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filtrer ();

                return false;
        }
    });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddexpenseActivity.class);
                startActivity(i);
            }
        });

        //delete all expenses
        TextView deleteall=(TextView) findViewById(R.id.deleteall);
        deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.expenseDao().deleteAll();
                Intent i = new Intent(getApplicationContext(), ListExpenses.class);
                startActivity(i);
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

    private  void filtrer (){
        expenses=database.expenseDao().getAllFiltred(filtersearch.getQuery().toString());
        expenseAdapter=new ExpenseAdapter(expenses,this);
        recyclerViewExpenses.setAdapter(expenseAdapter);
    }

    private  void fillIncome(){
        List<Income> incomes=database.incomeDao().getAll();
        Date date=new Date();
        for(Income income:incomes){
            if(date.getMonth()+1==income.getMonth()){
                incomedisplay.setText(Integer.toString( income.getIncome())+"DT");

                //calculer rest
                int calculexpense=0;
                for(Expense expense:expenses){
                    calculexpense+=expense.getCost();
                }
               restincome.setText(Integer.toString(  income.getIncome()-calculexpense)+"DT");
                if(income.getIncome()-calculexpense<=0){
                    imageButton.setEnabled(false);
                }
            }
        }
    }

}