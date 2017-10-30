package com.mybaits.pojo;

import java.util.List;

public interface StudentMapper {
	
	public List<Student> selectStudentAll();
	
	public void deleteStudent(int id);

}
