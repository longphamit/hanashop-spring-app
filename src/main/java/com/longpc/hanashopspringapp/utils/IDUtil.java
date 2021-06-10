package com.longpc.hanashopspringapp.utils;
import java.util.Calendar;
import java.util.Date;

public class IDUtil {
    public static Calendar c=Calendar.getInstance();
    public static String counter="1";
    public static Date date=c.getTime();
    public static String generateID() throws Exception{
        StringBuilder id=new StringBuilder("P");
        if(c.getTime().compareTo(date)>0){
            counter="0";
            date=c.getTime();
        }
        id.append(c.get(Calendar.DATE));
        id.append(c.get(Calendar.MONTH)+1);
        id.append(c.get(Calendar.YEAR));
        id.append("-");
        id.append(counter);
        counter=(Integer.parseInt(counter)+1)+"";
        return id.toString();
    }

    public static void main(String[] args) throws Exception{
        for(long i=0;i<20;i++){
            System.out.println(generateID());
        }
    }
}
