package com.examples.android.auth;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateDelete extends AppCompatActivity {

    EditText itemName,placeName,schedule;
    CheckBox status;
    Button updateButton;
    TextView userId;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_update_delete);

        itemName = (EditText) findViewById (R.id.update_ItemName);
        placeName = (EditText) findViewById (R.id.update_PlaceName);
        schedule = (EditText) findViewById (R.id.update_schedule);
        status = (CheckBox) findViewById (R.id.update_status);
        userId = (TextView) findViewById (R.id.updateText);

        String key = getIntent ().getExtras ().get("key").toString ();
        databaseReference = FirebaseDatabase.getInstance ().getReference ().child ("appliance").child (key);



        itemName.setText (getIntent ().getStringExtra ("itemName"));
        placeName.setText (getIntent ().getStringExtra ("type"));
        schedule.setText (getIntent ().getStringExtra ("schedule"));

    }
    public void update(View view){
        databaseReference.child ("userId").setValue (userId.getText ().toString ()).addOnCompleteListener (new OnCompleteListener<Void> ( ) {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful ()){
                    Toast.makeText (updateDelete.this,"Record updated successfully",Toast.LENGTH_SHORT).show ();
                    updateDelete.this.finish ();
                }
                else
                {
                    Toast.makeText (updateDelete.this,"Record Not Updated",Toast.LENGTH_SHORT).show ();
                }

            }
        });
    }
}
