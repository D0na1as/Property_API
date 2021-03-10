package building.registry.api.Controller;

import building.registry.api.Config.Response;
import building.registry.api.Model.Property;
import building.registry.api.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;

@Controller
@RequestMapping("/v1")
public class PropertyController {

    @Autowired
    PropertyService propertySrv;
    @Autowired
    Response resp;

    @RequestMapping( value = "/property", method = RequestMethod.POST)
    public ResponseEntity addProperty(@RequestParam("property") Property property)  {
        Property obj = propertySrv.saveProperty(property);
        String path = "/property/";
        return resp.createdResponse(obj.getId(), path, obj);
    }

    @RequestMapping( value = "/property/{id}", method = RequestMethod.GET)
    public ResponseEntity getPropertyById(@PathParam("id") int id)  {
        Property obj = propertySrv.getProperty(id);
        return resp.response(obj);
    }

    @RequestMapping( value = "/property", method = RequestMethod.PUT)
    public ResponseEntity updateProperty(@RequestParam("property") Property property)  {
        Property obj = propertySrv.saveProperty(property);
        return resp.response(obj);
    }

    @RequestMapping( value = "/property/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeProperty(@PathParam("id") int id)  {
        Boolean status = propertySrv.removeProperty(id);
        return resp.response(status);
    }
}
