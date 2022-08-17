package example.billService;

import example.entity.Bill;
import example.exception.BillNoFoundException;
import example.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;

    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new BillNoFoundException("Unable to find bill with id: " + id));
    }

    public Long createBill(Bill bill) {
        return billRepository.save(bill).getAccountId();
    }
}
