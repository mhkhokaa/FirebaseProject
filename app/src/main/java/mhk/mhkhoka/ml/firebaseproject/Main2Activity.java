package mhk.mhkhoka.ml.firebaseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    private EditText name,status;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name=findViewById(R.id.nameet);
        status=findViewById(R.id.statuset);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
       final String id=firebaseUser.getUid();

       Button profile_btn=findViewById(R.id.profilebtn);
       profile_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent profile=new Intent(Main2Activity.this,ProfileActivity.class);
               startActivity(profile);
           }
       });



        Button save=findViewById(R.id.savebtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myname=name.getText().toString();
                String mystat=status.getText().toString();

                if (!TextUtils.isEmpty(myname)&&!TextUtils.isEmpty(mystat)){

                databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(id);


                HashMap<String,String> usermap=new HashMap<>();
                usermap.put("name",myname);
                usermap.put("status",mystat);

                databaseReference.setValue(usermap);
                Toast.makeText(getApplicationContext(),"Data Uploaded",Toast.LENGTH_LONG).show();

                }else {

                    Toast.makeText(getApplicationContext(),"Data Upload Failed",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
