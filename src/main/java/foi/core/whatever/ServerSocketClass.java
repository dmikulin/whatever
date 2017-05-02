package foi.core.whatever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import foi.core.whatever.model.Cart;
import foi.core.whatever.model.Product;
import foi.core.whatever.model.User;
import foi.core.whatever.services.CartService;
import foi.core.whatever.services.ProductService;
import foi.core.whatever.services.UserService;

@Component
public class ServerSocketClass implements ApplicationRunner {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private YaaSServices yaasServices;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {

		String fromclient;
		ServerSocket server = new ServerSocket(5000);

		System.out.println("TCP Server waiting for client on port 5000!");

		while (true) {
			Socket connected = server.accept();
			System.out.println(
					" The Client: " + connected.getInetAddress() + ":" + connected.getPort() + " is connected!");

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connected.getInputStream()));

			while (true) {
				fromclient = inFromClient.readLine();

				if (fromclient.equals("q") || fromclient.equals("Q")) {
					connected.close();
					break;
				} else {
					System.out.println("RFID: " + fromclient);
					handleRequest(fromclient);
				}
			}
		}
	}

	private void handleRequest(String productNumber) throws ClientProtocolException, IOException {
		User user = userService.findByUsername("admin");
		Cart cart = cartService.findByUser(user);
		Product product = productService.findByProductNumber(productNumber);
		if (product == null || cart==null) {
			return;
		}
		cart.addProduct(product);
		cartService.save(cart);
		yaasServices.addItemToCart(product, cart.getYaasId());
	}

}
