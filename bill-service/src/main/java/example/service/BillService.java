package example.service;

import example.entity.Bill;

import java.math.BigDecimal;

public interface BillService {
    Bill getBillById(Long id);

    Long createBill(Bill bill);

    Bill updateBill(Long billId, Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnabled);

    Bill deleteBill(Long id);
}
