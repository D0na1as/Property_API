package building.registry.api.Repository;

import building.registry.api.Model.Tax;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRepo extends CrudRepository<Tax, Integer> {

    String table = "tax";

    //Queries
    String getTaxValue = "SELECT value FROM "+ table +" WHERE type=?;";
    String removeTax = "DELETE FROM "+ table +" WHERE type=?;";

    //Query execution
    @Query(nativeQuery = true, value = getTaxValue)
    double getTaxValue (String type);

    @Query(nativeQuery = true, value = getTaxValue)
    double removeTax (String type);
}