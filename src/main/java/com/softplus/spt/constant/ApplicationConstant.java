package com.softplus.spt.constant;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationConstant {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");
    public static String APPLICATION_VERSION_CURRENT = "v.1.0."+sdf.format(new Date());

}
