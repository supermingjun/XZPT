package xz.fzu.service.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Murphy
 * @date 2019/5/223:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mvc.xml", "/spring-mybatis.xml"})
@Transactional
public class UserServiceImplTest {

//    @InjectMocks
//    private UserServiceImpl iUserService;
//    @Mock
//    private UserMapper userMapper;
//    @Test
//    public void register() throws AccountUsedException {
//        User user = new User();
//        user.setPasswd("123456");
//        user.setUserName("待就业");
//        user.setEmail("djylrz@qq.com");
//        user.setSex(1);
//        iUserService.register(user);
//        when(iUserService.selectByEmail("djylrz@qq.com")).thenReturn(null);
//
//
//
//        System.out.println(user.getUserId());
////        assertTrue(user.getUserId());
//    }

//    @Test
//    public void selectByEmail() {
//    }
//
//    @Test
//    public void selectByUserId() {
//    }
//
//    @Test
//    public void verifyUser() {
//    }
//
//    @Test
//    public void verifyToken() {
//    }
//
//    @Test
//    public void updateToken() {
//    }
//
//    @Test
//    public void selectUserByToken() {
//    }
//
//    @Test
//    public void updateInfo() {
//    }
//
//    @Test
//    public void updatePasswd() {
//    }
//
//    @Test
//    public void resetPasswd() {
//    }
}