package xz.fzu.mapper;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import xz.fzu.model.Company;


/**
 * 企业相关的dao
 *
 * @author Murphy
 */
@Repository
public interface CompanyMapper extends Mapper<Company> {

    /**
     * 通过email查找company的id
     *
     * @param email 邮件地址
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/25 18:14
     */
    String selectIdByEmail(String email);

    /**
     * 通过token查看企业的id
     *
     * @param token token
     * @return java.lang.String
     * @author Murphy
     * @date 2019/4/25 19:26
     */
    String selectIdByToken(String token);

    /**
     * 查找company实例
     *
     * @param companyId 企业id
     * @return xz.fzu.model.Company
     * @author Murphy
     * @date 2019/4/25 18:15
     */
    Company selectCompanyById(String companyId);

    /**
     * 通过email查找company实例
     *
     * @param email 邮件地址
     * @return xz.fzu.model.Company
     * @author Murphy
     * @date 2019/4/25 18:15
     */
    Company selectCompanyByEmail(String email);

    /**
     * 修改公司信息，应当传入公司id，以及在修改完状态后应当更新审核状态
     *
     * @param company 企业实例
     * @return void
     * @author Murphy
     * @date 2019/4/25 18:17
     */
    void updateInfo(Company company);

    /**
     * 插入公司实例
     *
     * @param company 企业实例
     * @return void
     * @author Murphy
     * @date 2019/4/25 18:18
     */
//    void insertInstance(Company company);

    /**
     * 删除公司实例
     *
     * @param companyId 企业id
     * @return void
     * @author Murphy
     * @date 2019/4/25 18:19
     */
    void deleteCompany(String companyId);

    /**
     * 验证token的正确性
     *
     * @param token token
     * @return int
     * @author Murphy
     * @date 2019/4/25 19:47
     */
    int verifyToken(String token);


    /**
     * 通过账户密码登录
     *
     * @param email  邮箱地址
     * @param passwd 密码
     * @return int
     * @author Murphy
     * @date 2019/4/25 19:50
     */
    int loginWithPasswd(String email, String passwd);

    /**
     * 更新用户密码
     *
     * @param token     token
     * @param oldPasswd 旧密码
     * @param newPasswd 新密码
     * @return int
     * @author Murphy
     * @date 2019/4/25 20:51
     */
    int updatePasswd(String token, String oldPasswd, String newPasswd);

}
