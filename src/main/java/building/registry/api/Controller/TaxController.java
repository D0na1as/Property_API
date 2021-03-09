package building.registry.api.Controller;

import building.registry.api.Model.Property;
import building.registry.api.Model.Tax;
import building.registry.api.Service.PropertyService;
import building.registry.api.Service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/v1/property")
public class TaxController {

    @Autowired
    TaxService taxService;

    @RequestMapping( value = "/tax", method = RequestMethod.POST)
    public String addTax(@RequestParam("tax") Tax tax)  {
        taxService.saveTax(tax);
        return "Success";
    }

    @RequestMapping( value = "/tax", method = RequestMethod.GET)
    public String getTax(@RequestParam("type") String type)  {
        taxService.getTax(type);
        return "Success";
    }

    @RequestMapping( value = "/tax", method = RequestMethod.PUT)
    public String updateTax(@RequestParam("tax") Tax tax)  {
        taxService.saveTax(tax);
        return "Success";
    }

    @RequestMapping( value = "/tax", method = RequestMethod.DELETE)
    public String removeTax(@RequestParam("type") String type)   {
        taxService.removeTax(type);
        return "Success";
    }

    @RequestMapping( value = "/taxes", method = RequestMethod.GET)
    public String getTaxes()  {
        taxService.getAllTaxes();
        return "Success";
    }

    @RequestMapping( value = "/taxes/annual/{owner}/", method = RequestMethod.GET)
    public double getYearlyTaxes(@PathVariable String owner)  {
        return taxService.getBill(owner);
    }

}