package com.xj.service;

import com.xj.pojo.VoteSubject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xujuan1 on 2017/7/20.
 */
@Service
public interface SearchService {
    List<VoteSubject> getSearchResult(HttpServletRequest request);
}
