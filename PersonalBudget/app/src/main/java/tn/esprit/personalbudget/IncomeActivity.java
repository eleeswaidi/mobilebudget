package tn.esprit.personalbudget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.personalbudget.database.AppDataBase;
import tn.esprit.personalbudget.db.entities.Income;

public class IncomeActivity extends AppCompatActivity {

    EditText incomef;

    EditText year;
    Button btn;
    TextView errortext1;
    Spinner comboMonth;
    private AppDataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        database= AppDataBase.getAppDatabase(this.getApplicationContext());

        incomef=(EditText) findViewById(R.id.incomef);
        errortext1=(TextView) findViewById(R.id.errortext1);
        year=(EditText) findViewById(R.id.year);
        btn=(Button) findViewById(R.id.btn);
        comboMonth=(Spinner) findViewById(R.id.comboMonth);

        fillComboMonth();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( year.getText().toString().equals("") || incomef.getText().toString().equals("")){
                    errortext1.setVisibility(View.VISIBLE);
                    errortext1.setText("*Verify empty field(s)");
                }else{
                    Income incomeclas=new Income(Integer.parseInt( incomef.getText().toString()),comboMonth.getSelectedItemPosition()+1,Integer.parseInt(year.getText().toString()));
                    database.incomeDao().insertOne(incomeclas);
                    Intent i = new Intent(getApplicationContext(), ListExpenses.class);
                    startActivity(i);
                }

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

    public void fillComboMonth(){
        String array_spinner[];
        array_spinner=new String[12];
        array_spinner[0]="January";
        array_spinner[1]="February";
        array_spinner[2]="March";
        array_spinner[3]="April";
        array_spinner[4]="May";
        array_spinner[5]="June";
        array_spinner[6]="July";
        array_spinner[7]="August";
        array_spinner[8]="September";
        array_spinner[9]="October";
        array_spinner[10]="November";
        array_spinner[11]="December";
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, array_spinner);
        comboMonth.setAdapter(adapter);
    }
}
