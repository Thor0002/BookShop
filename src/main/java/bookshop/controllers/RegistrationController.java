package bookshop.controllers;

import bookshop.entities.User;
import bookshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @GetMapping("/signup")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user,
                          BindingResult bindingResult,
                          Model model,
                          HttpServletRequest request) throws ServletException {

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (!userService.saveUser(user)) {
            model.addAttribute("usernameError",
                    "Пользователь с таким именем уже существует");
            return "signup";
        }


        String username = user.getLogin();
        String password = user.getPassword();
        request.login(username, password);

        userService.saveUser(user);
        model.addAttribute("newUser", true);
        model.addAttribute("user", user);
        return "redirect:/";
    }
}
