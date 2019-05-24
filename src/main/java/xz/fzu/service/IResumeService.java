package xz.fzu.service;

import xz.fzu.exception.EvilIntentions;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.Resume;
import xz.fzu.vo.PageData;

import java.util.List;

/**
 * 简历相关的Service接口
 *
 * @author Murphy
 */
public interface IResumeService {

    /**
     * 插入一个实例
     *
     * @param userId 用户id
     * @param resume 简历实例
     * @return void
     * @throws TokenExpiredException token过期
     * @throws EvilIntentions        恶意操作
     * @author Murphy
     * @date 2019/4/30 0:14
     */
    void insertResume(String userId, Resume resume) throws TokenExpiredException, EvilIntentions;

    /**
     * 更新简历
     *
     * @param userId 用户id
     * @param resume 简历实例
     * @return xz.fzu.model.Resume
     * @throws TokenExpiredException token过期
     * @throws EvilIntentions        恶意操作
     * @author Murphy
     * @date 2019/4/30 0:14
     */
    void updateResume(String userId, Resume resume) throws EvilIntentions, TokenExpiredException;

    /**
     * 查找当前用户所有的简历
     *
     * @param userId      用户id
     * @param requestPage 请求页
     * @return java.util.List<xz.fzu.model.Resume>
     * @throws InstanceNotExistException 未找到实例
     * @author Murphy
     * @date 2019/4/30 0:15
     */

    List<Resume> getListResume(String userId, PageData requestPage) throws InstanceNotExistException;

    /**
     * 删除简历
     *
     * @param resumeId 简历id
     * @return int
     * @throws InstanceNotExistException 未找到实例异常
     * @author Murphy
     * @date 2019/4/30 0:16
     */
    int deleteResume(int resumeId) throws InstanceNotExistException;

    /**
     * 获得指定简历实例
     *
     * @param userId   用户的id
     * @param resumeId 简历id
     * @return xz.fzu.model.Resume
     * @throws InstanceNotExistException 未找到实例异常
     * @author Murphy
     * @date 2019/4/30 0:17
     */
    Resume getResume(String userId, Long resumeId) throws InstanceNotExistException;

    /**
     * copy简历，并返回主键
     *
     * @param resumeId 简历ID
     * @return int
     * @author Murphy
     * @date 2019/5/2 14:34
     */
    void copyResume(Long resumeId);

    /**
     * 获得第一个简历并返回
     *
     * @param userId 用户id
     * @return xz.fzu.model.Resume
     * @author Murphy
     * @date 2019/5/23 20:39
     */
    Resume getFirstResume(String userId) throws InstanceNotExistException;
}
