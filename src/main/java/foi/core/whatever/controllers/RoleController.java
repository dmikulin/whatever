package foi.core.whatever.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foi.core.whatever.model.Role;
import foi.core.whatever.services.RoleService;

@Controller
public class RoleController {

	@Autowired
	RoleService roleService;

	@RequestMapping(value = "/crud-role", method = RequestMethod.GET)
	public String rolePage(Model model) {
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "crud-role";
	}

	@RequestMapping(value = "/c-role", method = RequestMethod.GET)
	public String createRolePage(Model model) {
		model.addAttribute("heading", "Create role");
		model.addAttribute("buttonAction", "Create");
		model.addAttribute("role", new Role());
		return "cu-role";
	}

	@RequestMapping(value = "/u-role", method = RequestMethod.GET)
	public String updateRolePage(HttpServletRequest request, Model model) {
		model.addAttribute("heading", "Update role");
		model.addAttribute("buttonAction", "Update");
		int roleId = Integer.parseInt(request.getParameter("id"));
		Role role = roleService.findById(roleId);
		model.addAttribute("role", role);
		return "cu-role";
	}

	

}
