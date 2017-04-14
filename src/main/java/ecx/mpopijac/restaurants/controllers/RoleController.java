package ecx.mpopijac.restaurants.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ecx.mpopijac.restaurants.models.Operation;
import ecx.mpopijac.restaurants.models.Role;
import ecx.mpopijac.restaurants.service.RoleService;

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
		model.addAttribute("operation", Operation.CREATE);
		return "cu-role";
	}

	@RequestMapping(value = "/u-role", method = RequestMethod.GET)
	public String updateRolePage(HttpServletRequest request, Model model) {
		model.addAttribute("heading", "Update role");
		model.addAttribute("buttonAction", "Update");
		int roleId = Integer.parseInt(request.getParameter("id"));
		Role role = roleService.findById(roleId);
		model.addAttribute("role", role);
		model.addAttribute("operation", Operation.UPDATE);
		return "cu-role";
	}

	// Fetch data and delete/create/update role
	@RequestMapping(value = "/crud-role", method = RequestMethod.POST)
	public String addCreateRolePage(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		Operation operation = Operation.valueOf(request.getParameter("operation"));
		switch (operation) {
		case DELETE: {
			Role role = new Role();
			role.setId(Integer.parseInt(request.getParameter("id")));
			roleService.delete(role);
		}
		case CREATE: {
			if (name != null && !name.equals("")) {
				Role role = new Role();
				role.setName(name);
				roleService.save(role);
			}
		}
		case UPDATE: {
			if (name != null && !name.equals("")) {
				Role role = new Role();
				role.setName(name);
				role.setId(Integer.parseInt(request.getParameter("id")));
				roleService.update(role);
			}
		}
		}
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "crud-role";
	}

}
