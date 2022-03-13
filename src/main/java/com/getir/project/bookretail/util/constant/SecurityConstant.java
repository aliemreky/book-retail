package com.getir.project.bookretail.util.constant;

public class SecurityConstant {

    public static final String TOKEN_PREFIX                 = "Bearer ";
    public static final String HEADER_STRING                = "Authorization";
    public static final String SIGN_UP_URL                  = "/api/user/sign-up";
    public static final String SECRET_KEY                   = "SKTGJWTS";

    public static final long EXPIRATION_TIME                = 864_000_000; // 10 days
}
