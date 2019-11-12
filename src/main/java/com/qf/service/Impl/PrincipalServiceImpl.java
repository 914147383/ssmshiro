package com.qf.service.Impl;

import com.qf.mapper.PrincipalMapper;
import com.qf.mapper.StudentMapper;
import com.qf.pojo.User;
import com.qf.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrincipalServiceImpl implements PrincipalService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PrincipalMapper principalMapper;

    @Override
    public User getUserByUname(String username) {
        return studentMapper.getUserByUname(username);
    }

    @Override
    public int updUserByUname(User user) {
        return principalMapper.updUserByUname(user);
    }

    @Override
    public int updCheckedByVid(int vid) {
        return principalMapper.updCheckedByVid(vid);
    }

    @Override
    public int updCheckedByHid(int hid) {
        return principalMapper.updCheckedByHid(hid);
    }
}
