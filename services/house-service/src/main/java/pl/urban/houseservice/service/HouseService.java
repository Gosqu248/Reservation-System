package pl.urban.houseservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.urban.houseservice.repository.HouseRepository;
import pl.urban.houseservice.request.HouseRequest;
import pl.urban.houseservice.response.HouseResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository repository;
    private final HouseMapper mapper;
    public Long createHouse(HouseRequest request) {
        var house = mapper.toHouse(request);
        return repository.save(house).getId();
    }

    public HouseResponse findById(Long houseId) {
        return repository.findById(houseId)
                .map(mapper::toHouseResponse)
                .orElseThrow(() -> new EntityNotFoundException("House not found with id: " + houseId));
    }

    public List<HouseResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toHouseResponse)
                .collect(Collectors.toList());
    }
}
