package building.registry.api.Controller;

import building.registry.api.Model.Property;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v1")
public class TaxController {

    @RequestMapping( value = "/tax", method = RequestMethod.POST)
    public String addTax(@RequestParam("property") Property property)  {
        //TODO
        return "Success";
    }

    @RequestMapping( value = "/tax", method = RequestMethod.GET)
    public String getTax(@RequestParam("property") Property property)  {
        //TODO
        return "Success";
    }

    @RequestMapping( value = "/tax", method = RequestMethod.PUT)
    public String updateTax(@RequestParam("property") Property property)  {
        //TODO
        return "Success";
    }

    @RequestMapping( value = "/tax", method = RequestMethod.DELETE)
    public String removeTax(@RequestParam("property") Property property)  {
        //TODO
        return "Success";
    }

    @RequestMapping( value = "/taxes", method = RequestMethod.GET)
    public String getTaxes()  {
        //TODO
        return "Success";
    }

}