package foi.core.whatever;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import foi.core.whatever.model.Cart;
import foi.core.whatever.model.CartItems;
import foi.core.whatever.model.Product;
import foi.core.whatever.model.ProductCategory;
import foi.core.whatever.model.User;

@Component
public class YaaSServices {

	private static String TOKEN = "Bearer 022-8f5369ea-8ed8-4e0c-b875-7a88e6065eec";

	private static String PRODUCT_SERVICE = "https://api.yaas.io/hybris/product/v2/noviprojekt/products";
	private static String PRODUCT_DETAILS_SERVICE = "https://api.beta.yaas.io/hybris/productdetails/v2/noviprojekt/productdetails";
	private static String PRICE_SERVICE = "https://api.beta.yaas.io/hybris/price/v1/noviprojekt/prices";
	private static String CATEGORY_SERVICE = "https://api.beta.yaas.io/hybris/category/v1/noviprojekt/categories";
	private static String CUSTOMER_SERVICE = "https://api.yaas.io/hybris/customer/v1/noviprojekt/customers";
	private static String CART_SERVICE = "https://api.beta.yaas.io/hybris/cart/v1/noviprojekt/carts";

	private YaaSServices() {
	}

	public void newProduct(Product product) throws Exception{
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

		System.out.println("[NEW PRODUCT] Response Status: "+  response.getStatusLine().getStatusCode());

		newPrice(product);
	}

	public List<Product> getAllProducts() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(PRODUCT_DETAILS_SERVICE);

		get.setHeader("Authorization", TOKEN);
		HttpResponse response = client.execute(get);

		String jsonString = EntityUtils.toString(response.getEntity());
		System.out.println("All products: "+  jsonString);

