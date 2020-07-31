package ssapi.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig
{
    public static Properties prop;
    public static FileInputStream fis;

    public ReadConfig()
    {
        prop = new Properties();
        try {
            fis = new FileInputStream("C:\\Backup\\RestAPI Automation - Workspace\\SSStores_API\\src\\test\\java\\ssapi\\config\\config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getLoginPostURL()
    {
        String loginPostURL = prop.getProperty("loginPostURL");
        return loginPostURL;
    }

    public String getOTPVerifyPostURL()
    {
        String verifyOTPPostURL = prop.getProperty("otpVerifyPostURL");
        return verifyOTPPostURL;
    }

    public String getRegisterPostURL()
    {
        String registerPostPostURL = prop.getProperty("registerPostURL");
        return registerPostPostURL;
    }


}
