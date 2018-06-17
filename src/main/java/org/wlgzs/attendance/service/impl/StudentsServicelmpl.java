package org.wlgzs.attendance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wlgzs.attendance.dao.StudentDao;
import org.wlgzs.attendance.entity.Student;
import org.wlgzs.attendance.service.StudentService;
import org.wlgzs.attendance.utils.ExcelUtil;
import org.wlgzs.attendance.utils.SpecificationUtil;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zsh
 * @Date:20:05 2018/5/5
 * @Description:
 */
@Service(value = "studentService")
@Slf4j
public class StudentsServicelmpl implements StudentService {

    @Resource
    private StudentDao studentDao;


    @Override
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    public void importExcelInfo(InputStream in, MultipartFile file) {
        List<List<Object>> listob;
        try {
            listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
            List<Student> students = new ArrayList<>();
            //遍历listob数据，把数据放到List中
            for (int i = 0; i < listob.size(); i++) {
                List<Object> ob = listob.get(i);
                Student student = new Student();
                //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载
                student.setClasses(String.valueOf(ob.get(0)));
                student.setName(String.valueOf(ob.get(1)));
                student.setNumber(String.valueOf(ob.get(2)));
                Student byNumber = studentDao.findByNumber(String.valueOf(ob.get(2)));
                if (byNumber == null){
                    students.add(student);
                }
            }
            studentDao.saveAll(students);
            }catch (Exception e){
            e.printStackTrace();
        }


    }


    /**
     * @author zsh
     * @date 2018/5/6 18:00
     * @param: [id]
     * @return: void
     * @Description: 通过id删除单个学生
     */
    @Override
    public void deleteOne(Integer id) {
        studentDao.deleteById(id);
    }

    /**
     * @author zsh
     * @date 2018/5/6 18:17
     * @param: [ids]
     * @return: void
     * @Description: 通过ids删除多个学生
     */
    @Override
    public void deleteMany(Integer[] ids) {
        for (int i = 0;i<ids.length;i++){
            studentDao.deleteById(ids[i]);
        }
    }

    /**
     * @author zsh
     * @date 2018/5/6 18:23
     * @param: [student]
     * @return: void
     * @Description: 更新学生信息
     */
    @Override
    public void update(Student student) {
        studentDao.saveAndFlush(student);
    }

    /**
     * @author zsh
     * @date 2018/5/6 18:57
     * @param: [pageable]
     * @return: org.springframework.data.domain.Page<org.wlgzs.attendance.entity.Student>
     * @Description: 分页查询所有学生，默认10/页
     */
    @Override
    public Page<Student> findAll(Pageable pageable) {
        Page<Student> page = studentDao.findAll(pageable);
        return page;
    }

    /**
     * @author zsh
     * @date 2018/5/6 20:24
     * @param: [condition]
     * @return: org.springframework.data.domain.Page<org.wlgzs.attendance.entity.Student>
     * @Description: 通过姓名，班级，学号模糊查询
     */
    @Override
    public Page<Student> findAllByNameLikeOrClassesLikeOrNumberLike(String condition,Integer pn) {
        Pageable pageable = PageRequest.of(pn,10);

        Specification<Student> spe = new SpecificationUtil<Student>(condition).getSpe("name", "number", "classes");

        Page<Student> all = studentDao.findAll(spe, pageable);

        return all;
    }

    /**
     * @author zsh
     * @date 2018/5/13 10:24
     * @param: [number]
     * @return: org.wlgzs.attendance.entity.Student
     * @Description: 通过学号查询学生信息，查重用
     */
    @Override
    public Student findByNumber(String number) {
        Student byNumber = studentDao.findByNumber(number);
        return byNumber;
    }

    @Override
    public List<Student> find() {
        List<Student> all = studentDao.findAll();
        return all;
    }
}
