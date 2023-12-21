package com.stockcontrol.app.domain.operation;

import java.time.LocalDateTime;

import com.stockcontrol.app.dto.RequestOperationTypeDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.validator.constraints.Length;

/**
 * OperationType
 */
@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(of = "description")
@Getter
@Setter
public class OperationType {
    @Id
    @Length(max = 20)
    @NotBlank
    private String description;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public OperationType(RequestOperationTypeDTO data) {
        this.description = data.description();
    }
}
