package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String name;
    private String email;
    private String phone;
    private final OffsetDateTime creationDate;
    @ElementCollection
    private List<Long> bills;

    public Account() {
        creationDate = OffsetDateTime.now();
    }
}
