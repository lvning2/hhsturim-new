package rebotstudio.hhsturim.test;


import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.SystemUtil;
import rebotstudio.hhsturim.common.DateUtils;
import rebotstudio.hhsturim.common.StringUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Test {



    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse("2020-3-07");

        String s = DateUtils.GetQuarterByDate(parse);
        System.out.println(s);

    }


}
