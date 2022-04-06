package android.Contacts3;

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
    public ContactGroup group;
    private Button mSubmitButton;
    private TextView mGroupName;
    private EditText mEditName;
//    private LayoutInflater inflater = Activity.getLayoutInflater();
    //private LayoutInflater inflater = LayoutInflater.from(this);


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_group);
            ContactStore store = ContactStore.getInstance();

            View view;
            //view = inflater.inflate(R.layout.activity_add_group, null);

            mGroupName = (TextView) findViewById(R.id.group_name);
            mEditName = (EditText) findViewById(R.id.group_string);
            mSubmitButton = findViewById(R.id.submit_group);

            mSubmitButton.setOnClickListener(new View.OnClickListener() {
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