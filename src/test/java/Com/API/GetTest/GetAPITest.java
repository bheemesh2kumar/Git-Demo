package Com.API.GetTest;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.API.BaseClass.BaseClass;
import Com.API.HTTPRequests.HTTPGetAPI;
import Com.API.Utility.TestUtility;

public class GetAPITest extends BaseClass  {
	
	BaseClass BaseClassref;
	HTTPGetAPI HTTPGetAPIref;
	String ApiUrl;
	String DomainUrl;
	String Url;
	CloseableHttpResponse HttpClientResponse;
	TestUtility TestUtilityref;
	
	public GetAPITest()
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
		HTTPGetAPIref=new HTTPGetAPI();
		
		HashMap<String,String> HashMaprefhead=new HashMap<String,String>();
		HashMaprefhead.put("Content-Type", "application/json");
		
		
		HttpClientResponse=HTTPGetAPIref.GetAPI(Url,HashMaprefhead);
		
		//gt the statu codes 
		
				int statuscode=HttpClientResponse.getStatusLine().getStatusCode();
				System.out.println("the status code is" + statuscode);
				
				Assert.assertEquals(statuscode, 200, "status code is not 200");
				
				
				
			
				
				//now get joson object information in the string format
				
				String JsonStr=EntityUtils.toString(HttpClientResponse.getEntity(), "UTF-8");
				
				JSONObject Jsonformat=new JSONObject(JsonStr);
				System.out.println("the  Json resposne paload is" + Jsonformat);
				
				String perpage=TestUtilityref.getValueByJPath(Jsonformat, "/per_page");
				System.out.println("page per details are ***********>" + perpage);
				
				
				Assert.assertEquals(Integer.parseInt(perpage), 3,"Page is not valid");
				
				String totalrecords=TestUtilityref.getValueByJPath(Jsonformat, "/total");
				Assert.assertEquals(Integer.parseInt(totalrecords), 12, "Total  records are not 12");
				
				
				String firstarryname=TestUtilityref.getValueByJPath(Jsonformat, "/data[0]/name");
				System.out.println("0 arry of firtname is " +firstarryname );
				Assert.assertEquals(firstarryname, "cerulean");
				
				String PantoneVlaue=TestUtilityref.getValueByJPath(Jsonformat, "/data[2]/pantone_value");
				
				System.out.println("the panton value is" +PantoneVlaue );
				Assert.assertEquals(PantoneVlaue, "19-1664", "panton value is failed");

				
				
				
				
				
				
				
				
				
				
				//getting hearders also 
				
				Header[] headerarry=HttpClientResponse.getAllHeaders();
				
				HashMap<String,String> AllHeaders=new HashMap<String,String>();
				
				
				for(Header ele:headerarry)
				{
					AllHeaders.put(ele.getName(), ele.getValue());
				}
				
				System.out.println("All headers are " +AllHeaders );
				
				
		
	}
	
	
	
	

}
