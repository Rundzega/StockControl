package com.stockcontrol.app.domain.stock;

import com.stockcontrol.app.dto.RequestExchangeDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    @Size(min = 4, max = 4)
    @NotBlank
    private String mic;

    @Column
    @Size(max = 60)
    @NotBlank
    private String country;

    @Column
    @Size(min = 3, max = 3)
    @NotBlank
    private String currency;

    @Column
    @NotBlank
    private String name;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Exchange(RequestExchangeDTO data) {
        mic = data.mic();
        country = data.country();
        name = data.name();
        currency = data.currency();
    }
}
