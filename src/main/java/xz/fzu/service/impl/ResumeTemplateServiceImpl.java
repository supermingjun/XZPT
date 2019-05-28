package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.dao.IResumeTemplateDao;
import xz.fzu.exception.InstanceNotExistException;
import xz.fzu.model.ResumeTemplate;
import xz.fzu.service.IResumeTemplateService;
import xz.fzu.util.PageUtil;
import xz.fzu.vo.PageData;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/5/23 12:56
 */
@Service
public class ResumeTemplateServiceImpl implements IResumeTemplateService {

    @Resource
    IResumeTemplateDao iResumeTemplateDao;

    @Override
    public void insertInstance(ResumeTemplate instance) {
        iResumeTemplateDao.insert(instance);
    }

    @Override
    public ResumeTemplate getInstance(String fileName) {
        return iResumeTemplateDao.selectByPrimaryKey(fileName);
    }

    @Override
    public List<ResumeTemplate> getPageData(PageData<ResumeTemplate> requestPage) throws InstanceNotExistException {
        List<ResumeTemplate> list = iResumeTemplateDao.selectAll();

        return PageUtil.paging(list, requestPage);
    }
}
