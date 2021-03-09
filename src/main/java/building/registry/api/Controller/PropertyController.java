package building.registry.api.Controller;

import building.registry.api.Model.Property;
import building.registry.api.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class PropertyController {

    @Autowired
    PropertyService propertySrv;

    @RequestMapping( value = "/property", method = RequestMethod.POST)
    public String addProperty(@RequestParam("property") Property property)  {
        propertySrv.saveProperty(property);
        return "Success";
    }

    @RequestMapping( value = "/property", method = RequestMethod.GET)
    public Property getPropertyById(@RequestParam("id") int id)  {
        return propertySrv.getProperty(id);
    }

    @RequestMapping( value = "/property", method = RequestMethod.PUT)
    public String updateProperty(@RequestParam("property") Property property)  {
        propertySrv.saveProperty(property);
        return "Success";
    }

    @RequestMapping( value = "/property", method = RequestMethod.DELETE)
    public String removeProperty(@RequestParam("id") int id)  {
        propertySrv.removeProperty(id);
        return "Success";
    }

}
