package example.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private Boolean isDefault;
    private final OffsetDateTime creationDate;
    private Boolean overdraftEnabled;

    public Bill() {
        creationDate = OffsetDateTime.now();
    }
}
