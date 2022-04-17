package online.proyi.o2o3.dao.repository;

import online.proyi.o2o3.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Page<Shop> findList(Shop shopParam, Pageable pageable);
}
