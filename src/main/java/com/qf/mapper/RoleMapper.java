package com.qf.mapper;

import com.qf.pojo.Role;

import java.util.List;

public interface RoleMapper {
    public int addRole(String rolename);

    public int deleteRole(int rid);

    public int updateRole(Role role);

    public Role getRoleById(int rid);

    public List<Role> getRoleList();
}
