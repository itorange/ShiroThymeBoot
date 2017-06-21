package com.example.demo;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		System.out.println("密码比较器执行~~~~~~~~~~");
		return  true;
	}
}
