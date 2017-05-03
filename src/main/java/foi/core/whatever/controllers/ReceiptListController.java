package foi.core.whatever.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.model.Receipt;
import foi.core.whatever.model.User;
import foi.core.whatever.services.ReceiptService;
import foi.core.whatever.services.UserService;

@Controller
public class ReceiptListController {

	@Autowired
	private ReceiptService receiptService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/receipt-list", method = RequestMethod.GET)
	public String receiptListPage(HttpServletRequest request, Model model) throws Exception{

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);

		List<Receipt> receipts = receiptService.findByUser(user);

		model.addAttribute("receipts", receipts);

		return "receipt-list";	
	}

	@RequestMapping(value = "/admin/receipt-list", method = RequestMethod.GET)
	public String receiptListPageAdmin(HttpServletRequest request, Model model) throws Exception {
		return receiptListPage(request, model);
	}

}
