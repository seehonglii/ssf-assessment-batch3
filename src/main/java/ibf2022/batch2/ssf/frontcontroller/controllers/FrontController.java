package ibf2022.batch2.ssf.frontcontroller.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.batch2.ssf.frontcontroller.models.Login;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping
public class FrontController {

	@Autowired
	private  AuthenticationService aSvc;

	// TODO: Task 2, Task 3, Task 4, Task 6
	@GetMapping(path={"/", "/login.html"})
	public String getLogin(@RequestParam(required = true) String username, @RequestParam(required = true) String password, 
							Model model, HttpSession sess) throws IOException{
		Login login = new Login();
		Optional<Login> l = this.aSvc.getLogin(login);
		model.addAttribute("view1", l.get());
		return "view0";
	}

	@PostMapping("/authenticate")
	public String postAuthenticate (@RequestParam(required = true) String username, @RequestParam(required = true) String password, 
	Model model, HttpSession sess) throws IOException{
		if(aSvc.hasAuthenticated(username)){
			return "view0";
		}		
		return "view1";
	}

	@PostMapping("/disable")
	public String postDisable (@RequestParam(required = true) String username, @RequestParam(required = true) String password, 
	Model model) throws IOException{
		if (aSvc.hasDisabled(username)){
			return "view 2";
		}
	}

	
}
