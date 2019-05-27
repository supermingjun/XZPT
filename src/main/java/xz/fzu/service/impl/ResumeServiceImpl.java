package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IResumeDao;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.Resume;
import xz.fzu.service.IResumeService;
import xz.fzu.vo.PageData;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/4/30 0:18
 */
@Service
public class ResumeServiceImpl implements IResumeService {

    @Resource
    IResumeDao iResumeDao;

    @Override
    public void insertResume(String userId, Resume resume) {

        resume.setUserId(userId);
        iResumeDao.insert(resume);
    }

    @Override
    public void updateResume(String userId, Resume resume) {

        resume.setUserId(userId);
        iResumeDao.updateByPrimaryKeySelective(resume);
    }

    @Override
    public List<Resume> getListResume(String userId, PageData requestPage) throws InstanceNotExistException {

        List<Resume> resumeList = iResumeDao.selectListByUserId(userId, (requestPage.getCurrentPage() - 1) * requestPage.getPageSize(), requestPage.getPageSize());
        if (resumeList == null) {
            throw new InstanceNotExistException();
        }

        return resumeList;
    }

    @Override
    public int deleteResume(int resumeId) throws InstanceNotExistException {

        int rowAffect = iResumeDao.deleteInstance(resumeId);
        if (rowAffect == 0) {
            throw new InstanceNotExistException();
        }

        return 1;
    }

    @Override
    public Resume getResume(String userId, Long resumeId) throws InstanceNotExistException {

        //TODO 安全认证
        Resume resume = iResumeDao.selectInstanceByResumeId(resumeId);
        if (resume == null) {
            throw new InstanceNotExistException();
        }

        return resume;
    }

    @Override
    public void copyResume(Long resumeId) {

        iResumeDao.copyInstance(resumeId);
    }

    @Override
    public Resume getFirstResume(String userId) throws InstanceNotExistException {

        List<Resume> list = iResumeDao.selectAll();
        if (list.size() == 0) {
            throw new InstanceNotExistException();
        }
        return list.get(0);
    }
}
