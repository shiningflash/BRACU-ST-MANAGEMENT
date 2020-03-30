package com.example.bracu_st_management;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ApplicantAdapter extends RecyclerView.Adapter<ApplicantAdapter.ApplicantViewHolder> {
    private Context context;
    private List<ST_Applicant> applicantList;

    public ApplicantAdapter(Context context, List<ST_Applicant> applicantList) {
        this.context = context;
        this.applicantList = applicantList;
    }

    @NonNull
    @Override
    public ApplicantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_st_applicant, null);
        return new ApplicantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicantViewHolder holder, int position) {
        final ST_Applicant applicant = applicantList.get(position);

        holder.applicant_name.setText((applicant.getApplicantName()));
        holder.credit_completed.setText(("Credit completed: " + applicant.getTotalCredit()));
        holder.cgpa.setText((("Current CGPA: " + applicant.getCGPA())));
        holder.running_course.setText(("Running course: " + applicant.getCurrentCourse()));
        holder.total_class_hour.setText(("Total class hour: " + applicant.getCurrentClassHour()));

        holder.cse110_grade.setText("CSE110: " + applicant.getCSE110());
        holder.cse111_grade.setText("CSE111: " + applicant.getCSE111());
        holder.cse220_grade.setText("CSE220: " + applicant.getCSE220());
        holder.cse221_grade.setText("CSE221: " + applicant.getCSE221());
        holder.cse230_grade.setText("CSE230: " + applicant.getCSE230());
        holder.cse260_grade.setText("CSE260: " + applicant.getCSE260());
        holder.cse250_grade.setText("CSE250: " + applicant.getCSE250());
        holder.cse251_grade.setText("CSE251: " + applicant.getCSE251());

        holder.st_experience.setText("ST Experience: " + applicant.getSTExperience());
        holder.other_experience.setText("Other Experience: " + applicant.getOtherExperience());
        holder.scholarship.setText("Scholarship: " + applicant.getScholarship());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Selecting_ST.class);
                intent.putExtra("Email", applicant.getEmail_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return applicantList.size();
    }

    class ApplicantViewHolder extends RecyclerView.ViewHolder {
        TextView applicant_name, credit_completed, cgpa, running_course, total_class_hour;
        TextView cse110_grade, cse111_grade, cse220_grade, cse221_grade, cse230_grade, cse260_grade, cse250_grade, cse251_grade;
        TextView st_experience, other_experience, scholarship;

        public ApplicantViewHolder(@NonNull View itemView) {
            super(itemView);

            applicant_name = itemView.findViewById(R.id.applicant_name);
            credit_completed = itemView.findViewById(R.id.credit_completed);
            cgpa = itemView.findViewById(R.id.cgpa);
            running_course = itemView.findViewById(R.id.running_course);
            total_class_hour = itemView.findViewById(R.id.total_class_hour);

            cse110_grade = itemView.findViewById(R.id.cse110_grade);
            cse111_grade = itemView.findViewById(R.id.cse111_grade);
            cse220_grade = itemView.findViewById(R.id.cse220_grade);
            cse221_grade = itemView.findViewById(R.id.cse221_grade);
            cse230_grade = itemView.findViewById(R.id.cse230_grade);
            cse260_grade = itemView.findViewById(R.id.cse260_grade);
            cse250_grade = itemView.findViewById(R.id.cse250_grade);
            cse251_grade = itemView.findViewById(R.id.cse251_grade);

            st_experience = itemView.findViewById(R.id.st_experience);
            other_experience = itemView.findViewById(R.id.other_experience);
            scholarship = itemView.findViewById(R.id.scholarship);
        }
    }
}
