package android.Contacts3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ToDoActivity extends AppCompatActivity {

    public List<String> plans;
    private EditText planInput;
    private Button addPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        planInput = (EditText) findViewById(R.id.plan_input);
        addPlan = (Button) findViewById(R.id.add_plan);
        ContactStore store = ContactStore.getInstance();

        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                store.addPlan("- " + planInput.getText().toString());
            }
        });


        plans = store.getAllPlans();

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(ToDoActivity.this));

        ToDoActivity.PlanAdapter adapter = new ToDoActivity.PlanAdapter(plans);
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

    private class PlanAdapter extends RecyclerView.Adapter<ToDoActivity.PlanViewHolder> {
        private List<String> localDataSet;
        public PlanAdapter(List<String> plans) {
            localDataSet = plans;
        }
        private Contact contact;


        @NonNull
        @Override
        public ToDoActivity.PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            view = getLayoutInflater().inflate(R.layout.single_plan, parent, false);

            return new ToDoActivity.PlanViewHolder(view, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull ToDoActivity.PlanViewHolder holder, int position) {
            holder.bind(localDataSet.get(position));

        }

        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }

    private class PlanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private String plan;
        private TextView planName;

        public PlanViewHolder(@NonNull View view, int viewType) {

            super(view);

            itemView.setOnClickListener(this);
            planName = itemView.findViewById(R.id.plan_name);

        }

        private void bind(String plan) {
            this.plan = plan;
            planName.setText(plan);

        }

        @Override
        public void onClick(View v) {

        }
    }


}