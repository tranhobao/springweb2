package springweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springweb.dto.CustomerDto;
import springweb.requests.Login;
import springweb.services.CustomerService;

@Slf4j
@Controller
@RequestMapping("v1/shop")
public class LoginController {
    private static CustomerDto customerLogin = null;
    private CustomerService service;

    public LoginController(CustomerService service) {
        this.service = service;
    }

    public static CustomerDto getLogin() {
        return customerLogin;
    }

    @GetMapping("/login")
    public String methodGetLogin() {
        if (customerLogin != null) {
            return String.format("redirect:/v1/shop/customers/%s", customerLogin.getId());
        }
        return "Login";
    }

    @GetMapping("/login/action")
    public String methodPostLogin(Model model, Login login) {
        model.addAttribute("fullName", login.getFullName());
        model.addAttribute("password", login.getPassword());
        log.info(login.toString());
        CustomerDto customerDto = service.checkLogin(login);
        if (customerDto != null) {
            customerLogin = customerDto;
            OrderController.createOrderList();
            return "redirect:/v1/shop/vegetables";
        }
        return "LoginFail";
    }

    @GetMapping("")
    public String shop() {
        return "redirect:/v1/shop/vegetables/";
    }

    @GetMapping("/logout")
    public String logout() {
        customerLogin = null;
        return "redirect:/v1/shop";
    }
}
