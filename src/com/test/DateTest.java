package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) throws Exception{
        Date date=new Date();
        System.out.println(date);
        String str="2001-07-29";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date2=null;
        date2=simpleDateFormat.parse(str);
        System.out.println(date2);
        System.out.println(simpleDateFormat.format(date2));

        java.sql.Date date1=new java.sql.Date(date.getTime());
        System.out.println(date1);
    }
}
