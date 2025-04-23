package pl.urban.houseservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.urban.houseservice.repository.HouseRepository;
import pl.urban.houseservice.response.HouseResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository repository;
    private final HouseMapper mapper;


    public HouseResponse findById(Long houseId) {
        return repository.findById(houseId)
                .map(mapper::toHouseResponse)
                .orElseThrow(() -> new EntityNotFoundException("House not found with id: " + houseId));
    }

    @Cacheable("houses")
    public List<HouseResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toHouseResponse)
                .collect(Collectors.toList());
    }
}
