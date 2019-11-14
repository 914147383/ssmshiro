package com.qf.controller;

import com.github.pagehelper.PageInfo;
import com.qf.mapper.LeturerMapper;
import com.qf.mapper.StudentMapper;
import com.qf.pojo.*;

import com.qf.service.LecturerService;
import com.qf.service.PrincipalService;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUpload;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qf.service.StudentService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PrincipalService principalService;

    @RequestMapping("tablePage")
    public String tablePage(HttpSession session){
        session.setAttribute("username","wen");

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

        //System.out.println("userByUname:"+userByUname);

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

    //学生查看自己的请假申请列表
    @RequestMapping("selHolidayBySel")
    public String selHoliday(HttpSession session, Model model,
                             @RequestParam(defaultValue = "1") int pageNo,
                             @RequestParam(defaultValue = "6") int pageSize){
        String username = (String) session.getAttribute("username");
        PageInfo<Holiday> holidayBySname = studentService.getHolidayBySname(username, pageNo, pageSize);


        model.addAttribute("holidayBySname",holidayBySname);

      //  System.out.println("holidayBySname:"+holidayBySname.toString());

        return "selHolidayBySel";
    }

    //老师请假申请
    @RequestMapping("addVacate")
    public String addVacate(Model model){
        List<User> principal = principalService.getUserByRoleName("principal");
        model.addAttribute("principal",principal);
        return "addVacate";
    }

    //老师保存请假申请
    @RequestMapping("savVacate")
    public String savVacate(Vacate vacate,String bName){
        System.out.println("bName:"+bName);

        int i = studentService.addVacate(vacate,bName);

        //System.out.println("bName:"+bName);

        return "login";
    }

    //学生信息批量   导入,导出   界面
    @RequestMapping("InAndOut")
    public String addStu(HttpSession session,Model model){
        String username = (String) session.getAttribute("username");
        List<Weekly> weekly = studentMapper.getWeekly(username, "", "", "");

        model.addAttribute("weekly",weekly);
        return "addStu";
    }

    //学生信息批量导入  数据库保存
    @RequestMapping(value = "savStu", method = RequestMethod.POST)
    public String savStu(MultipartFile file,HttpServletRequest request){
        String contentType = file.getContentType();

        String fileName = file.getOriginalFilename();

        if (file.isEmpty()) {
            return "文件为空！";
        }
        try {
            //根据路径获取这个操作excel的实例
            HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());            //根据页面index 获取sheet页

            HSSFSheet sheet = wb.getSheetAt(0);
            //实体类集合
            List<Weekly> importDatas = new ArrayList<Weekly>();
            HSSFRow row = null;
            //循环sesheet页中数据从第二行开始，第一行是标题
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                //获取每一行数据
                row = sheet.getRow(i);

                Weekly w = new Weekly();

                w.setWid(Integer.valueOf((int) row.getCell(0).getNumericCellValue()));
                w.setUsername(row.getCell(1).getStringCellValue());
                w.setTitle(row.getCell(2).getStringCellValue());
                w.setReport(row.getCell(3).getStringCellValue());
                w.setScore(Integer.valueOf((int) row.getCell(4).getNumericCellValue()));
                //SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                //data.setCreateDate(df.parse(df.format(HSSFDateUtil.getJavaDate(row.getCell(2).getNumericCellValue()))));

                //data.setAge(Integer.valueOf((int) row.getCell(3).getNumericCellValue()));
                importDatas.add(w);
            }
            //循环展示导入的数据，实际应用中应该校验并存入数据库

            for (Weekly imdata : importDatas) {
                //SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                System.out.println("ID:"+imdata.getWid()+" name:"+imdata.getUsername());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "importSucPage";
    }



    //导出方法保存
    @RequestMapping(value = "importSucPage", method = RequestMethod.GET)
    public void outStu(HttpServletResponse response,HttpSession session){
        String username = (String) session.getAttribute("username");

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<Weekly> weekly = studentMapper.getWeekly(username, "", "", "");


        String fileName = "学生周报" + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"编号", "姓名", "标题","时间", "内容","分数"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Weekly weekly1 : weekly) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(weekly1.getWid());
            row1.createCell(1).setCellValue(weekly1.getUsername());
            row1.createCell(2).setCellValue(weekly1.getTitle());
            row1.createCell(3).setCellValue(weekly1.getTime());
            row1.createCell(4).setCellValue(weekly1.getReport());
            row1.createCell(5).setCellValue(weekly1.getScore());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        try {

            response.flushBuffer();
            workbook.write(response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

       // return "importSucPage";
    }



}