		List<Product> products = new ArrayList<>();
		JSONArray jsonProducts;
		try {
			jsonProducts = new JSONArray(jsonString);
		} catch (JSONException e) {
			return null;
		}
		for (int i = 0; i < jsonProducts.length(); i++) {
			Product product = new Product();
			JSONObject jsonProduct = jsonProducts.getJSONObject(i).getJSONObject("product");
			product.setProductNumber(jsonProduct.getString("code"));
			product.setName(jsonProduct.getJSONObject("name").getString("en"));
			product.setDescription(jsonProduct.getJSONObject("description").getString("en"));
			JSONArray jsonPrices = jsonProducts.getJSONObject(i).getJSONArray("prices");
			for (int j = 0; j < jsonPrices.length(); j++) {
				product.setPriceEUR(jsonPrices.getJSONObject(j).getDouble("originalAmount"));
			}
			products.add(product);
		}
		return products;
	}

	public void newPrice(Product product) throws Exception{	
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

		System.out.println("[NEW PRICE] Response Status: "+  response.getStatusLine().getStatusCode());
	}

	public void newCategory(ProductCategory category) throws Exception{	
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

		System.out.println("[NEW CATEGORY] Response Status: "+  response.getStatusLine().getStatusCode());
	}

	public String newCustomer(User user) throws Exception{	
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

		System.out.println("[NEW CUSTOMER] Response Status: "+  response.getStatusLine().getStatusCode());
		String jsonString = EntityUtils.toString(response.getEntity());

		String customerId="";
		try {
			JSONObject obj = new JSONObject(jsonString);
			customerId = obj.getString("id");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return customerId;
	}

	public void editCustomer(User user) throws Exception{	
		String json = "{\"firstName\":\"" + user.getFirstName() + "\",";
		json += "\"lastName\":\"" + user.getLastName() + "\",";
		json += "\"contactEmail\":\"" + user.getEmail() + "\",";
		json += "\"contactPhone\":\"" + user.getPhone() + "\",";
		json += "\"active\":"+ user.isActive()+"}";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPatch patch = new HttpPatch(CUSTOMER_SERVICE+"/"+user.getYaasId());

		patch.setHeader("Authorization", TOKEN);
		patch.setHeader("Content-Type", "application/json");

		HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
		patch.setEntity(entity);
		HttpResponse response = client.execute(patch);

		System.out.println("[EDIT CUSTOMER] Response Status: "+  response.getStatusLine().getStatusCode());
	}

	public List<User> getAllUsers() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(CUSTOMER_SERVICE);

		get.setHeader("Authorization", TOKEN);
		HttpResponse response = client.execute(get);

		String jsonString = EntityUtils.toString(response.getEntity());
		System.out.println("All customers: "+  jsonString);

		List<User> users = new ArrayList<>();
		JSONArray jsonCustomers;
		try {
			jsonCustomers = new JSONArray(jsonString);
		} catch (JSONException e) {
			return null;
		}
		for (int i = 0; i < jsonCustomers.length(); i++) {
			User user = new User();
			JSONObject jsonCustomer = jsonCustomers.getJSONObject(i);
			user.setFirstName(jsonCustomer.getString("firstName"));
			user.setLastName(jsonCustomer.getString("lastName"));
			user.setPhone(jsonCustomer.getString("contactPhone"));
			user.setEmail(jsonCustomer.getString("contactEmail"));
			user.setActive(jsonCustomer.getBoolean("active"));
			user.setYaasId(jsonCustomer.getString("id"));
			users.add(user);
		}

		return users;
	}


	public String newCart(Cart cart) throws Exception{	
		String json = "{\"customerId\":\"" + cart.getUser().getYaasId() + "\",";
		json += "\"sessionValidated\":true,";
		json += "\"currency\":\"EUR\"}";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(CART_SERVICE);

		post.setHeader("Authorization", TOKEN);
		post.setHeader("Content-Type", "application/json");

		HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);

		System.out.println("[NEW CART] Response Status: "+  response.getStatusLine().getStatusCode());

		String jsonString = EntityUtils.toString(response.getEntity());

		String cartId="";
		try {
			JSONObject obj = new JSONObject(jsonString);
			cartId = obj.getString("cartId");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return cartId;
	}

	public void addItemToCart(Product product, String cartId) throws Exception{	
		String json = "{\"price\":{\"originalAmount\":" + product.getPriceEUR() + ",";
		json += "\"priceId\":\"5908d39517679f000db41f21\",";
		json += "\"effectiveAmount\":"+ product.getPriceEUR() + ",";
		json += "\"currency\":\"EUR\"},";
		json += "\"quantity\":1,";
		json += "\"product\":{";
		json += "\"id\":\""+product.getProductNumber()+"\",";
		json += "\"name\":\""+product.getName()+"\",";
		json += "\"description\":\""+product.getDescription()+"\"}}";

		System.out.println("JSON:"+json);

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(CART_SERVICE+"/"+cartId+"/items");

		post.setHeader("Authorization", TOKEN);
		post.setHeader("Content-Type", "application/json");

		HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);

		System.out.println("[NEW ITEM IN CART] Response Status: "+  response.getStatusLine().getStatusCode());

	}

	public List<CartItems> getItemsFromCart(String cartId) throws Exception {	

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(CART_SERVICE+"/"+cartId+"/items");

		get.setHeader("Authorization", TOKEN);
		HttpResponse response = client.execute(get);

		String jsonString = EntityUtils.toString(response.getEntity());
		System.out.println("All items: "+  jsonString);

		List<CartItems> items = new ArrayList<>();
		JSONArray jsonItems;
		try {
			jsonItems = new JSONArray(jsonString);
		} catch (JSONException e) {
			return null;
		}
		for (int i = 0; i < jsonItems.length(); i++) {
			CartItems item = new CartItems();
			JSONObject jsonItem = jsonItems.getJSONObject(i);
			item.setPrice(jsonItem.getJSONObject("price").getDouble("originalAmount"));
			item.setProductId(jsonItem.getJSONObject("product").getString("id"));
			item.setProductName(jsonItem.getJSONObject("product").getString("name"));
			item.setQuantity(jsonItem.getInt("quantity"));
			items.add(item);
		}
		return items;

	}

}
