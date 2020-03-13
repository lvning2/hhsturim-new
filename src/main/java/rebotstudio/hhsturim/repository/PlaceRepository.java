package rebotstudio.hhsturim.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rebotstudio.hhsturim.entity.Place;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Integer> {

    List<Place> findPlaceByType(Integer type);

    List<Place> findPlaceByPriceBetween(Float start,Float end);

    Page<Place> findByUid(Integer uid, Pageable pageable);

}
