package android.Contacts3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GroupActivity extends AppCompatActivity {

    public List<Contact> contacts;
    TextView groupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        ContactStore store = ContactStore.getInstance();

        contacts = store.getSelectedGroup().getMembers();
        ContactGroup group = store.getSelectedGroup();

        groupName = findViewById(R.id.group_name);
        Contact contact = store.getSelectedContact();
        groupName.setText(group.getName());


        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(GroupActivity.this));

        GroupActivity.ContactAdapter adapter = new GroupActivity.ContactAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }

    public static class ContactAdapter extends RecyclerView.Adapter<GroupActivity.ContactAdapter.ViewHolder> {

        private List<Contact> localDataSet;
        public ContactAdapter(List<Contact> contacts) {
            localDataSet = contacts;
        }

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
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
        public GroupActivity.ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.single_contact, viewGroup, false);

            return new GroupActivity.ContactAdapter.ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(GroupActivity.ContactAdapter.ViewHolder viewHolder, final int position) {

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

}