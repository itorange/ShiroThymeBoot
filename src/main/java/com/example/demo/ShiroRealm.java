package com.example.demo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * Created by yangcheng on 2017/6/15.
 */
@Component("shiroRealm")
public class ShiroRealm extends AuthorizingRealm {
    Logger logger = Logger.getLogger("ShiroRealm.Class");

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("授权被调用~~~~~~~~~~~~~~~~~~~");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("a");
        info.addStringPermission("b");
       // info.addStringPermission("c");
        logger.info("授权操作开始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        String password = new String((char[])token.getCredentials());


        logger.info("认证被调用~~~~~~~~~~~~~~~~~~~");
        User user = new User();
        user.setUsername(username);
        String encryptedPwd = null;
        try {
            encryptedPwd = HexUtil.getEncryptedPwd(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.setPassword(encryptedPwd);
        //此处要求密码必须为加过密的!!!!!!!!!
        //getName()是返回唯一的realm名字
        AuthenticationInfo info = new SimpleAuthenticationInfo(user,password,getName());
        return info;
    }
   /* @PostConstruct
    public void initCredentialsMatcher() {
        //该句作用是重写shiro的密码验证，让shiro用我自己的验证
        setCredentialsMatcher(new CustomCredentialsMatcher());

    }*/
}
