package com.example.login.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.dto.ListUser;
import com.example.login.model.File;
import com.example.login.model.Message;
import com.example.login.model.Role;
import com.example.login.model.Todolist;
import com.example.login.model.User;
import com.example.login.model.UserExcel;
import com.example.login.repository.FileRepository;
import com.example.login.repository.MessageRepository;
import com.example.login.repository.RoleRepository;
import com.example.login.repository.TodoRepository;
import com.example.login.repository.UserRepository;
import com.example.login.response.UserResponse;
import com.example.login.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	FileRepository fileRepository;

	@Autowired
	TodoRepository todoRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	PasswordEncoder encoder;
	
	Set<Role> roles = new HashSet<>();

	@PostMapping("/addUsers")
	public User addUser(@RequestBody User user) {
		Set<Role> roles = new HashSet<>();
		// on considère qu on a un seul admin dqui peut ajouter plusieur utilisateurs
		// Set<Role> roles2 = new HashSet<>();
		// roles2.add(roleRepository.findById(2).get());
		/*
		 * if() {
		 * 
		 * }
		 */
		if ("ROLE_ADMIN".equals(user.getRole())) {
			roles.add(roleRepository.findById(2).get());
		} else {
			roles.add(roleRepository.findById(1).get());
		}
		// user.setRoles(roles2);
		user.setRoles(roles);
		return userRepository.save(user);
	}
	
	//add a new todoList
	@PostMapping("/addTodo")
	public Todolist addTodo(@RequestBody Todolist todo) {
		return todoRepository.save(todo);
	}

	// update roles
	@PostMapping("/addAdminRole/{id}")
	public User addAdminRoleToUser(@PathVariable(name = "id") Long id) {
		User user = userRepository.findById(id).get();
		if (user.getRoles().size() == 1)
			user.getRoles().add(roleRepository.findById(2).get());
		else
			user.getRoles().remove(2);
		return userRepository.save(user);
	}

	@PostMapping("/addAllUsers")
	public List<User> addAllUsers(@RequestBody List<User> users) {
		return userRepository.saveAll(users);
	}
//dont use it
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(
			/* @RequestParam(name="file") MultipartFile userImage, */
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "roles", required = false) Role roles,
			@RequestParam(name = "password", required = false) String password) throws IOException {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setRoles((Set<Role>) roles);
		user.setPassword(password);
		userRepository.save(user);

		return ResponseEntity.ok(new UserResponse("User Saved Succeffuly", userRepository.findAll()));
	}

	@GetMapping("/getAllUserById/{id}")
	public User getAllUserById(@PathVariable(name = "id") long id) {
		return userRepository.findById(id);
	}

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// for show details
	@GetMapping("/getAllFileById/{id}")
	public File getAllFileById(@PathVariable(name = "id") long id) {
		return fileRepository.findById(id);
	}
	// list of files
		@GetMapping("/getAllFiles")
		public List<File> getAllFiles() {
			return fileRepository.findAll();
		}
	
	//list of todoList
	@GetMapping("/getAlltodoList")
	public List<Todolist> getAlltodoList() {
		return todoRepository.findAll();
	}
	// for show details
		@GetMapping("/getTodoListById/{id}")
		public Todolist getTodoListById(@PathVariable(name = "id") long id) {
			return todoRepository.findById(id);
		}
		
		//message
		@GetMapping("/getAllMessage")
		public List<Message> getAllMessage() {
			return messageRepository.findAll();
		}
		
		//for show details 
		@GetMapping("/getMessageById/{id}")
		public Message getMessageById(@PathVariable(name="id") long id) {
			return messageRepository.findById(id);
		}
		@DeleteMapping("/deleteMessage/{id}")
		public boolean deleteMessage(@PathVariable(name = "id") long id) {
			Message existingEMP = messageRepository.findById(id);
			if (existingEMP != null) {
				messageRepository.deleteById(id);
				return true;
			}
			return false;

		}

	@GetMapping("/getUsers")
	public ResponseEntity<?> getUsers() {
		return ResponseEntity.ok(new UserResponse("users are in the List Below", userRepository.findAll()));
	}

	@DeleteMapping("/delUser")
	@Transactional
	public ResponseEntity<?> delUser(@RequestParam(name = "id", required = false) int id) {
		userRepository.deleteById(id);
		return ResponseEntity
				.ok(new UserResponse("Your User with ID" + id + " deleted succeffuly", userRepository.findAll()));
	}

	@PutMapping("/updUsers/{id}")
	public User UpdateUser(@RequestBody User user, @PathVariable(name = "id") long id) {//Long =>long

		User existingEMP = userRepository.findById(id);//orElse(null)
		if (existingEMP == null) {
			System.out.println("Emp not found");
			return userRepository.save(user);
		} else {
			existingEMP.setUsername(user.getUsername());
			existingEMP.setEmail(user.getEmail());
			existingEMP.setPassword(user.getPassword());
			/*
			 * if(existingEMP.getRoles.size()==2) { //appel au fonction addRole }
			 */
			
			userRepository.save(existingEMP);
		}
		return existingEMP;
	}
	@PutMapping("/updTask/{id}")
	public Todolist UpdateTask(@RequestBody Todolist task, @PathVariable(name = "id") long id) {//Long =>long

		Todolist existingEMP = todoRepository.findById(id);//orelse(null)
		if (existingEMP == null) {
			System.out.println("Emp not found");
			return todoRepository.save(task);
		} else {
			existingEMP.setLabel(task.getLabel());
			existingEMP.setCompleted(task.isCompleted());
			
			/*
			 * if(existingEMP.getRoles.size()==2) { //appel au fonction addRole }
			 */
			todoRepository.save(existingEMP);
		}
		return existingEMP;
	}
	/*
	 * @DeleteMapping("/deleteAdminRole/{id}") public void deleteAdmin
	 */
	//dont use it
	@PutMapping("/updUser")
	public ResponseEntity<?> updUser(/* @RequestParam(name="file",required = false) MultipartFile userImage, */
			@RequestParam(name = "username") String username, @RequestParam(name = "email") String email,
			@RequestParam(name = "roles") Role roles, @RequestParam(name = "password") String password,
			@RequestParam(name = "id") int id) throws IOException {
		User u1 = userRepository.findById(id);
		// User p2=userRepository.findByUserid(id);
		// if(userImage!=null){
		u1.setUsername(username);
		u1.setEmail(email);
		u1.setRoles((Set<Role>) roles);
		u1.setPassword(password);

		// else{

		// }
		userRepository.save(u1);
		return ResponseEntity.ok(new UserResponse("Your User with ID " + id + " updated succeffuly", u1));
	}
	//delete a task 
	@DeleteMapping("/deleteTodo/{id}")
	public boolean deleteTodo(@PathVariable(name = "id") long id) {
		Todolist existingEMP = todoRepository.findById(id);
		if (existingEMP != null) {
			todoRepository.deleteById(id);
			return true;
		}
		return false;

	}
