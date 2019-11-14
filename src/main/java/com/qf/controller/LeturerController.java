package com.qf.controller;

import com.qf.pojo.Employee;
import com.qf.pojo.Score;
import com.qf.pojo.Student;
import com.qf.pojo.Weekly;
import com.qf.service.LecturerService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LeturerController {

    @Autowired
    private LecturerService lecturerService;

    public LecturerService getLecturerService() {
        return lecturerService;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @RequestMapping("index")
    public String index() {

        return "redirect:user";
    }

    @RequestMapping("user")
    public String user(HttpServletRequest request) {
        String ename = "陈帅";
        Employee employee = lecturerService.selectSelf(ename);
        request.setAttribute("employee", employee);
        return "index";
    }

    @RequestMapping("selfInfo")
    public String selfInfo(HttpServletRequest request) {
        String ename = "陈帅";
        Employee employee = lecturerService.selectSelf(ename);
        request.setAttribute("employee", employee);
        return "selfInfo";
    }

    @RequestMapping("loginPage")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("stuList")
    public String stuList(HttpServletRequest request) {
        String ename = "陈帅";
        String sclass = lecturerService.myClass(ename);
        List<Student> stuList = lecturerService.stuList(sclass);
        request.setAttribute("stuList", stuList);
        return "stuList";
    }

    @RequestMapping("forms")
    public String forms(HttpServletRequest request) {
        String ename = "陈帅";
        Employee employee = lecturerService.selectSelf(ename);
        request.setAttribute("employee", employee);
        return "forms";
    }

    @RequestMapping(value = "UserExcelDownloads", method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        String sclass = "1902";
        List<Student> stuList = lecturerService.stuList(sclass);
        String fileName = "学生花名册" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"编号", "姓名", "班级", "照片"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Student student : stuList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(student.getSid());
            row1.createCell(1).setCellValue(student.getSname());
            row1.createCell(2).setCellValue(student.getSclass());
            row1.createCell(3).setCellValue(student.getImg()
            );
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("weeks")
    public String weeks(HttpServletRequest request) {
        String ename = "陈帅";
        Employee employee = lecturerService.selectSelf(ename);
        request.setAttribute("employee", employee);
        return "weeks";
    }

    @RequestMapping("weekly")
    public String weekly(HttpServletRequest request) {
        String ename = "陈帅";
        String sclass = lecturerService.myClass(ename);
        List<Student> stuList = lecturerService.stuList(sclass);
        request.setAttribute("stuList", stuList);
        return "weekly";
    }

    @RequestMapping("selfWeek")
    public String selfWeek(HttpServletRequest request, String sname) {
        String ename = "陈帅";
        Employee employee = lecturerService.selectSelf(ename);
        request.setAttribute("employee", employee);
        request.setAttribute("sname", sname);
        return "selfWeek";
    }


    @RequestMapping("selfWeekly")
    public String selfWeekly(String sname, HttpServletRequest request) {
        String username = sname;
        //System.out.println(username);
        List<Weekly> weekly = lecturerService.selfWeekly(username);
        if (weekly.size() != 0) {
            //System.out.println(weekly);
            request.setAttribute("weekly", weekly);
            return "selfWeekly";
        } else {
            return "redirect:noWeekly";
        }
    }

    @RequestMapping("noWeeks")
    public String noWeeks() {
        return "noWeeks";
    }

    @RequestMapping("noWeekly")
    public String noWeekly() {
        return "noWeekly";
    }

    @RequestMapping("score")
    public String score(int wid, int score) {
//        System.out.println(score);
//        System.out.println(wid);
        Weekly weekly = new Weekly();
        weekly.setWid(wid);
        weekly.setScore(score);
        lecturerService.updateScore(weekly);
        return "redirect:weekly";
    }

    @RequestMapping("manageScore")
    public String manageScore(Model model) {
        String sclass = lecturerService.myClass("陈帅");
        String score1 = lecturerService.selectScore("第一阶段", sclass).toString();
        String score2 = lecturerService.selectScore("第二阶段", sclass).toString();
        String score3 = lecturerService.selectScore("第三阶段", sclass).toString();
        String score4 = lecturerService.selectScore("第四阶段", sclass).toString();
        List<String> list = new ArrayList<String>();
        list.add(score1);
        list.add(score2);
        list.add(score3);
        list.add(score4);
        model.addAttribute("list", list);
        return "scoreCharts";
    }

    @RequestMapping("scoreManage")
    public String scoreManage(HttpServletRequest request) {
        String ename = "陈帅";
        Employee employee = lecturerService.selectSelf(ename);
        request.setAttribute("employee", employee);
        return "manageScore";
    }

    @RequestMapping("studentScore")
    public String studentScore(Model model, String sname, HttpServletRequest request, HttpSession session) {
        //System.out.println(sname);
        List<Score> list1 = lecturerService.selectStudentScore(sname);
        if (list1.size() == 0) {
            String ename = "陈帅";
            Employee employee = lecturerService.selectSelf(ename);
            request.setAttribute("employee", employee);
            return "redirect:noSuchStudent";
        } else {
            try {
                String score1 = lecturerService.studentScore("第一阶段", sname).toString();
                String score2 = lecturerService.studentScore("第二阶段", sname).toString();
                String score3 = lecturerService.studentScore("第三阶段", sname).toString();
                String score4 = lecturerService.studentScore("第四阶段", sname).toString();
                List<String> list = new ArrayList<String>();
                list.add(score1);
                list.add(score2);
                list.add(score3);
                list.add(score4);
                model.addAttribute("list", list);
                return "selfScore";
            } catch (Exception e) {
                String ename = "陈帅";
                Employee employee = lecturerService.selectSelf(ename);
                request.setAttribute("employee", employee);
                request.setAttribute("sname", sname);
                session = request.getSession();
                session.setAttribute("sname", sname);
                //System.out.println(session.getAttribute(sname));
                return "redirect:emptyScore";
            }
        }
    }


    @RequestMapping("emptyScore")
    public String emptyScore(HttpServletRequest request, String sname, HttpSession session) {
        session = request.getSession();
        sname = (String) session.getAttribute("sname");
        request.setAttribute("sname", sname);
        return "emptyScore";
    }

    @RequestMapping("noSuchStudent")
    public String noSuchStudent() {
        return "noSuchStudent";
    }

    @RequestMapping("selectLostScore")
    public String selectLostScore(String sname, HttpServletRequest request) {
        List<Score> list = lecturerService.selectStudentScore(sname);
        request.setAttribute("list", list);
        return "lostScore";
    }

    @RequestMapping("addScore1")
    public String addScore1() {
        return "addScore";
    }

    @RequestMapping("addScore")
    public String addScore(String sname, String sclass, String stage, int score) {
        System.out.println(sname);
        System.out.println(sclass);
        System.out.println(stage);
        System.out.println(score);
        Score score1 = new Score();
        score1.setSname(sname);
        score1.setSclass(sclass);
        score1.setStage(stage);
        score1.setScore(score);
        int i = lecturerService.addScore(score1);
        if (i != 0) {
            return "addSuccess";
        } else {
            return "addFail";
        }
    }
}