package Transaction.example.SpringbootTransaction.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Transaction.example.SpringbootTransaction.entity.Product;

public interface InventoryRepository extends JpaRepository<Product, Integer> {
	

}
