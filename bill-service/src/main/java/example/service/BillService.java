package example.service;

import example.entity.Bill;

import java.math.BigDecimal;
import java.util.List;

public interface BillService {
    Bill getBillById(Long id);

    Long createBill(Bill bill);

    Bill updateBill(Long billId, Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnabled);

    Bill deleteBill(Long id);

    List<Bill> getBillsByAccountId(Long accountId);
}
