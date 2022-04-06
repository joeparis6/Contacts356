package android.Contacts3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    // activity for adding a single contact

    private EditText first_name;
    private EditText last_name;
    private EditText phone_number;
    private EditText email;
    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        ContactStore store = ContactStore.getInstance();
        first_name = (EditText) findViewById(R.id.edit_first_name);
        last_name = (EditText) findViewById(R.id.edit_last_name);
        phone_number = (EditText) findViewById(R.id.edit_phone);
        email = (EditText) findViewById(R.id.edit_email);
        mSubmit = findViewById(R.id.add_contact);

        Contact contact = new Contact(first_name.getText().toString(), last_name.getText().toString(),
                phone_number.getText().toString());

        /* Check if email slot has entry */
        if (email.getText().toString().length() > 1) {
            contact.setEmail(email.getText().toString());
        }

        mSubmit.setOnClickListener(new View.OnClickListener() {
            final String TAG = "Add contact button listener";
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                int duration = Toast.LENGTH_SHORT;
                String text = "Contact Added";
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
                store.addContact(contact);
                openContactActivity();

            }
        });
    }

    public void openContactActivity() {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

}
