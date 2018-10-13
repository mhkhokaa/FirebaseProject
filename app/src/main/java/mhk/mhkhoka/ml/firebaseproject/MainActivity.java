package mhk.mhkhoka.ml.firebaseproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText mail,pass;
    private Button reg;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        mail=findViewById(R.id.emailet);
        pass=findViewById(R.id.passet);

        reg=findViewById(R.id.regbtn);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setmail=mail.getText().toString();
                String setpass=pass.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(setmail,setpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            gotosecond();
                        }  else {

                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }

    private void gotosecond() {
        Intent a=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(a);
    }
}
