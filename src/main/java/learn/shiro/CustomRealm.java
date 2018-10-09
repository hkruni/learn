package learn.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hukai on 2018/10/3.
 */
public class CustomRealm extends AuthorizingRealm {

    Map<String ,String > map = new HashMap<String ,String>(){
        {
            put("root","debd83bca36c128b8ea06d94ef54d551");
        }
    };


    //认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String )principalCollection.getPrimaryPrincipal();

        Set<String> roles = getRolesByUsername(username);
        Set<String > permissions = getPermissionsByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);


        return info;
    }

    private Set<String> getPermissionsByUsername(String username) {
        Set<String> sets = new HashSet<String>(){
            {
                add("user:delete");
                add("user:add");
            }
        };
        return sets;
    }

    private Set<String> getRolesByUsername(String username) {
        Set<String> sets = new HashSet<String>(){
            {
                add("admin");
                add("user");
            }
        };
        return sets;

    }


    //授权
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String) authenticationToken.getPrincipal();
        String password = getPasswordByUserName(username);
        if (password == null) {
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("root",password,"customRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("mark"));
        return authenticationInfo;



    }

    private String getPasswordByUserName(String username) {
        return  map.get(username);

    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("1234567","mark");
        System.out.println(md5Hash.toString());
    }
}
