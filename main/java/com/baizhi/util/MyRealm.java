package com.baizhi.util;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Authority;
import com.baizhi.entity.Role;
import com.baizhi.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.List;

public class MyRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入加权");
        String username= (String) principalCollection.getPrimaryPrincipal();
        System.out.println(username+"-----");
        AdminDao adminDao = (AdminDao) SpringContextUtil.getBean(AdminDao.class);
        Admin admin = adminDao.selectBy(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        List<Role> roles = admin.getRoles();
        for (Role role : roles) {
            simpleAuthorizationInfo.addRole(role.getRoleName());
            List<Authority> authorities = role.getAuthorities();
            for (Authority authority : authorities) {
                simpleAuthorizationInfo.addStringPermission(authority.getAuthorityName());
            }
        }
        System.out.println(simpleAuthorizationInfo);
        return simpleAuthorizationInfo;
    }
    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username= (String) authenticationToken.getPrincipal();
        System.out.println(username+"+++++++");
        AdminDao adminDao = (AdminDao) SpringContextUtil.getBean(AdminDao.class);
        Admin admin = adminDao.selectBy(username);
        System.out.println(admin+"========");
        if(admin==null){
            System.out.println("账户不存在");
            return null;
        }else{
            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(username,admin.getPassword(),ByteSource.Util.bytes(admin.getSalt()),this.getName());
            System.out.println("验证通过");
            System.out.println(simpleAuthenticationInfo+"]]]]]]]");
            return simpleAuthenticationInfo;
        }
    }
}
