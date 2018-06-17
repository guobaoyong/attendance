package org.wlgzs.attendance.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.wlgzs.attendance.dao.SigninDao;
import org.wlgzs.attendance.entity.Signin;
import org.wlgzs.attendance.service.SigninService;
import org.wlgzs.attendance.utils.SpecificationUtil;

import javax.annotation.Resource;

/**
 * @author: zsh
 * @Date:21:34 2018/5/9
 * @Description:
 */
@Service(value = "signinService")
@Slf4j
public class SigninServiceImpl implements SigninService {

    @Resource
    SigninDao signinDao;

    @Override
    public void save(Signin signin) {
        signinDao.save(signin);
    }

    @Override
    public void delete(Integer id) {
        signinDao.deleteById(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (int i =0;i<ids.length;i++){
            signinDao.deleteById(ids[i]);
        }
    }

    @Override
    public void update(Signin signin) {
        signinDao.saveAndFlush(signin);
    }

    @Override
    public Page<Signin> findAllByCondition(String condition,Integer pn) {
        Pageable pageable = PageRequest.of(pn,10);
        Specification<Signin> spe = new SpecificationUtil<Signin>(condition).getSpe("number", "ip", "name");
        Page<Signin> all = signinDao.findAll(spe, pageable);
        return all;
    }

    @Override
    public Page<Signin> findAll(Pageable pageable) {
        Page<Signin> all = signinDao.findAll(pageable);
        return all;
    }

    @Override
    public Signin findSignin(String start_time, String end_time, String ip) {
        Signin signin = signinDao.findSignin(start_time, end_time, ip);
        return signin;
    }
}
