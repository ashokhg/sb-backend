package com.practice.demo.service;

import java.util.List;
import java.util.Optional;

import com.practice.demo.entities.Courses;

public interface HomeService {

	public List<Courses> getAllCourses();
	public Courses addCourse(Courses course);
	public void deleteCourse(int id);
	public Optional<Courses> getCourseByID(int id);
}
