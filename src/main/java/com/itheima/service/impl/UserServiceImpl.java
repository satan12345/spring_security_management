package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private RoleService roleService;
    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userDao.findByName(username);
        if (sysUser==null) {
            return null;
        }
//        SysRole sysRole=new SysRole();
//        sysRole.setRoleName("ROLE_USER");
//        sysUser.getRoles().add(sysRole);
//        User user=new User(sysUser.getUsername(),"{noop}"+sysUser.getPassword(),sysUser.getRoles());
        User user=new User(sysUser.getUsername(),sysUser.getPassword(),sysUser.getRoles());
        return user;
    }

    @Override
    public void save(SysUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public Map<String, Object> toAddRolePage(Integer id) {
        List<SysRole> allRoles = roleService.findAll();
        List<Integer> myRoles = userDao.findRolesByUid(id);
        Map<String, Object> map = new HashMap<>();
        map.put("allRoles", allRoles);
        map.put("myRoles", myRoles);
        return map;
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] ids) {
        userDao.removeRoles(userId);
        if (Objects.isNull(ids)) {
            return;
        }
        for (Integer rid : ids) {
            userDao.addRoles(userId, rid);
        }
    }


}
