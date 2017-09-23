package com.example.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tutorial3.model.StudentModel;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.service.StudentService;

@Controller
public class StudentController {
	private final StudentService studentService;

	public StudentController() {
		studentService = new InMemoryStudentService();
	}

	@RequestMapping("/student/add")
	public String add(@RequestParam(value = "npm", required = true) String npm,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "gpa", required = true) double gpa) {	
		StudentModel student = new StudentModel(name, npm, gpa);
		studentService.addStudent(student);
		return "add";
	}
	
	@RequestMapping("/student/view")
	public String view(@RequestParam(value="npm", required=false) String npm, Model model) {
		StudentModel student = studentService.selectStudent(npm);
		
		if(student == null) {
			return "empty-data";
		}
		else {
			model.addAttribute("student", student);
			return "view";
		}
	}
	
	@RequestMapping("/student/viewall")
	public String viewAll(Model model) {
		List<StudentModel> students = studentService.selectAllStudent();
		model.addAttribute("students", students);
		return "viewall";
	}
	
	@RequestMapping("/student/view/{npm}")
	public String viewStudent(Model model, @PathVariable String npm) {
		StudentModel student = studentService.selectStudent(npm);
		
		if(student == null) {
			return "empty-data";
		}
		else {
			model.addAttribute("student", student);
			return "view";
		}
	}
	
	@RequestMapping(value={"/student/delete", "/student/delete/{npm}"})
	public String delete(@PathVariable Optional<String> npm) {
		if(npm.isPresent()) {
			StudentModel student = studentService.selectStudent(npm.get());
			
			if(student == null) {
				return "delete-cancel";
			}
			else {
				studentService.deleteStudent(npm);
				return "delete";
			}
		}
		else {
			return "delete-cancel";
		}
	}
}
