package ugb.sat.madsi.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findByBrand(@Param("brand") String brand);

    List<Car> findByColor(String color);
    
    List<Car> findByBrandAndModel( String brand, String model);
     
     List<Car> findByBrandOrColor( String brand, String color);
   
     @Query("select c from Car c where c.model=?1")
   List<Car> findByModel(String model);
}
