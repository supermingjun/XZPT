package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IUserDao;
import xz.fzu.exception.AccountUsedException;
import xz.fzu.exception.PasswordErrorException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.User;
import xz.fzu.service.IUserService;
import xz.fzu.util.Constants;
import xz.fzu.util.Sha;
import xz.fzu.util.TokenUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Murphy
 * @date 2019/4/19 23:14
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    IUserDao iUserDao;

    /**
     * @param user 用户实例
     * @return void
     * @author Murphy
     * @date 2019/4/23 0:10
     * @description 注册, 返回token
     */
    @Override
    public String register(User user) throws AccountUsedException {

        if (selectByEmail(user.getEmail()) != null) {
            throw new AccountUsedException();
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        user.setUserId(uuid);
        user.setPasswd(Sha.encrypt(user.getPasswd()));
        String token = TokenUtil.createToken(user.getUserId(), user.getPasswd());
        user.setToken(token);
        if (user.getHeadUrl() == null) {
            user.setHeadUrl(Constants.DEFAULT_PNG);
        }
        iUserDao.insert(user);

        return token;
    }

    /**
     * @param email 邮件地址
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/20 15:14
     * @description 根据用户email查找用户
     */
    @Override
    public User selectByEmail(String email) {
        return iUserDao.selectByEmail(email);
    }

    @Override
    public User selectByUserId(String userId) {
        return iUserDao.selectByUserId(userId);
    }

    /**
     * @param user 用户实例
     * @return int
     * @author Murphy
     * @date 2019/4/20 21:05
     * @description 验证用户名和密码, 并且返回token
     */
    @Override
    public String verifyUser(User user) throws PasswordErrorException {

        // 密码加密处理
        user.setPasswd(Sha.encrypt(user.getPasswd()));
        if (iUserDao.vertifyUser(user) != 1) {
            throw new PasswordErrorException();
        }
        // 因为传入的user只有email和passwd
        user = selectByEmail(user.getEmail());
        String token = TokenUtil.createToken(user.getUserId(), user.getPasswd());
        // 更新Token
        updateToken(token, user.getUserId());

        return token;
    }

    /**
     * @param token token
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/24 14:04
     * @description 验证token是否正确和过期
     */
    @Override
    public String verifyToken(String token) throws TokenExpiredException {

        TokenUtil.verify(token);
        String userId = iUserDao.selectUserIdByToken(token);
        if (userId == null) {
            throw new TokenExpiredException();
        }

        return userId;
    }

    /**
     * @param token  token
     * @param userId 用户id
     * @author Murphy
     * @date 2019/4/24 14:04
     * @description 更新token
     */
    @Override
    public void updateToken(String token, String userId) {
        iUserDao.updateToken(token, userId);
    }

    /**
     * @param token token
     * @return xz.fzu.model.User
     * @author Murphy
     * @date 2019/4/24 16:51
     * @description 通过token查找用户
     */
    @Override
    public User selectUserByToken(String token) throws TokenExpiredException {

        String userId = verifyToken(token);
        User user = iUserDao.selectByUserId(userId);
        user.setPasswd(null);
        user.setToken(null);

        return user;
    }

    /**
     * @param user  用户实例
     * @param token token
     * @return void
     * @author Murphy
     * @date 2019/4/24 16:50
     * @description 更新用户信息（禁止更新密码）
     */
    @Override
    public void updateInfo(User user, String token) throws TokenExpiredException {

        String userId = verifyToken(token);
        user.setUserId(userId);
        user.setPasswd(null);

        iUserDao.updateInfo(user);
    }

    /**
     * @param token     token
     * @param oldPasswd 旧密码
     * @param newPasswd 新密码
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/24 15:35
     * @description 更改密码
     */
    @Override
    public String updatePasswd(String token, String oldPasswd, String newPasswd) throws PasswordErrorException, TokenExpiredException {

        // 对密码进行加密处理
        oldPasswd = Sha.encrypt(oldPasswd);
        newPasswd = Sha.encrypt(newPasswd);

        String userId = verifyToken(token);

        User user = iUserDao.selectByUserId(userId);
        if (!oldPasswd.equals(user.getPasswd())) {
            throw new PasswordErrorException();
        }
        iUserDao.updatePasswd(userId, newPasswd);
        String newToken = TokenUtil.createToken(userId, newPasswd);
        iUserDao.updateToken(newToken, userId);

        return newToken;
    }

    /**
     * @param email  邮件地址
     * @param passwd 密码
     * @return void
     * @author Murphy
     * @date 2019/4/25 17:28
     * @description 重置用户密码
     */
    @Override
    public void resetPasswd(String email, String passwd) {

        String userId = iUserDao.selectByEmail(email).getUserId();
        User user = new User();
        user.setUserId(userId);
        user.setPasswd(Sha.encrypt(passwd));

        iUserDao.updateInfo(user);
    }

    @Override
    public List<String> selectUserByIndustryLabel(long industryLabel) {
        List<User> userList = iUserDao.selectAll();
        List<String> res = new ArrayList<>(userList.size() / 2);
        for (User user : userList) {
            if (user.getIndustryLabel() != null && user.getIndustryLabel() == industryLabel) {
                res.add(user.getUserId());
            }
        }
        return res;
    }
}
