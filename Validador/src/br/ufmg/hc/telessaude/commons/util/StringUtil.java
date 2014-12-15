/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.commons.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author paulo.gomes
 */
public class StringUtil {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy - HH:mm:ss";
    public static final String DD_MM_YYYY_HH_MM = "dd/MM/yyyy - HH:mm";

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DD_MM_YYYY).format(date);
    }

    public static String formatDateWithTime(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS).format(date);
    }

    public static String formatDateToMaxHourOfDay(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DD_MM_YYYY + " 23:59:59").format(date);
    }

    public static Date parseConcatDate(Date date, Time time) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(
                new SimpleDateFormat("dd/MM/yyyy").format(date) + " "
                + new SimpleDateFormat("HH:mm:ss").format(time));
    }
    
    public static Date parseConcatDate(Date date, Date time) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(
                new SimpleDateFormat("dd/MM/yyyy").format(date) + " "
                + new SimpleDateFormat("HH:mm:ss").format(time));
    }

    public static Date toMaxHourOfDay(Date date) throws ParseException {
        if (date == null) {
            date = Calendar.getInstance().getTime();
        }
        return new SimpleDateFormat().parse(formatDateToMaxHourOfDay(date));
    }

    public static String formatDateToMinHourOfDay(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DD_MM_YYYY + " 00:00:00").format(date);
    }

    public static Date toMinHourOfDay(Date date) throws ParseException {
        if (date == null) {
            date = Calendar.getInstance().getTime();
        }
        return new SimpleDateFormat().parse(formatDateToMinHourOfDay(date));
    }

    public static String formatDateOnlyNumbers(Date date) {
        return formatDate(date).replaceAll("[^\\d]", "");
    }

    public static String format(Date date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        if (date != null) {
            format = new SimpleDateFormat(pattern);
            return format.format(date);
        }
        return format.format(new Date());
    }

    public static Date parse(String dateStr) {
        DateFormat format = null;
        Date date = null;
        try {
            format = new SimpleDateFormat(DD_MM_YYYY);
            date = format.parse(dateStr);
        } catch (ParseException ex) {
        }
        return date;
    }

    public static Date parse(String dateStr, String pattern) {
        DateFormat format = null;
        Date date = null;

        try {
            format = new SimpleDateFormat(pattern);
            date = format.parse(dateStr);
        } catch (ParseException ex) {
        }

        return date;
    }

    public static Long toLong(String str) {
        Long value = 0L;
        try {
            value = Long.parseLong(str);
        } catch (Exception ex) {
        }
        return value;
    }

    public static Long toLong(Object obj) {
        try {
            return toLong(obj.toString());
        } catch (Exception ex) {
            return 0L;
        }
    }

    public static boolean validarEmail(String email) {
        boolean valido = false;
        if (email == null || email.indexOf("@") <= 1
                || email.trim().isEmpty() || email.indexOf(".") <= 1) {
            return valido;
        }
        String auxEmail = email.replace("@", "");
        if (auxEmail.indexOf("..") > 0 || auxEmail.indexOf("__") > 0 || auxEmail.indexOf("--") > 0) {
            return valido;
        }
        String regex = "^[\\w\\d\\.\\_\\-]+@([\\w\\d\\.\\_\\-]+\\.)+[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static Object formatDateToMaxHourOfDay(Object get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
