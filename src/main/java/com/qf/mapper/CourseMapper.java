package com.qf.mapper;

import com.qf.pojo.Course;

import java.util.List;

public interface CourseMapper {
    public int addCourse(String cname);

    public int deleteCourse(int cid);

    public int updateCourse(Course course);

    public Course getCourseById(int cid);

    public List<Course> getCourseList();
}
