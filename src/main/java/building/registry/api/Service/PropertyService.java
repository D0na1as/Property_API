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

    public boolean saveProperty(Property property) {
        propertyRepo.save(property);
        return true;
    }

    public Property getProperty(int id) {
        return propertyRepo.findById(id).orElse(null);
    }

    public boolean removeProperty(int id) {
        propertyRepo.deleteById(id);
        return true;
    }

    public List<Property> getPropertyByOwner(String owner) {
        return propertyRepo.findByOwner(owner);
    }
}
