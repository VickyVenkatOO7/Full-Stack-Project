package com.tri.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tri.model.Address;
import com.tri.model.Cart;
import com.tri.model.CartItem;
import com.tri.model.Order;
import com.tri.model.OrderItem;
import com.tri.model.Restaurant;
import com.tri.model.User;
import com.tri.repository.AddressRepository;
import com.tri.repository.OrderItemRepository;
import com.tri.repository.OrderRepository;
import com.tri.repository.RestaurantRepository;
import com.tri.repository.UserRepository;
import com.tri.request.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		
		Address shippAddress = order.getDeliveryAddress();
		
		Address savedAddress = addressRepository.save(shippAddress);
		
		if (!user.getAddresses().contains(savedAddress)) {
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
		}
		
		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
		
		Order createdOrder = new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreatedAt((java.sql.Date) new Date());
		createdOrder.setOrderStatus("Pending");
		createdOrder.setDeliveryAddress(savedAddress);
		createdOrder.setRestaurant(restaurant);
		
		Cart cart = cartService.findCartByUserId(user.getId());
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		for (CartItem cartItem : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIngredients(cartItem.getIngredients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			
			OrderItem savedOrderItem = orderItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}
		
		Long taotalPrice = cartService.calculateCartTotal(cart); 
		
		createdOrder.setItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotal());
		
		Order savedOrder = orderRepository.save(createdOrder);
		restaurant.getOrders().add(savedOrder);
		
		return createdOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		Order order = findOrderById(orderId);
		if (orderStatus.equals("OUT_FOR_DELIVERY") 
				|| orderStatus.equals("DELIVERED") 
				|| orderStatus.equals("COMPLETED")
				|| orderStatus.equals("PENDING")
		){
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		throw new Exception("Please Select a valid order status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		return orderRepository.findByRestaurantId(userId);
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
		if (orderStatus != null) {
			orders = orders.stream().filter(order ->
					 order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
		}
		return orders;
	}

	@Override
	public Order findOrderById(Long orderId) throws Exception {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if (optionalOrder.isEmpty()) {
			throw new Exception("Order not Found.");
		}
		return optionalOrder.get();
	}

}
