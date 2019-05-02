package xz.fzu.dao;

import xz.fzu.model.Recruitment;

import java.util.List;

public interface IRecruitmentDao {

    /**
     * @param recruitment
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:13
     * @description 插入一个实例
     */
    public void insertInstance(Recruitment recruitment);

    /**
     * @param recruitmentId
     * @return xz.fzu.model.Recruitment
     * @author Murphy
     * @date 2019/4/27 1:13
     * @description 通过实例id查找数据库实例
     */
    public Recruitment selectInstaceById(long recruitmentId);

    /**
     * @param companyId
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/4/27 1:13
     * @description 根据公司id查找所有的招聘信息
     */
    public List<Recruitment> selectListInstanceByCompanyId(String companyId, int requestPage, int pageSize);

    /**
     * @param keyWord
     * @param requestPage
     * @return java.util.List<xz.fzu.model.Recruitment>
     * @author Murphy
     * @date 2019/4/28 23:54
     * @description 通过keyWord查找招聘信息
     */
    public List<Recruitment> selectInstanceByKeyWord(String keyWord, int requestPage, int pageSize);
    /**
     * @param recruitmentId
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:15
     * @description 删除招聘信息
     */
    public void deleteInstace(long recruitmentId);

    /**
     * @param recruitment
     * @return void
     * @author Murphy
     * @date 2019/4/27 1:15
     * @description 更新信息
     */
    public void updateInstace(Recruitment recruitment);
}
