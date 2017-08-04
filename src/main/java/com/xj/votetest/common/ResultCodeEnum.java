package com.xj.votetest.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xujuan1 on 2017/7/20.
 */
public enum ResultCodeEnum {

    USER_ALREADY_VOTE(100),//用户已经参与了投票
    VOTE_FAILED(101), //投票失败
    VOTE_SUCCESS(102),//投票成功
    INSERT_NEWVOTEINFO_SUCCESS(200),//添加投票信息成功
    INSERT_VOTESUBJETC_FAILED(201),//添加投票信息失败
    INSERT_VOTEOPTIONS_FAILED(202),//添加投票选项失败

    //注册结果码
    PWD_LENGTH_VERIFIED_FAILED(301),
    REGISTER_SUCCESS(302),
    MALICIOUS_CODE_FOUND(303),
    PWD_NULL(304),
    UNAME_NULL(305),
    COMFIRMPWD_NULL(306),
    PWD_NOT_EQUAL_WITH_CONFIRMPWD(307),



    //登录操作结果码
    LOGIN_SUCCESS(401),
    LOGIN_FAILD(402),

    //数据库操作失败
    INTERNEL_ERROR(500),

    //用户管理
    GRANT_SUCCESS(601),
    GRANT_FAILD(602),
    REVOKE_SUCCESS(603),
    REVOKE_FAILD(604)
    ;


    private final Integer value;
    public static final Map<Integer,String> resultCodeDesc = new HashMap<Integer,String>();
    static{
        resultCodeDesc.put(USER_ALREADY_VOTE.value(),"用户已经参与了投票");
        resultCodeDesc.put(VOTE_FAILED.value(),"投票失败");
        resultCodeDesc.put(VOTE_SUCCESS.value(),"投票成功");
        resultCodeDesc.put(INSERT_NEWVOTEINFO_SUCCESS.value(),"添加投票信息成功");
        resultCodeDesc.put(INSERT_VOTESUBJETC_FAILED.value(),"添加投票信息失败");
        resultCodeDesc.put(INSERT_VOTEOPTIONS_FAILED.value(),"添加投票选项失败");
        resultCodeDesc.put(PWD_LENGTH_VERIFIED_FAILED.value(),"密码长度验证失败，长度为6~16");
        resultCodeDesc.put(REGISTER_SUCCESS.value(),"注册成功");
        resultCodeDesc.put(MALICIOUS_CODE_FOUND.value(),"发现恶意代码");
        resultCodeDesc.put(PWD_NULL.value(),"密码为空");
        resultCodeDesc.put(UNAME_NULL.value(),"用户名为空");
        resultCodeDesc.put(COMFIRMPWD_NULL.value(),"确认密码为空");
        resultCodeDesc.put(PWD_NOT_EQUAL_WITH_CONFIRMPWD.value,"密码不一致");
        resultCodeDesc.put(INTERNEL_ERROR.value,"内部错误");
        resultCodeDesc.put(LOGIN_SUCCESS.value(),"登录成功");
        resultCodeDesc.put(LOGIN_FAILD.value(),"用户名密码错误");
        resultCodeDesc.put(GRANT_SUCCESS.value(),"授权成功");
        resultCodeDesc.put(GRANT_FAILD.value(),"授权失败");
        resultCodeDesc.put(REVOKE_SUCCESS.value,"降级成功");
        resultCodeDesc.put(REVOKE_FAILD.value,"降级失败");
    }

    ResultCodeEnum(Integer value){
        this.value = value;
    }

    public Integer value(){
        return value;
    }
}
