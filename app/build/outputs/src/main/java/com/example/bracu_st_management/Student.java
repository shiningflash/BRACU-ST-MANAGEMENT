package com.example.bracu_st_management;

public class Student {
    private String Email_id, Name, Contact_no, Department, Student_id, student_tutor;

    public String getEmail_id() {
        return Email_id;
    }

    public void setEmail_id(String email_id) {
        Email_id = email_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact_no() {
        return Contact_no;
    }

    public void setContact_no(String contact_no) {
        Contact_no = contact_no;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getStudent_id() {
        return Student_id;
    }

    public void setStudent_id(String student_id) {
        Student_id = student_id;
    }

    public String getStudent_tutor() {
        return student_tutor;
    }

    public void setStudent_tutor(String student_tutor) {
        this.student_tutor = student_tutor;
    }
}
