package online.proyi.o2o3.dao.repository;

import online.proyi.o2o3.entity.ShopAuthMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopAuthMapRepository extends JpaRepository<ShopAuthMap, Integer> {
}
