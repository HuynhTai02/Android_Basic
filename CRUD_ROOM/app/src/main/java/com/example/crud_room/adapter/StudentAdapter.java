package com.example.crud_room.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_room.R;
import com.example.crud_room.database.entities.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> studentList;
    private final IClickItemStudent iClickItemStudent;

    //interface callback cho sự kiện update,delete
    public interface IClickItemStudent {
        void editStudent(Student student);

        void deleteStudent(Student student);
    }

    public StudentAdapter(IClickItemStudent iClickItemStudent) {
        this.iClickItemStudent = iClickItemStudent;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Student> list) {
        this.studentList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        final Student student = studentList.get(position);
        if (student == null) {
            return;
        }
        //set dữ liệu lên giao diện
        holder.tvStudentName.setText(student.getStudentName());
        holder.tvAddress.setText(student.getAddress());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemStudent.editStudent(student);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemStudent.deleteStudent(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (studentList != null) {
            return studentList.size();
        }
        return 0;
    }
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvStudentName;
        private final TextView tvAddress;
        private final Button btnEdit;
        private final Button btnDelete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAddress = itemView.findViewById(R.id.tv_address);
            tvStudentName = itemView.findViewById(R.id.tv_studentName);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}