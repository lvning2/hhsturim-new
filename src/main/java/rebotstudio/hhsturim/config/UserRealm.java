package rebotstudio.hhsturim.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;
import rebotstudio.hhsturim.entity.User;

import rebotstudio.hhsturim.service.UserService;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入了授权方法..." + principalCollection.getPrimaryPrincipal());

        return null;
    }

    /**
     * 认证方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //System.out.println("进入了认证方法...");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userService.getUserByUsername(token.getUsername());
        if (user == null) {        // 用户名不存在
            return null;       // shrio 底层会抛出 UnKnowAccountExcption
        }

        return new SimpleAuthenticationInfo("", user.getPassword(), "");

    }

}