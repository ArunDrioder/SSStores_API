package ssapi.tests.POST.VerifyOTP;

import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import ssapi.base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import ssapi.data.Xls_Reader;

import static io.restassured.RestAssured.given;

public class VerifyOTPTestClass extends BaseClass
{
    public static Response response;
    public static RequestSpecification httpPostRequest;
    public static JSONObject requestParam;
    public static Xls_Reader reader;


    @Test
    public void verifyOTPTest()
    {
        RestAssured.baseURI = verifyOTPPostURL;
        httpPostRequest = RestAssured.given();
        reader = new Xls_Reader("C:\\Backup\\RestAPI Automation - Workspace\\SSStores_API\\src\\test\\java\\ssapi\\data\\SSAPI_data.xlsx");
        String sheetName = "Login";

        int rowCount = reader.getRowCount(sheetName);
        for (int rowNum = 2; rowNum<=rowCount; rowNum++)
        {
            String user_id = reader.getCellData(sheetName,"id",rowNum);
            String otp = reader.getCellData(sheetName,"otp",rowNum);

            requestParam = new JSONObject();
            requestParam.put("user_id",user_id);
            requestParam.put("otp",otp);
            httpPostRequest.header("Content-Type","application/json");
            httpPostRequest.body(requestParam.toString());

            response = httpPostRequest.request(Method.POST,"/verify/otp");

            String getResponseBody = response.getBody().asString();
            System.out.println("Response Body is :"+""+"\n" +getResponseBody);

            int statusCode = response.getStatusCode();
            System.out.println("The status code is :" +statusCode);
            Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_SUCCESS);

            String statusLine = response.getStatusLine();
            System.out.println(statusLine);
            Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

            String statusMessage = response.jsonPath().get("status");
            System.out.println("The status message is:" +statusMessage);
            Assert.assertEquals(statusMessage,RESPONSE_STATUS_STRING_SUCCESS,"Sorry,the test failed");

            String responseMessage = response.jsonPath().get("message");
            System.out.println("The response message is :" +responseMessage);
            Assert.assertEquals(responseMessage,RESPONSE_STATUS_MESSAGE_OTP_SUCCESS);

            int responseUserId = response.jsonPath().get("user.id");
            System.out.println("The newly registered user's ID is :" +responseUserId);





        }


    }



    }


