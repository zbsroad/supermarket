package Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-DD");
    //字符串转换为Util
    public static Date toUtil(String str){
        try {
            Date date=simpleDateFormat.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

    return null;
    }
//Util.Date转换为字符串
    public static String UtiltoString(Date date)
    {
        return simpleDateFormat.format(date);
    }
//Util.Date 转化为 Sql.Date
    public static java.sql.Date toSql(Date date)
    {
        java.sql.Date date1=new java.sql.Date(date.getTime());
        return  date1;
    }

}
