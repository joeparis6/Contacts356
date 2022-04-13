package android.Contacts3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllContactsActivity extends AppCompatActivity {

    public List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        ContactStore store = ContactStore.getInstance();
        contacts = store.getAllContacts();

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllContactsActivity.this));

        AllContactsActivity.ContactAdapter adapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(adapter);

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

    /*
    public static class ContactAdapter extends RecyclerView.Adapter<AllContactsActivity.ContactAdapter.ViewHolder> {

        private List<Contact> localDataSet;
        public ContactAdapter(List<Contact> contacts) {
            localDataSet = contacts;
        }


        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                textView = (TextView) view.findViewById(R.id.contact_name);
            }

            public TextView getTextView() {
                return textView;
            }
        }



        // Create new views (invoked by the layout manager)
        @Override
        public AllContactsActivity.ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.single_contact, viewGroup, false);

            return new AllContactsActivity.ContactAdapter.ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(AllContactsActivity.ContactAdapter.ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            String firstName = localDataSet.get(position).getFirstName();
            String lastName = localDataSet.get(position).getLastName();

            if (lastName != null) {
                viewHolder.getTextView().setText(firstName + " " + lastName);
            }
            else {
                viewHolder.getTextView().setText(firstName);
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }
    */
    ////////////
    private class ContactAdapter extends RecyclerView.Adapter<AllContactsActivity.contactViewHolder> {
        private List<Contact> localDataSet;
        public ContactAdapter(List<Contact> contacts) {
            localDataSet = contacts;
        }
        private Contact contact;


        @NonNull
        @Override
        public AllContactsActivity.contactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            view = getLayoutInflater().inflate(R.layout.single_contact, parent, false);

            return new AllContactsActivity.contactViewHolder(view, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull AllContactsActivity.contactViewHolder holder, int position) {
            holder.bind(localDataSet.get(position));

        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }

    private class contactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Contact contact;
        private TextView contactName;

        public contactViewHolder(@NonNull View view, int viewType) {

            super(view);

            itemView.setOnClickListener(this);
            contactName = itemView.findViewById(R.id.contact_name);

        }

        private void bind(Contact contact) {
            this.contact = contact;
            Log.d("Contact: ",  contact.getFirstName());
            if (contact.getFirstName() != null && contact.getLastName() != null) {
                contactName.setText(contact.getFirstName() + " " + contact.getLastName());
            }
            else {
                contactName.setText(contact.getFirstName());
            }


        }

        @Override
        public void onClick(View v) {
            ContactStore store = ContactStore.getInstance();

            store.setSelectedContact(contact);
            openContactActivity();
        }
    }

    public void openContactActivity() {
        Intent intent = new Intent(this, ContactActivity.class);

        startActivity(intent);
    }
}
