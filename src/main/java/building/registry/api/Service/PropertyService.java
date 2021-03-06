package building.registry.api.Service;

import building.registry.api.Model.Property;
import building.registry.api.Repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    PropertyRepo propertyRepo;

    public Property saveProperty(Property property){
        return propertyRepo.save(property);
    }

    public Property getPropertyById(int id) {
        return propertyRepo.findById(id).orElse(null);
    }

    public List<Property> getPropertyByOwner(String owner) {
        return propertyRepo.findByOwner(owner);
    }

    public boolean removeProperty(int id) {
        propertyRepo.deleteById(id);
        return true;
    }

}
