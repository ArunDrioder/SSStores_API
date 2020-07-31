package ssapi.tests.POST.Login;

import ssapi.base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class LoginTestClass extends BaseClass
{
    public static Response response;
    public static RequestSpecification httpPostRequest;
    public static JSONObject requestParam;

    @Test(dataProvider = "userLoginData")
    public void loginTest(String mobile)
    {
        RestAssured.baseURI = loginPostURL;
        httpPostRequest = RestAssured.given();

         requestParam = new JSONObject();
        requestParam.put("mobile",mobile);
        httpPostRequest.header("Content-Type","application/json");
        httpPostRequest.body(requestParam.toString());

         response = httpPostRequest.request(Method.POST,"/login");

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
        Assert.assertEquals(responseMessage,RESPONSE_STATUS_MESSAGE_isFirstTimeRegistrationMessage);

        int responseUserId = response.jsonPath().get("user.id");
        System.out.println("The newly registered user's ID is :" +responseUserId);
    }

    @DataProvider(name = "userLoginData")
    Object[][] getUserCredentials()
    {
        String userData[][] = {{"9443337463"},{"9790191817"},{"9486157661"},{"9940855238"},{"7639915844"}};
        return(userData);
    }



}
