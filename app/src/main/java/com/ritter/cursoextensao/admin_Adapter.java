package com.ritter.cursoextensao;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import com.ritter.cursoextensao.data.CourseModel;
import com.ritter.cursoextensao.data.DataBaseHelper;

import java.util.List;

public class admin_Adapter extends RecyclerView.Adapter<admin_Adapter.CourseViewHolder> {

    Button btnDelete;

    private Context context;
    private List<CourseModel> courseList;
    private DataBaseHelper dbHelper;

    public admin_Adapter(Context context, List<CourseModel> courseList) {
        this.context = context;
        this.courseList = courseList;
        this.dbHelper = new DataBaseHelper(context);
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.courses_item, parent, false);
        btnDelete = view.findViewById(R.id.btn_register);
        return new CourseViewHolder(view, btnDelete);

    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        CourseModel course = courseList.get(position);

        holder.courseNameTextView.setText(course.getName());
        holder.courseSessionTextView.setText(course.getSession());
        holder.courseDayTextView.setText(course.getWeekDay());
        holder.courseDescTextView.setText(course.getDescription());
        this.btnDelete = btnDelete;

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteCourse(course.getId());
                courseList.remove(position);
                notifyDataSetChanged();
            }
        });




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

        public CourseViewHolder(View itemView, Button btnDelete) {
            super(itemView);
            courseNameTextView = itemView.findViewById(R.id.textViewCourseName);
            courseSessionTextView = itemView.findViewById(R.id.textViewCourseSession);
            courseDayTextView = itemView.findViewById(R.id.textViewCourseDay);
            courseDescTextView = itemView.findViewById(R.id.textViewCourseDesc);
        }
    }


}
