package android.Contacts3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    Button addToGroup;
    TextView contactName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ContactStore store = ContactStore.getInstance();
        contactName = findViewById(R.id.individual_contact_name);
        Contact contact = store.getSelectedContact();
        if (contact.getFirstName() != null && contact.getLastName() != null) {
            contactName.setText(contact.getFirstName() + " " + contact.getLastName());
        }
        else {
            contactName.setText(contact.getFirstName());
        }



        addToGroup = findViewById(R.id.add_to_group);
        addToGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openAddActivity();
            }
        });
    }

    public void openAddActivity() {
        Intent intent = new Intent(this, AddToGroupActvitity.class);

        startActivity(intent);
    }
}