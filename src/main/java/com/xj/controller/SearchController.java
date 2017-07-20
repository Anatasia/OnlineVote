package com.xj.controller;

import com.xj.common.AjaxResult;
import com.xj.pojo.VoteSubject;
import com.xj.service.SearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xujuan1 on 2017/7/18.
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    private Logger logger = Logger.getLogger(SearchController.class);
    @Autowired
    private SearchService searchService;
    @RequestMapping(value = "/search_subject", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult searchSubject(HttpServletRequest request){
        AjaxResult result = new AjaxResult(1);
        try {
            List<VoteSubject> list =searchService.getSearchResult(request);
        }catch (Exception e){
            logger.info(e.toString());
            result.setCode(-1);
            result.setMsg("内部错误");
        }
        return result;
    }
}
