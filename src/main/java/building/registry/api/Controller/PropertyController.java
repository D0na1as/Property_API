package building.registry.api.Controller;

import building.registry.api.Model.Property;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
@RequestMapping("/v1")
public class PropertyController {

    @RequestMapping( value = "/property", method = RequestMethod.POST)
    public String addProperty(@RequestParam("property") Property property)  {
        //TODO
        return "Success";
    }

    @RequestMapping( value = "/property", method = RequestMethod.GET)
    public String getProperty(@RequestParam("property") Property property)  {
        //TODO
        return "Success";
    }

    @RequestMapping( value = "/property", method = RequestMethod.PUT)
    public String updateProperty(@RequestParam("property") Property property)  {
        //TODO
        return "Success";
    }

    @RequestMapping( value = "/property", method = RequestMethod.DELETE)
    public String removeProperty(@RequestParam("property") Property property)  {
        //TODO
        return "Success";
    }

}
