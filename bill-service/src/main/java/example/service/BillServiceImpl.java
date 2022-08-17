package example.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import example.entity.Bill;
import example.exception.BillNoFoundException;
import example.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;

    @Transactional(readOnly = true)
    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElseThrow(() -> new BillNoFoundException("Unable to find bill with id: " + id));
    }

    @Transactional
    @Override
    public Long createBill(Bill bill) {
        return billRepository.save(bill).getAccountId();
    }

    @Transactional
    @Override
    public Bill updateBill(Long billId, Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnabled) {
        Bill bill = Bill.builder()
                .id(billId)
                .accountId(accountId)
                .amount(amount)
                .isDefault(isDefault)
                .overdraftEnabled(overdraftEnabled)
                .build();
        return billRepository.save(bill);
    }


    @Transactional
    @Override
    public Bill deleteBill(Long id) {
        Bill billByIdToDelete = getBillById(id);
        billRepository.deleteById(id);
        return billByIdToDelete;
    }

}
