package org.wlgzs.attendance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.wlgzs.attendance.entity.Student;


/**
 * @author: zsh
 * @Date:19:51 2018/5/5
 * @Description:
 */
public interface StudentDao extends JpaRepository<Student,Integer>
        , PagingAndSortingRepository<Student,Integer>
        ,JpaSpecificationExecutor<Student> {

    Student findByNumber(String number);

}
