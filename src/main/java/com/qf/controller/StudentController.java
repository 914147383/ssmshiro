package com.qf.controller;

import com.github.pagehelper.PageInfo;
import com.qf.pojo.Holiday;
import com.qf.pojo.User;
import com.qf.pojo.Vacate;
import com.qf.pojo.Weekly;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUpload;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qf.service.StudentService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("tablePage")
    public String tablePage(HttpSession session){
        session.setAttribute("username","b");

        String username = (String) session.getAttribute("username");

        User userByUname = studentService.getUserByUname(username);

        session.setAttribute("userByUname",userByUname);

        return "tables";
    }

    //个人资料管理   界面
    @RequestMapping("updStuPage")
    public String updStuPage(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");

        User userByUname = studentService.getUserByUname(username);

        System.out.println("userByUname:"+userByUname);

        model.addAttribute("userByUname",userByUname);
        return "updStuPage";
    }

    @RequestMapping("loginP")
    public String loginP(){
        return "login";
    }

    //个人资料管理   保存
    @PostMapping("savStuPage")
    public String savStuPage(String username,String password,HttpSession session,
                             HttpServletRequest request,
                             MultipartFile file){
        User user = new User();
        user.setUsername(username);

        String md5Hash = new Md5Hash(password).toString();

        user.setPassword(md5Hash);

        String filename=file.getOriginalFilename();
        user.setImg(filename);

        String path=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\"+filename;

       // System.out.println("path:"+path);

        File file1=new File(path);
        if(!filename.equals("")){
            try {
                file.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int i = studentService.updUserAndStuByUname(user);
       // System.out.println("i:"+i);

        return "redirect:updStuPage";
    }

    //查询周报
    @RequestMapping("getWeekly")
    public String selWeeklyPage(HttpSession session, Model model,
                                @RequestParam(defaultValue = "") String title,
                                @RequestParam(defaultValue = "") String time,
                                @RequestParam(defaultValue = "") String score,
                                @RequestParam(defaultValue = "1") int pageNo,
                                @RequestParam(defaultValue = "6") int pageSize){
         String username = (String) session.getAttribute("username");

        PageInfo<Weekly> weekly = studentService.getWeekly(username,time,score, title, pageNo, pageSize);

        //System.out.println("weekly:"+weekly);
        model.addAttribute("weekly",weekly);

        return "selWeeklyPage";
    }

    //模糊搜搜周报
    @RequestMapping("selWeekByLike")
    public String selWeekByLike(){
return "";
    }

    //删除周报
    @RequestMapping("delWeekly")
    public String delWeekly(HttpSession session,int wid){
         String username = (String) session.getAttribute("username");

        int i = studentService.delWeekly(username, wid);
        return "redirect:getWeekly";
    }

    //新增周报
    @RequestMapping("addWeeklyPage")
    public String addWeeklyPage(){
        return "addWeeklyPage";
    }

    //保存新增的周报
    @RequestMapping("savWeekly")
    public String savWeekly(Weekly weekly){
        int i = studentService.addWeekly(weekly);

        return "redirect:getWeekly";
    }

    //学生请假申请
    @RequestMapping("addHoliday")
    public String addHoliday(){
        return "addHolidayPage";
    }

    //学生保存请假申请
    @RequestMapping("savHoliday")
    public String savHoliday(Holiday holiday){
        int i = studentService.addHoliday(holiday);

        return "login";
    }

    //老师请假申请
    @RequestMapping("addVacate")
    public String addVacate(){
        return "addVacate";
    }

    //老师保存请假申请
    @RequestMapping("savVacate")
    public String savVacate(Vacate vacate){
        int i = studentService.addVacate(vacate);

        return "login";
    }

    //学生信息批量导入界面
    @RequestMapping("addStu")
    public String addStu(){
        return "addStu";
    }

    //学生信息批量导入保存
    @RequestMapping("savStu")
    public String savStu(){
        return "redirect:addStu";
    }
}
