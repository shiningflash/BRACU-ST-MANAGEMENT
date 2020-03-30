package com.example.bracu_st_management;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ApplicantViewHolder> {
    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    private void showContactInfo(Context c, String title, String contactInfo) {
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle(title)
                .setMessage(contactInfo)
                .create();
        dialog.show();
    }

    @NonNull
    @Override
    public ApplicantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_student_list, null);
        return new ApplicantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicantViewHolder holder, int position) {
        final Student student = studentList.get(position);
        String srl = ((position < 9 ? "0" : "") + (position+1));
        holder.srl_no.setText(srl);
        holder.std_id.setText(student.getStudent_id());
        holder.std_name.setText(student.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // showContactInfo(context, "Contact Info", "Email: " +
                //        student.getEmail_id() + "\nPhone: " + student.getContact_no());
                Intent intent = new Intent(context, UserProfileActivity.class);
                intent.putExtra("Email", student.getEmail_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    class ApplicantViewHolder extends RecyclerView.ViewHolder {
        TextView srl_no, std_id, std_name;

        public ApplicantViewHolder(@NonNull View itemView) {
            super(itemView);

            srl_no = itemView.findViewById(R.id.srl_no);
            std_id = itemView.findViewById(R.id.std_id);
            std_name = itemView.findViewById(R.id.std_name);
        }
    }
}
