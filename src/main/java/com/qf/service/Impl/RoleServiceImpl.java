package com.qf.service.Impl;

import com.qf.mapper.RoleMapper;
import com.qf.pojo.Role;
import com.qf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public RoleMapper getRoleMapper() {
        return roleMapper;
    }

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public int addRole(String rolename) {
        return roleMapper.addRole(rolename);
    }

    @Override
    public int deleteRole(int rid) {
        return roleMapper.deleteRole(rid);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public Role getRoleById(int rid) {
        return roleMapper.getRoleById(rid);
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }
}
