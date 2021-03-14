package building.registry.api.Controller;

import building.registry.api.Model.Property;
import building.registry.api.Model.Tax;
import building.registry.api.Service.PropertyService;
import building.registry.api.Service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/v1")
public class PropertyController {

    @Autowired
    PropertyService propertySrv;
    @Autowired
    TaxService taxService;

    @RequestMapping( value = "/property", method = RequestMethod.POST)
    public ResponseEntity addProperty(@RequestBody Property property)  {
        Tax tax = taxService.getTax(property.getType());
        if (tax==null) {
            ResponseEntity.badRequest().body("Tax must be in database!");
        }
        Property obj = propertySrv.saveProperty(property);
        String path = "/v1/property/{id}";
        URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path(path).buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @RequestMapping( value = "/property/{id}", method = RequestMethod.GET)
    public ResponseEntity getPropertyById(@PathVariable int id ) {
        Property obj = propertySrv.getPropertyById(id);
        if (obj==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(obj);
    }

    @RequestMapping( value = "/property", method = RequestMethod.GET)
    public ResponseEntity getPropertyById(@RequestParam(value = "owner") String owner) {
        List<Property> obj = propertySrv.getPropertyByOwner(owner);
        if (obj.size()==0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(obj);
    }

    @RequestMapping( value = "/property/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateProperty(@PathVariable("id") int id,
                                         @RequestBody Property property)  {
        Property obj = propertySrv.getPropertyById(id);
        if (obj!=null) {
            property.setId(id);
            obj = propertySrv.saveProperty(property);
            return ResponseEntity.ok(obj);
        } else {
            return ResponseEntity.badRequest().body("No such property in database!");
        }
    }

    @RequestMapping( value = "/property/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeProperty(@PathVariable("id") int id)  {
        Property obj = propertySrv.getPropertyById(id);
        if (obj!=null) {
            propertySrv.removeProperty(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
