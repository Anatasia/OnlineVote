package com.xj.votetest.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xujuan1 on 2017/7/26.
 */
public class InputVerify {
    public boolean hasInjectionData(String inputData)
    {
        if (inputData.isEmpty())
            return false;

        //里面定义恶意字符集合
        //验证inputData是否包含恶意集合
        Pattern pattern = Pattern.compile(getRegexString());
        Matcher matcher = pattern.matcher(inputData.toLowerCase());
        if(matcher.find()) return true;
        else return false;
    }

    //获取正则表达式
    private static String getRegexString()
    {
        //构造SQL的注入关键字符
        String[] strBadChar =
                {
                        //"select\\s",
                        //"from\\s",
                        "insert\\s",
                        "delete\\s",
                        "update\\s",
                        "drop\\s",
                        "truncate\\s",
                        "exec\\s",
                        "count\\(",
                        "declare\\s",
                        "asc\\(",
                        "mid\\(",
                        "char\\(",
                        "net user",
                        "xp_cmdshell",
                        "/add\\s",
                        "exec master.dbo.xp_cmdshell",
                        "net localgroup administrators"
                };

        //构造正则表达式
        String str_Regex = ".*(";
        for (int i = 0; i < strBadChar.length - 1; i++)
        {
            str_Regex += strBadChar[i] + "|";
        }
        str_Regex += strBadChar[strBadChar.length - 1] + ").*";

        return str_Regex;
    }


}
