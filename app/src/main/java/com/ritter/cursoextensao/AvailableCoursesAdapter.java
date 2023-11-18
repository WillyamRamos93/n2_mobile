package com.ritter.cursoextensao;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class AvailableCoursesAdapter extends RecyclerView.Adapter<AvailableCoursesAdapter.CourseViewHolder> {

    private Context context;
    private List<CourseModel> coursesList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onRegisterButtonClick(int position);
    }

    public AvailableCoursesAdapter(Context context, List<CourseModel> coursesList, OnItemClickListener listener) {
        this.context = context;
        this.coursesList = coursesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.available_courses_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        CourseModel course = coursesList.get(position);

        holder.courseNameTextView.setText(course.getName());
        holder.courseSessionTextView.setText(course.getSession());
        holder.courseDayTextView.setText(course.getWeekDay());
        holder.courseDescTextView.setText(course.getDescription());

        // Defina o clique no botão
        holder.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRegisterButtonClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseNameTextView;
        TextView courseSessionTextView;
        TextView courseDayTextView;
        TextView courseDescTextView;
        Button registerButton;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.textViewCourseName);
            courseSessionTextView = itemView.findViewById(R.id.textViewCourseSession);
            courseDayTextView = itemView.findViewById(R.id.textViewCourseDay);
            courseDescTextView = itemView.findViewById(R.id.textViewCourseDesc);
            registerButton = itemView.findViewById(R.id.btn_register);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}