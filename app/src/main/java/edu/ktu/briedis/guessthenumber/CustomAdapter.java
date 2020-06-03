package edu.ktu.briedis.guessthenumber;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private List<Results> notesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvId, tvAge, tvDifficulty;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvId = view.findViewById(R.id.tv_id);
            tvAge = view.findViewById(R.id.tv_age);
            tvDifficulty = view.findViewById(R.id.tv_difficulty);
        }
    }


    public CustomAdapter(Context context, List<Results> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_result, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Results student = notesList.get(position);

        holder.tvName.setText(student.getName());
        holder.tvId.setText(String.valueOf(student.getId()));
        holder.tvAge.setText(String.valueOf(student.getAge()));

        int diff = student.getDifficulty();

        if (diff == 0) {
            holder.tvDifficulty.setText("Easy");
        } else if (diff == 1) {
            holder.tvDifficulty.setText("Medium");
        } else if (diff == 2) {
            holder.tvDifficulty.setText("Hard");
        } else {
            holder.tvDifficulty.setText("Imposs");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, student.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}