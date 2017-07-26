package com.xj.common;

/**
 * Created by xujuan1 on 2017/7/20.
 */
public class Constants {
    public final static int USER_ALREADY_VOTE = 100;//用户已经参与了投票
    public final static int VOTE_FAILED = 101;//投票失败
    public final static int VOTE_SUCCESS = 102;//投票失败

    //添加新的投票
    public final static int INSERT_NEWVOTEINFO_SUCCESS = 200;//添加投票信息成功
    public final static int INSERT_VOTESUBJETC_FAILED = 201;//添加投票信息失败
    public final static int INSERT_VOTEOPTIONS_FAILED = 202;//添加投票选项失败

    //Session中保存的信息名
    public final static String SESSION_USER = "user";//保存用户信息

}
