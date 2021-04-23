package com.example.demo.controler;

import com.example.demo.model.User;
import com.example.demo.model.UserProfile;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {
    @Autowired
    private UserService userservice= new UserService();
    //get request to login and registration
    @RequestMapping(method = RequestMethod.GET,value = "/users/login")
    public String login(Model model)
    {
        model.addAttribute("user",new User());
        return "users/login";
    }
    @RequestMapping(method = RequestMethod.GET,value = "/users/registration")
    public String registration(Model model)
    {
        User user =new User();
        UserProfile userProfile=new UserProfile();
        user.setUserProfile(userProfile);
        model.addAttribute("user",user);
        return "users/registration";
    }
    //post request to "/user/login"
    @RequestMapping(method = RequestMethod.POST,value = "/users/login")
    public String loginUser(User user , HttpSession session) {

        User existingUser = userservice.login(user);

        if (existingUser != null) {
            //Maintain the session
            session.setAttribute("LoggedUser" , existingUser);
            System.out.println("User Found");
            return "redirect:/index";
        } else {
            System.out.println("User Does Not Exist");
            return "users/login";
        }
    }
    @RequestMapping(method = RequestMethod.POST,value="/users/registration")
    public String userRegistration(User user){
        userservice.registerUser(user);
        return "redirect:/users/login";
    }

    @RequestMapping("/users/logout")
    public String userLogout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/";
    }
}
