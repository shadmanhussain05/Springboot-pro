package Transaction.example.SpringbootTransaction.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import Transaction.example.SpringbootTransaction.Handler.AuditLogHandler;
import Transaction.example.SpringbootTransaction.Handler.InventoryHandler;
import Transaction.example.SpringbootTransaction.Handler.NotificationHandler;
import Transaction.example.SpringbootTransaction.Handler.OrderHandler;
import Transaction.example.SpringbootTransaction.Handler.PaymentValidatorHandler;
import Transaction.example.SpringbootTransaction.Handler.ProductRecommendationHandler;
import Transaction.example.SpringbootTransaction.entity.Order;
import Transaction.example.SpringbootTransaction.entity.Product;

@Service
public class OrderProcessingService {

	private final OrderHandler orderHandler;
	private final InventoryHandler inventoryHandler;
	private final AuditLogHandler auditLogHandler;
	private final ProductRecommendationHandler recommendationHandler;
	private final PaymentValidatorHandler paymentValidatorHandler;

	private final NotificationHandler notificationHandler;

	public OrderProcessingService(OrderHandler OrderHandler, InventoryHandler inventoryHandler,
			AuditLogHandler auditLogHandler, PaymentValidatorHandler paymentValidatorHandler,
			NotificationHandler notificationHandler, ProductRecommendationHandler recommendationHandler) {
		this.orderHandler = OrderHandler;
		this.inventoryHandler = inventoryHandler;
		this.auditLogHandler = auditLogHandler;
		this.paymentValidatorHandler = paymentValidatorHandler;
		this.notificationHandler = notificationHandler;
		this.recommendationHandler = recommendationHandler;
	}

	// REQUIRED : join an existing transaction or create a new one if not exist
	// REQUIRES_NEW : Always create a new transaction , suspending if any existing
	// transaction
	// MANDATORY : Require an existing transaction , if nothing found it will throw
	// exception
	// NEVER : Ensure the method will run without transaction , throw an exception
	// if found any
	// NOT_SUPPORTED : Execute method without transaction, suspending any active
	// transaction
	// SUPPORTS : Supports if there is any active transaction , if not execute
	// without transaction
	// NESTED : Execute within a nested transaction, allowing nested transaction
	// to rollback independently if there is any exception without impacting outer
	// transaction

	// outer tx
	@Transactional(propagation = Propagation.REQUIRED)
	public Order placeAnOrder(Order Order) {

		// get product inventory
		Product product = inventoryHandler.getProduct(Order.getProductId());

		// validate stock availability <(5)
		validateStockAvailability(Order, product);
		if (Order.getQuatity() > product.getStockquantity()) {
			throw new RuntimeException("Insufficient Stock !");
		}

		// update total price in Order entity
		Order.setTotalprice(Order.getQuatity() * product.getPrice());

		Order saveOrder = null; // OrderHandler.SaveOrder(Order);
		try {
			// save Order

			saveOrder = OrderHandler.saveOrder(order);

			// update stock in inventory
			updateInventoryStock(Order, product);
			auditLogHandler.logAuditDetails(Order, "Order placement succeeded");
		} catch (Exception ex) {
			auditLogHandler.logAuditDetails(Order, "Order placement failed");
		}

		// retries 3
		// notificationHandler.sendOrderConfirmationNotification(Order);

		paymentValidatorHandler.validatePayment(Order);

		// recommendationHandler.getRecommendations();

//        getCustomerDetails();

		return saveOrder;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void getCustomerDetails() {
		System.out.println("Customer details fetched !!!!!");
	}

	// Call this method after placeAnOrder is successfully completed
	public void processOrder(Order Order) {
		// Step 1: Place the Order
		Order savedOrder = placeAnOrder(Order);

		// Step 2: Send notification (non-transactional)
		notificationHandler.sendOrderConfirmationNotification(Order);
	}

	private static void validateStockAvailability(Order Order, Product product) {
		if (Order.getQuatity() > product.getStockquantity()) {
			throw new RuntimeException("Insufficient stock !");
		}
	}

	private void updateInventoryStock(Order Order, Product product) {
		int availableStock = product.getStockquantity() - Order.getQuatity();
		product.setStockquantity(availableStock);
		inventoryHandler.updatProductDetails(product);
	}

}
