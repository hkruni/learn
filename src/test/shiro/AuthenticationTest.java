package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by hukai on 2018/10/2.
 */
public class AuthenticationTest {


    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();


    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("root","123456","admin","user");
    }

    @Test
    public void testAuthentication() {

        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2.主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token  = new UsernamePasswordToken("root","123456");
        subject.login(token);

        System.out.println("isAuthenticated:" + subject.isAuthenticated());
        subject.checkRole("admin2");//检查是否是管理员角色

        subject.logout();
    }
}
