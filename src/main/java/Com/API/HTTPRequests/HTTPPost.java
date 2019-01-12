package Com.API.HTTPRequests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HTTPPost {
	
	
	public CloseableHttpResponse PostAPI(String Url,HashMap<String,String> hashmapref,String Jsonpayload) throws ClientProtocolException, IOException
	{
		//created http client connection 
		CloseableHttpClient HttpClientRef=HttpClients.createDefault();
		//created object of httppost calss and passed requred url
		HttpPost HttpPostref=new HttpPost(Url);
		
		
		//link all headers to httppostref
		
		
		for(Map.Entry<String,String> entry:hashmapref.entrySet())
		{
			HttpPostref.addHeader(entry.getKey(), entry.getValue());
			
		}
		
		//define json body
		
		HttpPostref.setEntity(new StringEntity(Jsonpayload));
		
		//submitting Post reqest
		
		
		CloseableHttpResponse HttpClientResponse=HttpClientRef.execute(HttpPostref);
		return HttpClientResponse;
		

}

}