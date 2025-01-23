package Transaction.example.SpringbootTransaction.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import Transaction.example.SpringbootTransaction.entity.AuditLog;
import Transaction.example.SpringbootTransaction.entity.Order;
import java.time.LocalDateTime;

@Service
public class PaymentValidatorHandler {

    @Autowired
    private JpaRepository auditLogRepository;

  @Transactional(propagation = Propagation.NESTED)
    public void validatePayment(Order order) {
        // Assume payment processing happens here
        boolean paymentSuccessful = false;

        // If payment is unsuccessful, we log the payment failure in the mandatory transaction
        if (!paymentSuccessful) {
            AuditLog paymentFailureLog = new AuditLog();
            paymentFailureLog.setOrderId(Long.valueOf(order.getId()));
            paymentFailureLog.setAction("Payment Failed for Order");
            paymentFailureLog.setTimestamp(LocalDateTime.now());

            if(order.getTotalprice()>1000){
                throw new RuntimeException("Error in payment validator");
            }
            // Save the payment failure log
            auditLogRepository.save(paymentFailureLog);
        }
        
    }

}