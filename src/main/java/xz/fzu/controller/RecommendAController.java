package xz.fzu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xz.fzu.algorithm.RecomAlgorithm;
import xz.fzu.exception.TokenExpiredException;
import xz.fzu.model.RecommendResult;
import xz.fzu.service.IProfileService;
import xz.fzu.service.IRecommendService;
import xz.fzu.service.IUserService;
import xz.fzu.vo.ResponseData;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Murphy
 * @date 2019/4/30 19:29
 */
@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class RecommendAController {

    @Resource
    RecomAlgorithm recomAlgorithm;
    @Resource
    IProfileService iProfileService;
    @Resource
    IUserService iUserService;

    @Autowired
    public RecommendAController(RecomAlgorithm recomAlgorithm) {
        this.recomAlgorithm = recomAlgorithm;
        new Thread() {
            @Override
            public void run() {
                for (String string : iProfileService.selectUserId()) {

                    recomAlgorithm.recomAlgorithm(iProfileService.getUserProfile(string), iProfileService.getRecruitmentProfile(), 10);
                }
            }
        };
    }

    @Resource
    IRecommendService iRecommendService;
    /**
     * @param token
     * @return xz.fzu.vo.ResponseData
     * @author Murphy
     * @date 2019/4/29 21:53
     * @description 推荐接口
     */
    @RequestMapping(value = "/getrecommend", method = RequestMethod.POST)
    public ResponseData getRecommend(@RequestParam String token) throws TokenExpiredException {

        ResponseData responseData = new ResponseData();
        String userId = iUserService.verifyToken(token);
        List<RecommendResult> recruitmentProfiles = iRecommendService.getListResult(userId);
        responseData.setResultObject(recruitmentProfiles);

        return responseData;
    }
}