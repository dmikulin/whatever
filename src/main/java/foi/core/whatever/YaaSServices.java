package foi.core.whatever;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import foi.core.whatever.model.Cart;
import foi.core.whatever.model.Product;
import foi.core.whatever.model.ProductCategory;
import foi.core.whatever.model.User;

@Component
public class YaaSServices {

	private static String TOKEN = "Bearer 021-fb4de8e1-f63a-4a77-a45a-486aa00e88a1";

	private static String PRODUCT_SERVICE = "https://api.yaas.io/hybris/product/v2/noviprojekt/products";
	private static String PRODUCT_DETAILS_SERVICE = "https://api.beta.yaas.io/hybris/productdetails/v2/noviprojekt/productdetails";
	private static String PRICE_SERVICE = "https://api.beta.yaas.io/hybris/price/v1/noviprojekt/prices";
	private static String CATEGORY_SERVICE = "https://api.beta.yaas.io/hybris/category/v1/noviprojekt/categories";
	private static String CUSTOMER_SERVICE = "https://api.yaas.io/hybris/customer/v1/noviprojekt/customers";
	private static String CART_SERVICE = "https://api.beta.yaas.io/hybris/cart/v1/noviprojekt/carts";

	private YaaSServices() {
	}

	public void newProduct(Product product) throws ClientProtocolException, IOException{

		String json = "{\"id\":\"" + product.getProductNumber() + "\",";
		json += "\"name\":\"" + product.getName() + "\",";
		json += "\"code\":\"" + product.getProductNumber() + "\",";
		json += "\"description\":\"" + product.getDescription() + "\",";
		json += "\"published\":true}";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(PRODUCT_SERVICE);

		post.setHeader("Authorization", TOKEN);
		post.setHeader("Content-Language", "en");
		post.setHeader("Content-Type", "application/json");

		HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);

		System.out.println("Response status: "+  response.getStatusLine().getStatusCode());

		newPrice(product);
	}


	public List<Product> getAllProducts() throws ClientProtocolException, IOException{

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(PRODUCT_DETAILS_SERVICE);

		get.setHeader("Authorization", TOKEN);
		HttpResponse response = client.execute(get);

		String jsonString = EntityUtils.toString(response.getEntity());

		System.out.println("All products: "+  jsonString);

		return null;
	}

	public void newPrice(Product product) throws ClientProtocolException, IOException{	

		String json = "{\"productId\":\"" + product.getProductNumber() + "\",";
		json += "\"originalAmount\":" + product.getPriceEUR() + ",";
		json += "\"currency\":\"EUR\"}";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(PRICE_SERVICE);

		post.setHeader("Authorization", TOKEN);
		post.setHeader("Content-Type", "application/json");

		HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);

		System.out.println("Response status: "+  response.getStatusLine().getStatusCode());
	}

	public void newCategory(ProductCategory category) throws ClientProtocolException, IOException{	

		String json = "{\"code\":\"" + category.getName().toLowerCase() + "\",";
		json += "\"name\":{\"en\":\"" + category.getName() + "\"},";
		json += "\"published\":true}";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(CATEGORY_SERVICE);

		post.setHeader("Authorization", TOKEN);
		post.setHeader("Content-Type", "application/json");

		HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);

		System.out.println("Response status: "+  response.getStatusLine().getStatusCode());
	}

	public String newCustomer(User user) throws ClientProtocolException, IOException{	

		String json = "{\"firstName\":\"" + user.getFirstName() + "\",";
		json += "\"lastName\":\"" + user.getLastName() + "\",";
		json += "\"contactEmail\":\"" + user.getEmail() + "\",";
		json += "\"contactPhone\":\"" + user.getPhone() + "\",";
		json += "\"active\":true}";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(CUSTOMER_SERVICE);

		post.setHeader("Authorization", TOKEN);
		post.setHeader("Content-Type", "application/json");

		HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);

		System.out.println("Response status: "+  response.getStatusLine().getStatusCode());
		String jsonString = EntityUtils.toString(response.getEntity());
		System.out.println("User: "+  jsonString);

		String customerId="";
		return customerId;
	}


	public List<User> getAllUsers() throws ClientProtocolException, IOException{

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(CUSTOMER_SERVICE);

		get.setHeader("Authorization", TOKEN);
		HttpResponse response = client.execute(get);

		String jsonString = EntityUtils.toString(response.getEntity());

		System.out.println("All customers: "+  jsonString);

		return null;
	}


	public void newCart(Cart cart) throws ClientProtocolException, IOException{	

		String json = "{\"customerId\":\"" + cart.getUser().getYaasId() + "\",";
		json += "\"currency\":\"EUR\"}";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(CART_SERVICE);

		post.setHeader("Authorization", TOKEN);
		post.setHeader("Content-Type", "application/json");

		HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);

		System.out.println("Response status: "+  response.getStatusLine().getStatusCode());
	}

}
