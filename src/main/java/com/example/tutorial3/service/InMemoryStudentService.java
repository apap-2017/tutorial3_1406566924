package com.example.tutorial3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.tutorial3.model.StudentModel;

public class InMemoryStudentService implements StudentService {
	private static List<StudentModel> studentList = new ArrayList<StudentModel>();
	
	@Override
	public StudentModel selectStudent(String npm) {
		if(studentList.isEmpty()) {
			return null;
		}
		else {
			for(int i = 0; i < studentList.size(); i++) {
				if(npm.equalsIgnoreCase(studentList.get(i).getNpm())) {
					return studentList.get(i);
				}
			}
			return null;
		}
	}

	@Override
	public List<StudentModel> selectAllStudent() {
		return studentList;
	}

	@Override
	public void addStudent(StudentModel student) {
		studentList.add(student);
	}

	@Override
	public void deleteStudent(Optional<String> npm) {
		for(int i = 0; i < studentList.size(); i++) {
			if(npm.equals(studentList.get(i).getNpm())) {
				studentList.remove(i);
				break;
			}
		}
	}
	
}
