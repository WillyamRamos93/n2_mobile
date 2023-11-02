package com.ritter.cursoextensao;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.CourseViewHolder> {
    private Context context;
    private List<CourseModel> courseList;

    public Adapter(Context context, List<CourseModel> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coruses_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        CourseModel course = courseList.get(position);

        holder.courseNameTextView.setText(course.getName());
        holder.courseSessionTextView.setText(course.getSession());
        holder.courseDayTextView.setText(course.getWeekDay());
        holder.courseDescTextView.setText(course.getDescription());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseNameTextView;
        TextView courseSessionTextView;
        TextView courseDayTextView;
        TextView courseDescTextView;

        public CourseViewHolder(View itemView) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.textViewCourseName);
            courseSessionTextView = itemView.findViewById(R.id.textViewCourseSession);
            courseDayTextView = itemView.findViewById(R.id.textViewCourseDay);
            courseDescTextView = itemView.findViewById(R.id.textViewCourseDesc);
        }
    }
}
