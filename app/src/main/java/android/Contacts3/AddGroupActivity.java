package android.Contacts3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;


public class AddGroupActivity extends AppCompatActivity {

    // activity for adding a single group

    private Button mSubmit;
    private EditText mEditName;



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_group);
            /*add up button*/
            // calling the action bar
            ActionBar actionBar = getSupportActionBar();
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);

            ContactStore store = ContactStore.getInstance();


            mEditName = (EditText) findViewById(R.id.group_string);
            mSubmit = findViewById(R.id.submit_group);

            mSubmit.setOnClickListener(new View.OnClickListener() {
                final String TAG = "Add group button listener";

                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: ");
                    int duration = Toast.LENGTH_SHORT;
                    String text = mEditName.getText().toString()+" group created";
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                    store.addGroup(new ContactGroup(mEditName.getText().toString()));

                }
            });
        }
}