package Transaction.example.SpringbootTransaction.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Transaction.example.SpringbootTransaction.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Integer> {
	

}
