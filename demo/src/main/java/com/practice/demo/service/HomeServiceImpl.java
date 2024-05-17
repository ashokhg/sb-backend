package com.practice.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.dao.CourseDAO;
import com.practice.demo.entities.Courses;

@Service
public class HomeServiceImpl implements HomeService {
	
	@Autowired
	CourseDAO courseDao;
	
	@Override
	public List<Courses> getAllCourses() {
		return courseDao.findAll();
	}
	
	@Override
	public Courses addCourse(Courses courses) {
		return courseDao.save(courses);
	}
	
	@Override
	public void deleteCourse(int id) {
		courseDao.deleteById(id);
	}

	@Override
	public Optional<Courses> getCourseByID(int id) {
		return courseDao.findById(id);
	}
	
}
