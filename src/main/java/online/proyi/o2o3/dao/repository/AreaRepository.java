package online.proyi.o2o3.dao.repository;

import online.proyi.o2o3.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

    Optional<Area> findByName(@NotBlank String name);
}
