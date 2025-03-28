package pl.urban.houseservice.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.urban.houseservice.enums.HouseType;
import pl.urban.houseservice.enums.LocationType;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private HouseType type;
    private Integer capacity;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @ElementCollection
    @CollectionTable(name = "house_amenities", joinColumns = @JoinColumn(name = "house_id"))
    @Column(name = "amenity")
    private List<String> amenities;
}
