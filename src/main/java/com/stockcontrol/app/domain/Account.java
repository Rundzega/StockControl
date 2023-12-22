package com.stockcontrol.app.domain;

import com.stockcontrol.app.dto.RequestAccountDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    @NotBlank
    @Size(max = 60)
    private String firstName;

    @Column
    @NotBlank
    @Size(max = 60)
    private String lastName;

    @Column
    @Email
    @NotBlank
    private String email;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Account(RequestAccountDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.email = data.email();
    }
}
