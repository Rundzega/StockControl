package com.stockcontrol.app.domain.stock;

import com.stockcontrol.app.dto.RequestStockDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    @Size(max = 6)
    @NotBlank
    private String ticker;

    @Column
    @Size(max = 100)
    @NotBlank
    private String name;

    @NotNull
    @ManyToOne()
    @JoinColumn()
    private Exchange exchange;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Stock(RequestStockDTO data, String exchangeMic) {
        this.ticker = data.ticker();
        this.name = data.name();
        this.exchange = new Exchange();
        this.exchange.setMic(exchangeMic);
    }
}
