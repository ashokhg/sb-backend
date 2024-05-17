package com.practice.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.practice.demo.entities.Courses;
import com.practice.demo.service.HomeService;

@RestController
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	@GetMapping("/")
	public String home() {
		return "this is home page";
		
	}
	
	@PostMapping("/course")
	public ResponseEntity<Courses> addCourse(@RequestBody Courses course) {
		try {
			Courses courses =  homeService.addCourse(course);
			if(courses == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.of(Optional.of(courses));
			
		} catch (InternalServerError e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}	
	}
	
	@GetMapping("/course")
	public ResponseEntity<List<Courses>> getAllCourses() {

		List<Courses> courses = homeService.getAllCourses();
		if(courses.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(courses));
	}
	
	@DeleteMapping("/course/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable("id") int id) {
		homeService.deleteCourse(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted the Course");
	}
	
	@PutMapping("/course/{id}")
	public ResponseEntity<String> updateCourse(@PathVariable("id") int id, @RequestBody Courses course){
		
		Optional<Courses> course1 = homeService.getCourseByID(id);
		
		if(!course1.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The course not found");
		}
		
		Courses existingCourse = course1.get();
		
		existingCourse.setCourseName(course.getCourseName());
		existingCourse.setDuration(course.getDuration());
		existingCourse.setFees(course.getFees());
		
		homeService.addCourse(existingCourse);
		
		return ResponseEntity.status(HttpStatus.OK).body("Updated the cousre");
	}
}
