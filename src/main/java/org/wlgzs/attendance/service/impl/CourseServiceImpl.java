package org.wlgzs.attendance.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.wlgzs.attendance.dao.CourseDao;
import org.wlgzs.attendance.entity.Course;
import org.wlgzs.attendance.entity.Student;
import org.wlgzs.attendance.service.CourseService;
import org.wlgzs.attendance.utils.SpecificationUtil;

import javax.annotation.Resource;

/**
 * @author: zsh
 * @Date:21:34 2018/5/9
 * @Description:
 */
@Service(value = "courseService")
public class CourseServiceImpl implements CourseService {

    @Resource
    CourseDao courseDao;

    @Override
    public void save(Course course) {
        courseDao.save(course);
    }

    @Override
    public void delete(Integer id) {
        courseDao.deleteById(id);
    }

    @Override
    public void deleteMany(Integer[] ids) {
        for (int i = 0;i<ids.length;i++){
            courseDao.deleteById(ids[i]);
        }
    }

    @Override
    public void update(Course course) {
        courseDao.saveAndFlush(course);
    }

    @Override
    public Page<Course> findAll(Pageable pageable) {
        Page<Course> all = courseDao.findAll(pageable);
        return all;
    }

    @Override
    public Page<Course> findByCondition(String condition, Integer pn) {
        Pageable pageable = PageRequest.of(pn,10);

        Specification<Course> spe = new SpecificationUtil<Course>(condition).getSpe("name","room","classess");

        Page<Course> all = courseDao.findAll(spe, pageable);

        return all;
    }

    @Override
    public Course findCourseByTime(String date,String today) {
        Course courseByTime = courseDao.findCourseByTime(date,today);
        return courseByTime;
    }
}
