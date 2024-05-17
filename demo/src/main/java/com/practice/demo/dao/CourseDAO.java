package com.practice.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.demo.entities.Courses;

@Repository
public interface CourseDAO extends JpaRepository<Courses, Integer> {

}
