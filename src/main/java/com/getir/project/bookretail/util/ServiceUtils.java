package com.getir.project.bookretail.util;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;

@Slf4j
public class ServiceUtils {

    public static String stringTransformForEncryptionOfSecretKey(String s, int i) {
        char[] chars = s.toCharArray();
        for (int j = 0; j < chars.length; j++)
            chars[j] = (char) (chars[j] ^ i);
        return String.valueOf(chars);
    }

    public static boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }

    public static String getStackTrace(final Throwable throwable){
        String trace = "";
        try(final StringWriter sw = new StringWriter(); final PrintWriter pw = new PrintWriter(sw,true)){
            throwable.printStackTrace(pw);
            trace = sw.getBuffer().toString();
        }catch (final Exception e){
            log.error(e.getMessage());
        }
        return trace;
    }

}
