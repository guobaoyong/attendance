package org.wlgzs.attendance.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.attendance.entity.Result;
import org.wlgzs.attendance.entity.Student;
import org.wlgzs.attendance.service.StudentService;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * @author: zsh
 * @Date:14:37 2018/5/6
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {

    @Resource
    StudentService studentService;


    /**
     * @param  student
     * @return  modelAndView
     * @author zsh
     * @date 2018/5/6 17:46
     * @Description: 单个增加学生
     */
    @RequestMapping(value = "/addOne",method = RequestMethod.POST)
    public Result addOne(Student student){
        Student byNumber = studentService.findByNumber(student.getNumber());
        Result result = new Result();
        if (byNumber == null){
            studentService.save(student);
            result.setMsg("添加学生成功");
            result.setCode(200);
            return result;
        }else {
            result.setMsg("学生已经存在");
            result.setCode(500);
            return result;
        }
    }


    /**
     * @return ModelAndView
     * @author zsh
     * @date 2018/5/6 17:46
     * @Description: 通过excel批量录入学生信息
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ModelAndView upload(HttpServletRequest request) throws Exception {
        //获取上传的文件
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        MultipartFile file = multipart.getFile("upfile");
        InputStream in = file.getInputStream();
        //数据导入
        studentService.importExcelInfo(in,file);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jump");
        return modelAndView;
    }

    /**
     * @author zsh
     * @date 2018/5/6 18:01
     * @param: [id]
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description: 通过id删除单个学生
     */
    @RequestMapping(value = "/deleteOne",method = RequestMethod.GET)
    public Result deleteOne(Integer id){
        Result result = new Result();
        studentService.deleteOne(id);
        result.setCode(200);
        result.setMsg("删除成功");
        return result;
    }


    /**
     * @author zsh
     * @date 2018/5/6 18:10
     * @param: [ids]
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description: 通过ids删除多个学生
     */
    @RequestMapping(value = "/deleteMany",method = RequestMethod.POST)
    public Result deleteMany(String ids){
        Result result = new Result();
        String[] id = ids.split(",");
        Integer[] ids_int = new Integer[id.length];

        for (int i = 0;i<id.length;i++){
            ids_int[i] = Integer.parseInt(id[i]);
        }
        result.setMsg("删除成功");
        result.setCode(200);
        studentService.deleteMany(ids_int);
        return result;
    }

    /**
     * @author zsh
     * @date 2018/5/6 19:09
     * @param: [student] 
     * @return: org.springframework.web.servlet.ModelAndView 
     * @Description: 更新学生信息
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(Student student){
        Result result = new Result();
        studentService.update(student);
        result.setCode(200);
        result.setMsg("修改成功!");
        return result;
    }

    /**
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description: 分页查询、条件查询二合一
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ModelAndView findAll(@RequestParam(value = "pn", defaultValue = "0")int pn
            ,String condition){
        log.info("调用查询方法");
        log.info(pn+"---"+condition);
        ModelAndView modelAndView = new ModelAndView();
        Pageable pageable = PageRequest.of(pn,30);
        modelAndView.setViewName("listStudent");
        if (condition == null){
            log.info("无条件查询");
            Page<Student> page = studentService.findAll(pageable);
            modelAndView.addObject("page",page);
            modelAndView.addObject("condition",null);
        }else{
            log.info("有条件查询");
            Page<Student> like = studentService.findAllByNameLikeOrClassesLikeOrNumberLike(condition,pn);
            modelAndView.addObject("page",like);
            modelAndView.addObject("condition",condition);
        }
        return modelAndView;
    }
}
