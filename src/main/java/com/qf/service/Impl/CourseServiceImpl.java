package com.qf.service.Impl;

import com.qf.mapper.CourseMapper;
import com.qf.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qf.service.CourseService;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    public CourseMapper getCourseMapper() {
        return courseMapper;
    }

    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    @Override
    public int deleteCourse(int cid) {
        return courseMapper.deleteCourse(cid);
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);

    }

    @Override
    public Course getCourseById(int cid) {
        return courseMapper.getCourseById(cid);
    }

    @Override
    public List<Course> getCourseList() {
        return courseMapper.getCourseList();
    }
}
