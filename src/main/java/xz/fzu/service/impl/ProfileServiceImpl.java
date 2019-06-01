package xz.fzu.service.impl;

import org.springframework.stereotype.Service;
import xz.fzu.mapper.ProfileMapper;
import xz.fzu.model.RecruitmentProfile;
import xz.fzu.model.UserProfile;
import xz.fzu.service.IProfileService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/4/30 22:15
 */
@Service
public class ProfileServiceImpl implements IProfileService {

    @Resource
    ProfileMapper profileMapper;

    @Override
    public UserProfile getUserProfile(String userId) {
        return profileMapper.getUserProfile(userId);
    }

    @Override
    public List<RecruitmentProfile> getRecruitmentProfile() {
        return profileMapper.getRecruitmentProfile();
    }

    @Override
    public List<String> selectUserId() {
        return profileMapper.selectUserId();
    }
}
