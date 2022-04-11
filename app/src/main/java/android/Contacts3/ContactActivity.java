package android.Contacts3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactActivity extends AppCompatActivity {

    Button addToGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

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