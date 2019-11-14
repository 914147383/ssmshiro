package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Course;
import com.qf.pojo.Student;
import com.qf.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CourseController {
@Autowired
    private CourseService courseService;

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("course")
    public String course(@RequestParam(defaultValue = "1") int pageNum,HttpServletRequest request){
        PageHelper.startPage(pageNum, 5);
        List<Course> courseList = courseService.getCourseList();
        PageInfo<Course> pageInfo = new PageInfo<Course>(courseList);
        request.setAttribute("pageInfo",pageInfo);
        return "coursetable";
    }


    @RequestMapping("addPage")
    public String addPage(){
        return "addcourse";
    }

    @RequestMapping("addCourse")
    public String addCourse(String cname){
        int i = courseService.addCourse(cname);
        if (i>0){
            return "redirect:course";
        }
        return "addcourse";
    }

    @RequestMapping("updatePage")
    public String updatePage(HttpServletRequest request,int cid){
        Course course = courseService.getCourseById(cid);
        request.setAttribute("course",course);
        return "updatecourse";
    }

    @RequestMapping("updateCourse")
    public String updateCourse(int cid,String cname){
        Course course = new Course(cid, cname);
        int i = courseService.updateCourse(course);
        if (i>0){
            return "redirect:course";
        }
        return "updatePage";
    }

    @RequestMapping("deleteCourse")
    public String deleteCouse(int cid){
        int i = courseService.deleteCourse(cid);
        if (i>0){
            return "redirect:course";
        }
        return "fail";
    }

}

