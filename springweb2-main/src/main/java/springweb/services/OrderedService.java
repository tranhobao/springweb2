package springweb.services;

import org.springframework.beans.BeanUtils;
import springweb.dto.OrderDetailDto;
import springweb.dto.OrderDto;
import springweb.entity.Ordered;
import springweb.entity.OrderedDetail;
import springweb.entity.Vegetable;
import springweb.repository.OrderedRepository;
import springweb.repository.VegetableRepository;
import springweb.requests.CreateOrder;
import springweb.requests.ProductOrder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderedService {

    private OrderedRepository orderedRepository;
    private VegetableRepository vegetableRepository;

    public OrderedService(OrderedRepository orderedRepository, VegetableRepository vegetableRepository) {
        this.orderedRepository = orderedRepository;
        this.vegetableRepository = vegetableRepository;
    }

    /**
     * Create ordered - (chức năng 3)
     * @param createOrder
     * @return OrderDto
     */
    public OrderDto createdOrder(CreateOrder createOrder) {
        Ordered ordered = toOrderedByCreateOrder(createOrder);
        if (ordered == null) {
            return null; // Input something not true || has id vegatable not exsits in local db
        }
        Ordered saved = orderedRepository.save(ordered);
        OrderDto orderDto = OrderDto.builder().build();
        BeanUtils.copyProperties(saved, orderDto);
        List<OrderDetailDto> orderDetailDtoList = toOrderedDetailList(saved.getOrderedDetails());
        if (orderDetailDtoList == null) {
            orderedRepository.delete(saved);
            return null; // Sql action save has some problem so data orderedDetail has problem
        }
        orderDto.setOrderDetailDtoList(orderDetailDtoList);
        updateVegetable(orderDetailDtoList);
        return orderDto;
    }

    /**
     * Update amount vegetable
     * @param orderDetailDtoList
     */
    private void updateVegetable(List<OrderDetailDto> orderDetailDtoList) {
        for (OrderDetailDto orderDetailDto : orderDetailDtoList) {
            Vegetable vegetable = vegetableRepository.findById(orderDetailDto.getVegetableId()).get();
            vegetable.setAmount(vegetable.getAmount()-orderDetailDto.getQuantity());
            vegetableRepository.save(vegetable);
        }
    }

    /**
     * Convert class CreateOrder to class entity Order to save local db
     * @param createOrder
     * @return Ordered
     */
    private Ordered toOrderedByCreateOrder(CreateOrder createOrder) {
        if (createOrder.validate() == false) {
            return null;
        }
        Ordered ordered = Ordered.builder().build();
        ordered.setCustomerId(createOrder.getId());
        ordered.setNote(createOrder.getNote());
        ordered.setDateCreated(toStringDate(new Date()));
        Set<OrderedDetail> orderedDetails = new HashSet<>();
        Double total = 0D;
        for (ProductOrder productOrder : createOrder.getOrderList()) {
            Vegetable vegetable = vegetableRepository.findById(productOrder.getId()).orElse(null);
            if (vegetable == null) {
                return null;
            }
            total = vegetable.getPrice()*productOrder.getQuantity() + total;
            orderedDetails.add(toOrderedDetail(vegetable, productOrder, ordered));
        }
        ordered.setTotal(total);
        ordered.setOrderedDetails(orderedDetails);
        return ordered;
    }

    /**
     * Convert class Date to class String (with my format date)
     * @param date
     * @return String
     */
    private String toStringDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    /**
     * Convert Class Vegetable, ProductOrder, Ordered  to Class entity OrderedDetail to save local db
     * @param vegetable
     * @param productOrder
     * @param ordered
     * @return OrderedDetail
     */
    private OrderedDetail toOrderedDetail(Vegetable vegetable, ProductOrder productOrder, Ordered ordered) {
        return OrderedDetail.builder()
            .ordered(ordered)
            .vegetableId(vegetable.getId())
            .price(vegetable.getPrice()*productOrder.getQuantity())
            .quantity(productOrder.getQuantity())
            .build();
    }

    /**
     * Convert Set<OrderedDetail> to List<OrderDetailDto> to return response
     * @param orderedDetails
     * @return List<OrderDetailDto>
     */
    private List<OrderDetailDto> toOrderedDetailList(Set<OrderedDetail> orderedDetails) {
        List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
        for (OrderedDetail orderedDetail : orderedDetails) {
            Vegetable vegetable = vegetableRepository.findById(orderedDetail.getVegetableId()).orElse(null);
            if (vegetable == null) {
                return null;
            }
            orderDetailDtoList.add(OrderDetailDto.builder()
                .id(orderedDetail.getId())
                .vegetableId(vegetable.getId())
                .vegetableName(vegetable.getName())
                .quantity(orderedDetail.getQuantity())
                .price(orderedDetail.getPrice())
                .build()
            );
        }
        return orderDetailDtoList;
    }
}
