package android.Contacts3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddToGroupActvitity extends AppCompatActivity {

    public List<ContactGroup> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_group);

        ContactStore store = ContactStore.getInstance();
        groups = store.getAllGroups();
        //store.setSelectedGroup(store.getGroup1());

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddToGroupActvitity.this));

        AddToGroupActvitity.GroupAdapter adapter = new AddToGroupActvitity.GroupAdapter(groups);
        recyclerView.setAdapter(adapter);
    }


    private class GroupAdapter extends RecyclerView.Adapter<AddToGroupActvitity.GroupViewHolder> {
        private List<ContactGroup> localDataSet;
        public GroupAdapter(List<ContactGroup> groups) {
            localDataSet = groups;
        }
        private ContactGroup group;


        @NonNull
        @Override
        public AddToGroupActvitity.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            view = getLayoutInflater().inflate(R.layout.contact_group, parent, false);

            return new AddToGroupActvitity.GroupViewHolder(view, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull AddToGroupActvitity.GroupViewHolder holder, int position) {
            holder.bind(localDataSet.get(position));

        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }

    private class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ContactGroup group;
        private TextView groupName;

        public GroupViewHolder(@NonNull View view, int viewType) {

            super(view);

            itemView.setOnClickListener(this);
            groupName = itemView.findViewById(R.id.group_name);

        }

        private void bind(ContactGroup group) {
            this.group = group;
            groupName.setText(group.getName());


        }

        @Override
        public void onClick(View v) {
            ContactStore store = ContactStore.getInstance();

            store.setSelectedGroup(group);
            //openGroupActivity();
            ContactGroup addGroup = store.getSelectedGroup();
            addGroup.addContact(store.getSelectedContact());
            Toast toast = Toast.makeText(getApplicationContext(), "Contact added to group", Toast.LENGTH_SHORT);
            backToMain();
            // add selected contact to selected group
            //toast
        }

    }

    public void backToMain() {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

}