package Transaction.example.SpringbootTransaction.Handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import Transaction.example.SpringbootTransaction.Repository.InventoryRepository;
import Transaction.example.SpringbootTransaction.entity.Product;

@Service
public class InventoryHandler {

	private final InventoryRepository inventoryRepository;

	public InventoryHandler(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Product updatProductDetails(Product product) {

		if (product.getPrice() > 500) {
			throw new RuntimeException("Db Crashed");
		}
		return inventoryRepository.save(product);

	}

	public Product getProduct(int id) {
		return inventoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not available with id : " + id));
	}
}
