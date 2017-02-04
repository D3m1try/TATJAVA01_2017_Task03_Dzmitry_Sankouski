package com.epam.news_manager.view;

import com.epam.news_manager.dao.impl.FileIdGenerator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry_Sankouski on 02-Feb-17.
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        int i = 0;
        Pattern pattern = Pattern.compile("(\\s\\-[^\\s]+\\s+)([^\\s]+)");
        Matcher matcher = pattern.matcher("     add   -ISBN  asjk  -d 2013-03-03 -ksdjf kjshdfk   - sjfhksfh kj");

        Pattern p = Pattern.compile("\\s\\-ISBN+\\s+[^\\s]+\\s");
        Matcher m = p.matcher(" -ISBN  asjk ");

        pattern = Pattern.compile("title:zzz");
        matcher = pattern.matcher("Book\n" +
                "title:zzzzzz;date:Thu Jan 01 06:44:14 MSK 1970;message:kjndfskjsjkdfjvnsk;isbn:sdjfkvgsjdfkvasjdfvka;pageCount:0;id:1.Book");


        while (matcher.find()) {
            System.out.println(matcher.group(0));
//            System.out.println(matcher.group(2));
            i++;
        }

//        StringBuilder result = new StringBuilder();
//        result.append("sdfg").append("---");
//        System.out.println(result.toString());


    }
}
