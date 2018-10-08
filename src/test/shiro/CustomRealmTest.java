package src.test.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import learn.shiro.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created by hukai on 2018/10/3.
 */
public class CustomRealmTest {



    @Test
    public void testAuthentication() {

        CustomRealm customRealm = new CustomRealm();


        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");//加密算法
        matcher.setHashIterations(1);//加密次数
        customRealm.setCredentialsMatcher(matcher);



        //2.主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token  = new UsernamePasswordToken("root","1234567");
        subject.login(token);

        System.out.println("isAuthenticated:" + subject.isAuthenticated());
//        subject.checkRole("admin");
//        subject.checkPermission("user:add");
//        subject.logout();
    }
}
