package TZJanosi.usedCars.repository;

import TZJanosi.usedCars.model.KilometerState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KilometerStateRepository extends JpaRepository<KilometerState,Long> {
}
