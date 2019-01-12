package Com.API.GetTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import Com.API.BaseClass.BaseClass;
import Com.API.HTTPRequests.HTTPGetAPI;
import Com.API.HTTPRequests.HTTPPost;
import Com.API.Postdata.Users;
import Com.API.Utility.TestUtility;

public class PostAPITest extends BaseClass {
	BaseClass BaseClassref;
	TestUtility TestUtilityref;
	String DomainUrl;
	String ApiUrl;
	String Url;
	HTTPPost HTTPPostref;
	CloseableHttpResponse CloseableHttpResponseref;
	
	public PostAPITest()
	{
		super();
	}
	
	@BeforeMethod
	public void SetUp()
	{
		BaseClassref=new BaseClass();
		TestUtilityref=new TestUtility();
		 DomainUrl=prop.getProperty("EndUrl");
		 ApiUrl=prop.getProperty("SerUrl");
		 Url=DomainUrl+ApiUrl;
	
	}
	
	@Test
	public void Getvalidation() throws ClientProtocolException, IOException
	{
		HTTPPostref=new HTTPPost();
		
		HashMap<String,String> HashMaprefhead=new HashMap<String,String>();
		HashMaprefhead.put("Content-Type", "application/json");
		
		//jackson api to covert jav object to json object -marsaling
		
		ObjectMapper mapper=new ObjectMapper();
		Users Usersref=new Users ("morpheus","leader");
		
		//convert pojo into josn file 
		
		
		
		mapper.writeValue(new File("C:/Users/home/workspace/ProjectBackEnd/src/main/java/Com/API/Postdata/Users.json"), Usersref);
		
		
		
		
		//covernt pojo into json string file
		
		String jsonstring=mapper.writeValueAsString(Users.class);
		
		CloseableHttpResponseref=HTTPPostref.PostAPI(Url, HashMaprefhead, jsonstring);
		
		
		
		//validate status code 
		int statuscode=CloseableHttpResponseref.getStatusLine().getStatusCode();
		System.out.println("the status code is" + statuscode);
		
		Assert.assertEquals(statuscode, 400, "status code is not 200");
		
		
		
	
		
		//now raw response into json stringformat
		
		String JsonStr=EntityUtils.toString(CloseableHttpResponseref.getEntity(), "UTF-8");
		
		//System.out.println("the json string is "+ JsonStr);
		
		
		//unmaraling jsonstring into java object
		
		
		//Users usersresponse=mapper.readValue(JsonStr, Users.class);
		
		Users usersres=mapper.readValue(JsonStr, Users.class);
		
		Assert.assertTrue(Usersref.getName().equals(usersres.getName()));
		
	}

}
