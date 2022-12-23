package springweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springweb.dto.CustomerDto;
import springweb.dto.OrderDto;
import springweb.dto.VegetableDto;
import springweb.requests.CreateOrder;
import springweb.requests.ProductOrder;
import springweb.services.OrderedService;
import springweb.services.VegetableService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("v1/shop/orders")
public class OrderController {
    private OrderedService service;
    private VegetableService vegetableService;
    private static List<ProductOrder> orderList;
    private static Double totalAllOrderList;

    public OrderController(OrderedService service, VegetableService vegetableService) {
        this.service = service;
        this.vegetableService = vegetableService;
    }

    public static void createOrderList() {
        orderList = new ArrayList<>();
        totalAllOrderList = 0D;
    }

    @GetMapping("")
    public String orderHome(Model model) {
        if (LoginController.getLogin() == null) {
            return "LoginToOrder";
        }
        model.addAttribute("orderList", orderList);
        model.addAttribute("total", totalAllOrderList);
        return "Order";
    }

    @PostMapping("")
    public String order(Model model) {
        if (LoginController.getLogin() == null) {
            return "LoginToOrder";
        }
        if (orderList == null || orderList.isEmpty()) {
            return "OrderFail";
        }
        CreateOrder createOrder = CreateOrder.builder()
                .id(LoginController.getLogin().getId())
                .orderList(orderList)
                .build();
        log.info(createOrder.toString());
        createOrder.setNote("");
        OrderDto orderDto = service.createdOrder(createOrder);
        model.addAttribute("customer", LoginController.getLogin());
        model.addAttribute("orderedList", orderDto.getOrderDetailDtoList());
        model.addAttribute("orderedTotal", orderDto.getTotal());
        OrderController.createOrderList();
        return "BillOrder";
    }

    @PostMapping("/vegetableId/{vegetableId}")
    public String addProductOrder(@PathVariable Integer vegetableId, @ModelAttribute("quantity") Integer quantity) {
        if (LoginController.getLogin() != null) {
            ProductOrder productOrder = orderList.stream()
                    .filter(p -> p.getId() == vegetableId)
                    .findAny()
                    .orElse(ProductOrder.builder().build());
            if (productOrder.getId() == null) {
                VegetableDto vegetableDto = vegetableService.findById(vegetableId);
                BeanUtils.copyProperties(vegetableDto, productOrder);
                productOrder.setQuantity(quantity);
                Double total = vegetableDto.getPrice() * quantity;
                productOrder.setPrice(total);
                totalAllOrderList = totalAllOrderList + total;
                orderList.add(productOrder);
            } else {
                int totalQuantity = productOrder.getQuantity() + quantity;
                Double total = (productOrder.getPrice() / productOrder.getQuantity()) * totalQuantity;
                totalAllOrderList = totalAllOrderList - productOrder.getPrice() + total;
                productOrder.setPrice(total);
                productOrder.setQuantity(totalQuantity);

            }
            return "redirect:/v1/shop";
        }
        return "LoginToOrder";
    }

    @GetMapping("/{id}")
    public String removeProductOrder(@PathVariable Integer id) {
        if (LoginController.getLogin() == null) {
            return "LoginToOrder";
        }
        log.info(String.format("id remove %s", id));
        ProductOrder productOrder = orderList.stream()
                .filter(p -> p.getId()==id)
                .findAny()
                .get();
        totalAllOrderList = totalAllOrderList - productOrder.getPrice();
        orderList.remove(productOrder);
        return "redirect:/v1/shop/orders";
    }

}
