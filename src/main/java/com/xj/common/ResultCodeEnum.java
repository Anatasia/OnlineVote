package com.xj.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xujuan1 on 2017/7/20.
 */
public enum  ResultCodeEnum {

    USER_ALREADY_VOTE(100),//用户已经参与了投票
    VOTE_FAILED(101), //投票失败
    VOTE_SUCCESS(102),//投票成功
    INSERT_NEWVOTEINFO_SUCCESS(200),//添加投票信息成功
    INSERT_VOTESUBJETC_FAILED(201),//添加投票信息失败
    INSERT_VOTEOPTIONS_FAILED(202);//添加投票选项失败


    private final Integer value;
    public static final Map<Integer,String> resultCodeDesc = new HashMap<Integer,String>();
    static{
        resultCodeDesc.put(USER_ALREADY_VOTE.value(),"用户已经参与了投票");
        resultCodeDesc.put(VOTE_FAILED.value(),"投票失败");
        resultCodeDesc.put(VOTE_SUCCESS.value(),"投票成功");
        resultCodeDesc.put(INSERT_NEWVOTEINFO_SUCCESS.value(),"添加投票信息成功");
        resultCodeDesc.put(INSERT_VOTESUBJETC_FAILED.value(),"添加投票信息失败");
        resultCodeDesc.put(INSERT_VOTEOPTIONS_FAILED.value(),"添加投票选项失败");
    }

    ResultCodeEnum(Integer value){
        this.value = value;
    }

    public Integer value(){
        return value;
    }
}
