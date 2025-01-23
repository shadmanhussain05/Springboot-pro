package Transaction.example.SpringbootTransaction.Handler;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import Transaction.example.SpringbootTransaction.Repository.OrderRepository;
import Transaction.example.SpringbootTransaction.entity.Order;

@Service
public class OrderHandler {
	
	private final OrderRepository orderRepository;
	
	public OrderHandler(OrderRepository orderRepository) {
		
		this.orderRepository = orderRepository;
		
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public Order saveOrder(Order order) {
		
		return orderRepository.save(order);
	}

}