//delete user
	@DeleteMapping("/delete/{id}")
	public boolean deleteUser(@PathVariable(name = "id") long id) {
		User existingEMP = userRepository.findById(id);
		if (existingEMP != null) {
			userRepository.deleteById(id);
			return true;
		}
		return false;

	}
	@GetMapping("/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException{
		//System.out.println("export to Excel ...");
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime= dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename-users_"+currentDateTime+".xlsx";
		response.setHeader(headerKey, headerValue);
		List<User> listUsers = userService.listAll();
		UserExcel excel = new UserExcel(listUsers);
		excel.export(response);
	}
	@GetMapping("/count")
	@ResponseBody
	public long countUser() {
		long count = userService.getCountOfUsers();
		return count;
	}
	@GetMapping("/countByRole")
	@ResponseBody
	public long countUserByRole(User user) {
		long count = userService.getCountOfUserByRole(user);
		return count;
	}
	@GetMapping("/countVNOK")
	@ResponseBody
	public long countVNOK() {
		long count = userService.getValidName();
		return count;
	}
	
	@GetMapping("/countVNKO")
	@ResponseBody
	public long countVNKO() {
		long count = userService.getValidNameKO();
		return count;
	}
	@GetMapping("/countVDKO")
	@ResponseBody
	public long countVDKO() {
		long count = userService.getValidDateKO();
		return count;
	}
	@GetMapping("/countVDOK")
	@ResponseBody
	public long countVDOK() {
		long count = userService.getValidDateOK();
		return count;
	}
	@GetMapping("/countVMKO")
	@ResponseBody
	public long countVMKO() {
		long count = userService.getValidMontantKO();
		return count;
	}
	@GetMapping("/countVMOK")
	@ResponseBody
	public long countVMOK() {
		long count = userService.getValidMontantOK();
		return count;
	}
	@GetMapping("/countVCKO")
	@ResponseBody
	public long countVCKO() {
		long count = userService.getValidColumnKO();
		return count;
	}
	@GetMapping("/countVCOK")
	@ResponseBody
	public long countVCOK() {
		long count = userService.getValidColumnOK();
		return count;
	}
	@GetMapping("/countFileOK")
	@ResponseBody
	public long countFileOK() {
		long count = userService.getCountFileOk();
		return count;
	}
	
	
}

