package building.registry.api.Repository;

import building.registry.api.Model.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepo  extends CrudRepository<Property, Integer> {

    String table = "Property";

    //Queries
    String getProperties = "SELECT * FROM "+ table +" WHERE owner=?;";

    //Query execution
    @Query(nativeQuery = true, value = getProperties)
    List<Property> findByOwner(String owner);

}
