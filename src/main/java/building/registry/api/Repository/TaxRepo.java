package building.registry.api.Repository;

import building.registry.api.Model.Tax;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaxRepo extends CrudRepository<Tax, Integer> {

    String table = "tax";

    //Queries
    String getTaxValue = "SELECT * FROM "+ table +" WHERE type=?;";
    String removeTax = "DELETE FROM "+ table +" WHERE type=?;";

    //Query execution
    @Query(nativeQuery = true, value = getTaxValue)
    Tax getTaxValue (String type);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = removeTax)
    void removeTax (String type);
}