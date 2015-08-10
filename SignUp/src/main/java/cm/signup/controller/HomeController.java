package cm.signup.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.signup.service.ISignUpService;
import com.signup.vo.User;
import com.user.mail.Mail;

@Controller
public class HomeController {

	@Autowired
	ISignUpService signUpService;

	public void setSignUpService(ISignUpService signUpService) {
		this.signUpService = signUpService;
	}

	@RequestMapping("/home")
	public ModelAndView showMessage() {
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}

	User objectUser = new User();

	@RequestMapping(value = "/addnewuser", method = RequestMethod.POST)
	public ModelAndView addNewProduct(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("eid") String emailId) {
		ModelAndView mv = new ModelAndView("mail");
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();

		objectUser.setFirstName(firstName);
		objectUser.setLastName(lastName);
		objectUser.setEmailId(emailId);

		objectUser.setAccountStatus("1");

		objectUser.setToken(randomUUIDString);
		objectUser.setTokenExpiry("default");
		boolean userDetail = signUpService.addNewUser(objectUser);
		Mail objectMail = new Mail();
		boolean status = objectMail.sendMail(emailId, objectUser.getToken());
		if (status)
			System.out.println("Mail Sent!!");
		System.out.println(userDetail);
		mv.addObject("uList", userDetail);
		return mv;
	}

	/*
	 * @RequestMapping("/userdetail") public ModelAndView verification() {
	 * ModelAndView mv = new ModelAndView("userdetail"); return mv; }
	 */

	@RequestMapping(value = "/serviceverification/{token}", method = RequestMethod.POST)
	public ModelAndView signUpVerification(@PathVariable String token,
			@RequestParam("dob") String dateOfBirth,
			@RequestParam("pass") String password,
			
			@RequestParam("img") String image,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("signup");
		HttpSession session = request.getSession();
		
		User objectUsers =(User) session.getAttribute(token);

		objectUsers.setDateOfBirth(dateOfBirth);
		objectUsers.setPassword(password);
		objectUsers.setImage(image);
		objectUsers.setAccountStatus("2");
		//objectUsers.setToken(token);
		
		System.out.println("token:" + objectUsers.getToken());
		System.out.println("date of birth"+objectUsers.getDateOfBirth());
		System.out.println("controllerrrrrr");
		
			System.out.println("controllerrrrrr iiiifffff");
			boolean userDetails = signUpService.updateUser(objectUsers);
			System.out.println("controllerrrrrr after service");
			if (userDetails)
				System.out.println("Added Whole Data!!");
		return mv;
	}

	@RequestMapping(value = "/serviceverification/{token}", method = RequestMethod.GET)
	public ModelAndView signUpVerification(@PathVariable String token,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("Whasssss upppppp");
		ModelAndView mv = new ModelAndView("userdetail");
		mv.addObject("token",token);
		User checkUserValidation = new User();
		User userObject = new User();
		System.out.println("Token :" + checkUserValidation.getToken());
		checkUserValidation.setToken(token);
		System.out.println("token set");
		System.out.println("Token :" + checkUserValidation.getToken());
		userObject = signUpService.checkValidation(checkUserValidation);
		System.out.println("after checking");
		System.out.println("Token :" + userObject.getToken());
		HttpSession session = request.getSession();
		session.setAttribute(checkUserValidation.getToken(), userObject);
		System.out.println("session Completes");
		if (userObject != null) {

			System.out.println("Yes...Its Exist");
		}

		return mv;
	}

}
