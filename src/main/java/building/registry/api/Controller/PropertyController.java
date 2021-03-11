package building.registry.api.Controller;

import building.registry.api.Config.Response;
import building.registry.api.Model.Property;
import building.registry.api.Model.Tax;
import building.registry.api.Service.PropertyService;
import building.registry.api.Service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class PropertyController {

    @Autowired
    PropertyService propertySrv;
    @Autowired
    TaxService taxService;
    @Autowired
    Response resp;

    @RequestMapping( value = "/property", method = RequestMethod.POST)
    public ResponseEntity addProperty(@RequestBody Property property)  {
        Tax tax = taxService.getTax(property.getType());
        if (tax==null) {
            return resp.createdResponse(0, null, null);
        }
        Property obj = propertySrv.saveProperty(property);
        String path = "/v1/property/{id}";
        return resp.createdResponse(obj.getId(), path, obj);
    }

    @RequestMapping( value = "/property/{id}", method = RequestMethod.GET)
    public ResponseEntity getPropertyById(@PathVariable int id ) {
        Property obj = propertySrv.getPropertyById(id);
        return resp.response(obj);
    }

    @RequestMapping( value = "/property", method = RequestMethod.GET)
    public ResponseEntity getPropertyById(@RequestParam(value = "owner") String owner) {
        List<Property> obj = propertySrv.getPropertyByOwner(owner);
        if (obj.size()==0) {
            return resp.response(false);
        }
        return resp.response(obj);
    }

    @RequestMapping( value = "/property/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateProperty(@PathVariable("id") int id,
                                         @RequestBody Property property)  {
        Property obj = propertySrv.getPropertyById(id);
        if (obj!=null) {
            property.setId(id);
            obj = propertySrv.saveProperty(property);
            return resp.response(obj);
        } else {
            return resp.response(false);
        }
    }

    @RequestMapping( value = "/property/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeProperty(@PathVariable("id") int id)  {
        Property obj = propertySrv.getPropertyById(id);
        if (obj!=null) {
            return resp.response(propertySrv.removeProperty(id));
        } else {
            return resp.response(false);
        }
    }
}
