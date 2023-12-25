package com.stockcontrol.app.domain.account;

import com.stockcontrol.app.domain.operation.Operation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    @Email
    @NotBlank
    private String email;

    @Column
    @NotBlank
    private String provider;

    @Column
    @NotBlank
    private String providerId;

    @JoinColumn()
    @ManyToOne()
    private Role role;

    @OneToMany(mappedBy = "account")
    List<Operation> operations;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
