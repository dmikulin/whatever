package foi.core.whatever.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import foi.core.whatever.model.Receipt;
import foi.core.whatever.model.ReceiptProducts;

@Component
public class ReceiptReport {

	public List<Map<String,?>> getReceiptData(List<Receipt> receipts){
		List<Map<String,?>> receiptData = new ArrayList<Map<String,?>>();
		for (Receipt receipt : receipts) {
			Map<String,Object> r = new HashMap<String,Object>();

			r.put("nameUser", receipt.getUser().getFirstName()+" "+receipt.getUser().getLastName());
			r.put("documentDate", receipt.getTimeString(receipt.getDocumentDate()));
			r.put("receiptNumber", receipt.getReceiptId());

			List<Map<String,?>> products = new ArrayList<Map<String,?>>();
			for (ReceiptProducts receiptProduct : receipt.getReceiptProducts()) {
				Map<String,Object> product = new HashMap<String,Object>();
				product.put("productNumber", receiptProduct.getProductId());
				product.put("productName", receiptProduct.getProductName());
				product.put("productPrice", receiptProduct.getPrice());
				product.put("quantity", receiptProduct.getQuantity());
				products.add(product);
			}
			r.put("products", products);

			receiptData.add(r);
		}

		return receiptData;
	}

}

