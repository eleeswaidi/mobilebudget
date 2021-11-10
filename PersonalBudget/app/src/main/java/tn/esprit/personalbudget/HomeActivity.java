package tn.esprit.personalbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import tn.esprit.personalbudget.database.AppDataBase;

public class HomeActivity extends AppCompatActivity {
    private AppDataBase database;
    TextView titre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        database= AppDataBase.getAppDatabase(this.getApplicationContext());
        database.expenseDao().deleteAll();
        database.incomeDao().deleteAll();
       // database.expenseDao().getAll();
        titre=(TextView) findViewById(R.id.titre);
        titre.setText(titre.getText()+" depense have :"+database.expenseDao().getAll().size());

    }
}