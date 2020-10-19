package pv826.web;

import pv826.entities.Message;
import pv826.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pv826.repositories.MessageRepository;
import pv826.repositories.UserRepository;

/**
 * @author Ramesh Fadatare
 *
 */
@Controller
public class HomeController
{
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@GetMapping("/home")
	public String home(Model model)
	{
		model.addAttribute("msgs", messageRepository.findAll());		
		return "userhome";
	}
	
	@PostMapping("/messages")
	public String saveMessage(Message message)
	{
		messageRepository.save(message);
		return "redirect:/home";
	}
}
