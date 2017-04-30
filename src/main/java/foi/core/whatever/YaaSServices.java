package foi.core.whatever;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import foi.core.whatever.model.Product;

@Component
public class YaaSServices {
		
	private static String TOKEN = "Bearer 022-c1169181-7cdd-4da1-936e-9d38797f6cb8";
	private static String PRODUCT_SERVICE = "https://api.yaas.io/hybris/product/v2/milena/products";

    private YaaSServices() {
    }
    
    public void newProduct(Product product) throws ClientProtocolException, IOException{
		
    	String json = "{\"name\":\"" + product.getName() + "\",";
		json += "\"code\":\"" + product.getProductNumber() + "\",";
		json += "\"description\":\"" + product.getDescription() + "\"}";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(PRODUCT_SERVICE);

		post.setHeader("Authorization", TOKEN);
		post.setHeader("Content-Language", "en");
		post.setHeader("Content-Type", "application/json");

		HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);

		System.out.println("Response status: "+  response.getStatusLine().getStatusCode());
   
    }
    
    public List<Product> getAllProducts(){
    	return null;
    }
	//TODO: Implementirati ostale servise
}
