package com.stockcontrol.app.domain.account;

import com.stockcontrol.app.dto.RequestRoleDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Role {
    @Id
    @NotBlank
    @Size(max = 10)
    private String id;

    @Column
    @NotBlank
    @Size(max = 60)
    private String description;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt;

    public Role(RequestRoleDTO data) {
        this.id = data.id();
        this.description = data.description();
    }
}