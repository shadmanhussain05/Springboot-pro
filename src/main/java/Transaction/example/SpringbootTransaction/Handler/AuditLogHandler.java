package Transaction.example.SpringbootTransaction.Handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Transaction.example.SpringbootTransaction.Repository.AuditLogRepository;
import Transaction.example.SpringbootTransaction.entity.AuditLog;
import Transaction.example.SpringbootTransaction.entity.Order;

import java.time.LocalDateTime;

@Component
public class AuditLogHandler {

    @Autowired
    private AuditLogRepository auditLogRepository;

    // Log audit details (runs in an independent transaction)
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logAuditDetails(Order order, String action) {
        AuditLog auditLog = new AuditLog();
        auditLog.setOrderId(Long.valueOf(order.getId()));
        auditLog.setAction(action);
        auditLog.setTimestamp(LocalDateTime.now());

        // Save the audit log
        auditLogRepository.save(auditLog);
    }
}
