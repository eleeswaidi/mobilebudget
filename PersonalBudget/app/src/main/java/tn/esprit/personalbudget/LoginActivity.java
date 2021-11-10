package tn.esprit.personalbudget;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tn.esprit.personalbudget.dao.UserDao;
import tn.esprit.personalbudget.database.AppDataBase;
import tn.esprit.personalbudget.db.entities.User;

public class LoginActivity extends AppCompatActivity {
    EditText login,password;
    Button logbtn;
    public static String username;
    public static int  idutil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.loginui);
        password = findViewById(R.id.passwordui);
        logbtn = findViewById(R.id.btnlog);
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginText = login.getText().toString();
                String passText = password.getText().toString();
                if(loginText.isEmpty()||passText.isEmpty()){
                    Toast.makeText(LoginActivity.this, "fill all fiels", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Perform query
                    AppDataBase userDataBase = AppDataBase.getAppDatabase(getApplicationContext());
                    UserDao userDao = userDataBase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user =  userDao.login(loginText,passText);
                            if(user==null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                String name = user.username;
                                username = name;
                                int uId =user.id;
                                idutil=uId;
                                startActivity(new Intent(LoginActivity.this,IncomeActivity.class).
                                        putExtra("name",name));

                            }
                        }
                    }).start();
                }
            }
        });
    }
}