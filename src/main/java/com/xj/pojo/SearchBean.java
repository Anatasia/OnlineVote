package com.xj.pojo;

import org.springframework.stereotype.Component;

/**
 * Created by xujuan1 on 2017/7/20.
 */
@Component
public class SearchBean {
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    private String keyWord;

}
