package Com.API.HTTPRequests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class HTTPGetAPI {
	
	//method for without headers 
	
	public CloseableHttpResponse GetAPI(String Url) throws ClientProtocolException, IOException
	{
		//created http client connection 
		CloseableHttpClient HttpClientRef=HttpClients.createDefault();
		//created object of httpget calss and passed requred url
		HttpGet HttpGetRef=new HttpGet(Url);
		//hit the api now stored completed esponse information in reference valiable 
		
		CloseableHttpResponse HttpClientResponse=HttpClientRef.execute(HttpGetRef);
		return HttpClientResponse;
		
	}
	
	//method for with headers 
	
	public CloseableHttpResponse GetAPI(String Url,HashMap<String,String> hashmapref) throws ClientProtocolException, IOException
	{
		//created http client connection 
		CloseableHttpClient HttpClientRef=HttpClients.createDefault();
		//created object of httpget calss and passed requred url
		HttpGet HttpGetRef=new HttpGet(Url);
		//hit the api now stored completed esponse information in reference valiable 
		
		
		for(Map.Entry<String,String> entry:hashmapref.entrySet())
		{
			HttpGetRef.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse HttpClientResponse=HttpClientRef.execute(HttpGetRef);
		return HttpClientResponse;
		
	}
	
	//method post call
	
	
	

}
