package lk.ac.kln.pizzaloop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    private EditText etTp;
    private EditText etPw;
    private Button btnSignUp;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().setTitle("SIGN IN");

        etTp = (EditText)  findViewById(R.id.etTp);
        etPw = (EditText)  findViewById(R.id.etPw);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate (etTp.getText().toString(),etPw.getText().toString());
                //openMenu();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 openSignUp();
            }
        });
    }
        public void openSignUp(){
            Intent intent = new Intent(SignIn.this,SignUp.class);
            startActivity(intent);
        }


    private void validate (String etTp, String etPw){
        if((etTp.equals("1")) && (etPw.equals("1111"))){
            Intent intent = new Intent(SignIn.this,FoodItems.class);
            startActivity(intent);
        }

        else {
            Toast.makeText(SignIn.this, "Login Fail", Toast.LENGTH_SHORT).show();

        }

    }

   /* private void openMenu(){
        Intent intent = new Intent( SignIn.this,ListMenu.class);
        startActivity(intent);
    }*/
}
