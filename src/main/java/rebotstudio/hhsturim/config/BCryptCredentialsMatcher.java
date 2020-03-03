package rebotstudio.hhsturim.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rebotstudio.hhsturim.common.StringUtils;

public class BCryptCredentialsMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

        // 密码对比
        return bCryptPasswordEncoder.matches(StringUtils.ArrayToString(token.getPassword()),authenticationInfo.getCredentials().toString());
    }
}
