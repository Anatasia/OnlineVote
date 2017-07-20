package com.xj.service.impl;

import com.xj.common.LuceneUtil;
import com.xj.pojo.SearchBean;
import com.xj.pojo.VoteSubject;
import com.xj.service.SearchService;
import org.apache.log4j.Logger;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xujuan1 on 2017/7/20.
 */
@Service
public class SearchServiceImpl implements SearchService{
    private Logger logger = Logger.getLogger(SearchServiceImpl.class);
    @Autowired
    private LuceneUtil luceneUtil;
    @Autowired
    private SearchBean searchBean;

    @Override
    public List<VoteSubject> getSearchResult(HttpServletRequest request) {
        List<VoteSubject> list = null;
        try {
            String keyWord = request.getParameter("keyword");
            //传入关键字，返回靠前的十个结果
            list = luceneUtil.search(keyWord,10);
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
            logger.info(e.toString());
        }
        return list;
    }
}
