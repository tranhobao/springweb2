package springweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springweb.dto.CustomerDto;
import springweb.services.CustomerService;

@Slf4j
@Controller
@RequestMapping("v1/shop/customers")
public class CustomerController {
    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public String register(Model m)
    {
        if (LoginController.getLogin() != null) {
            return "redirect:/v1/shop/customers/"+LoginController.getLogin().getId();
        }
        CustomerDto customerDto = new CustomerDto();
        m.addAttribute("customer", customerDto);
        return "CustomerRegister";
    }

    @PostMapping("/register")
    public String save(Model model, @ModelAttribute("customer") CustomerDto customerDto)
    {
        log.info(customerDto.toString());
        CustomerDto response = service.save(customerDto);
        if (response != null && response.getId() != null) {
            return RegisterSuccess(model, response);
        }
        return "RegisterFail";
    }

    private String RegisterSuccess(Model model, CustomerDto customerDto) {
        model.addAttribute("data", customerDto);
        return "RegisterSuccess";
    }

    @GetMapping("/{id}")
    public String findOne(Model model, @PathVariable Integer id) {
        if (LoginController.getLogin() == null) {
            return "LoginToOrder";
        }
        CustomerDto customerDto = service.findById(id);
        return RegisterSuccess(model, customerDto);
    }


    /*
    @PostMapping("/customer/update")
    public String update(Model model, @ModelAttribute("customer") Customer customer)
    {
        Optional<Customer> cus = customersReposity.findById(customer.getCustomerID());
        Customer c;
        c = cus.get();
        c.setAddress(customer.getAddress());
        c.setCity(customer.getCity());
        c.setFullname(customer.getFullname());
        customersReposity.save(c);
        
        return "redirect:/customer/all";
        
    }
    @GetMapping("/customer/all")
    public String showAllCustomer(Model m)
    {
        Iterable<Customer> list = customersReposity.findAll();
        m.addAttribute("data", list);
        return "customer_all";
        
    }
    @GetMapping(value = {"customer/edit/{id}"})
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Customer> cus = customersReposity.findById(id);
        cus.ifPresent(customer->model.addAttribute("customer", cus));
        //model.addAttribute("customer", cus);
        return "customer_edit";
    }
     */
}
