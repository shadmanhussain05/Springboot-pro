package Transaction.example.SpringbootTransaction.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Transaction.example.SpringbootTransaction.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}
