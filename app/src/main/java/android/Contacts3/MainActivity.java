package android.Contacts3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    public List<ContactGroup> groups;
    private Button todoButton;
    private Button allContactsButton;
    private ContactGroup example;
    
    private TableRow addGroupBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //ContactStore store = new ContactStore();
        ContactStore store = ContactStore.getInstance();
        groups = store.getAllGroups();
        store.setSelectedGroup(store.getGroup1());

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        GroupAdapter adapter = new GroupAdapter(groups);
        recyclerView.setAdapter(adapter);
        allContactsButton = findViewById(R.id.all_contacts_button);
        allContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAllContactsActivity();
            }
        });

        todoButton = findViewById(R.id.to_do_button);
        todoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openToDoActivity();
            }
        });


        addGroupBar = findViewById(R.id.add_group);
        addGroupBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddGroupActivity();
            }
        });

    }

    //////////////////////////

    private class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {
        private List<ContactGroup> localDataSet;
        public GroupAdapter(List<ContactGroup> groups) {
            localDataSet = groups;
        }
        private ContactGroup group;


        @NonNull
        @Override
        public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            view = getLayoutInflater().inflate(R.layout.contact_group, parent, false);

            return new GroupViewHolder(view, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
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
            openGroupActivity();
        }
    }


    ///////////////////////////
    public void openAllContactsActivity() {
        Intent intent = new Intent(this, AllContactsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void openToDoActivity() {
        Intent intent = new Intent(this, ToDoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    /*
    public static class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        private List<ContactGroup> localDataSet;
        public CustomAdapter(List<ContactGroup> groups) {
            localDataSet = groups;
        }
        private ContactGroup group;



        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private final TextView textView;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                textView = (TextView) view.findViewById(R.id.group_name);



            }

            public TextView getTextView() {
                return textView;
            }

        }



        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.contact_group, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element


            viewHolder.getTextView().setText(localDataSet.get(position).getName());
            group = localDataSet.get(position);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }


    }
    */



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.SearchMenuItem:

                //openSearchActivity();
                return true;
            case R.id.AddContactMenuItem:

                openAddContactActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    public void openGroupActivity() {
        Intent intent = new Intent(this, GroupActivity.class);

        startActivity(intent);
    }

    public void openContactActivity() {
        Intent intent = new Intent(this, ContactActivity.class);

        startActivity(intent);
    }

    public void openAddContactActivity() {
        Intent intent = new Intent(this, AddContactActivity.class);

        startActivity(intent);
    }

    public void openAddGroupActivity() {
        Intent intent = new Intent(this, AddGroupActivity.class);

        startActivity(intent);
    }
}