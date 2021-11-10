package tn.esprit.personalbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.personalbudget.dao.UserDao;
import tn.esprit.personalbudget.database.AppDataBase;
import tn.esprit.personalbudget.db.entities.User;

public class MainActivity extends AppCompatActivity {
    EditText firstNameEt,lastNameEt,loginEt,passwordEt;
    Button registerBtn,loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameEt = findViewById(R.id.firstName);
        lastNameEt = findViewById(R.id.lastName);
        loginEt = findViewById(R.id.login);
        passwordEt = findViewById(R.id.password);
        registerBtn = findViewById(R.id.register);
        loginbtn = findViewById(R.id.signin);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating user entity
                User u = new User();
                u.setFirstName(firstNameEt.getText().toString());
                u.setLastName(lastNameEt.getText().toString());
                u.setUsername(loginEt.getText().toString());
                u.setPassword(passwordEt.getText().toString());
                if (validateInput(u)) {
                    //insert operation
                    AppDataBase userDataBase = AppDataBase.getAppDatabase(getApplicationContext());
                    final UserDao userDao = userDataBase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //register user
                            userDao.register(u);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "user registered successfully", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(MainActivity.this, "fill all fiels", Toast.LENGTH_SHORT).show();
                }
            }

        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

    }
    private Boolean validateInput(User u){
        if(u.getFirstName().isEmpty()||u.getLastName().isEmpty()||u.getUsername().isEmpty()||u.getPassword().isEmpty())
        {return false;}
        else
        {return true;}
    }
}