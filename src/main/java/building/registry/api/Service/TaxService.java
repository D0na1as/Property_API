package building.registry.api.Service;

import building.registry.api.Model.Property;
import building.registry.api.Model.Tax;
import building.registry.api.Repository.PropertyRepo;
import building.registry.api.Repository.TaxRepo;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.spi.ServiceRegistry;
import java.util.List;

@Service
public class TaxService {

    @Autowired
    TaxRepo taxRepo;
    @Autowired
    PropertyService propertyService;

    public Tax saveTax(Tax tax) {
        return taxRepo.save(tax);

    }

    public Tax getTax(String type) {
        return taxRepo.getTaxValue(type);
    }

    public void removeTax(String type) {
        taxRepo.removeTax(type);
    }

    public List<Tax> getAllTaxes() {
        return (List<Tax>) taxRepo.findAll();
    }

    public double getBill(String owner) {
        List<Property> properties = propertyService.getPropertyByOwner(owner);
        double sum = 0;
        for (Property property : properties) {
            sum += property.getValue() * taxRepo.getTaxValue(property.getType()).getValue() / 100;
        }
        return sum;
    }
}
