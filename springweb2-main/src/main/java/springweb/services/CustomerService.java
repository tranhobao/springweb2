package springweb.services;

import org.springframework.beans.BeanUtils;
import springweb.dto.CustomerDto;
import springweb.requests.Login;
import springweb.entity.Customer;
import springweb.repository.CustomerRepository;


public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Chức năng login (chức năng 1: Đăng nhập)
     * Login -> check db customer with object login (full name vs password)
     * @param login
     * @return boolean
     */
    public CustomerDto checkLogin(final Login login) {
        Customer customer = customerRepository.findByFullNameAndPassword(login.getFullName(), login.getPassword()).orElse(null);
        if (customer == null) {
            return null;
        }
        CustomerDto customerDto = CustomerDto.builder().build();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }

    /**
     * Chức năng đăng ký (chức năng 2: Đăng ký)
     * Add 1 customer
     * @param customerDto
     * @return CustomerDto
     */
    public CustomerDto save(CustomerDto customerDto) {
        CustomerDto check = checkLogin(customerDto);
        if (check == null) {
            Customer customer = Customer.builder().build();
            BeanUtils.copyProperties(customerDto, customer);
            Customer saved = customerRepository.save(customer);
            BeanUtils.copyProperties(saved, customerDto);
            return customerDto;
        }
        return null;
    }

    public CustomerDto findById(Integer id) {
        Customer customer = customerRepository.findById(id).get();
        CustomerDto customerDto = CustomerDto.builder().build();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }
}
