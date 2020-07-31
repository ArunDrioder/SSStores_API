package ssapi.base;

import ssapi.config.ReadConfig;

import java.io.IOException;

public class BaseClass
{
    public static int RESPONSE_STATUS_CODE_SUCCESS = 200;
    public static int RESPONSE_STATUS_CODE_SUCCESS_CREATED = 201;
    public static int RESPONSE_STATUS_CODE_INTERNAL_SERVER_ERROR = 500;
    public static int RESPONSE_STATUS_CODE_URL_NOT_FOUND = 404;
    public static int RESPONSE_STATUS_CODE_UNAUTHORIZED = 401;
    public static String RESPONSE_STATUS_STRING_SUCCESS = "success";
    public static String RESPONSE_STATUS_MESSAGE_isFirstTimeRegistrationMessage = "Registered Details Successfully";
    public static String RESPONSE_STATUS_MESSAGE_loginMessage = "OTP has been sent to the number";
    public static String RESPONSE_STATUS_MESSAGE_OTP_SUCCESS = "Otp has been verified Successfully";
    public static String RESPONSE_STATUS_MESSAGE_OTP_ERROR = "Invalid Otp";


    public static String loginPostURL = "";
    public static String verifyOTPPostURL = "";
    public static String registerPostURL = "";

    static {
        ReadConfig readConfig = new ReadConfig();
        loginPostURL = readConfig.getLoginPostURL();
        verifyOTPPostURL = readConfig.getOTPVerifyPostURL();
        registerPostURL = readConfig.getRegisterPostURL();

    }





}
