package android.Contacts3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openAddActivity() {
        Intent intent = new Intent(this, AddToGroupActvitity.class);

        startActivity(intent);
    }
}