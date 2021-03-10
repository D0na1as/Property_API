package building.registry.api.Controller;

import building.registry.api.Config.Response;
import building.registry.api.Model.Property;
import building.registry.api.Model.Tax;
import building.registry.api.Service.PropertyService;
import building.registry.api.Service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/v1/property")
public class TaxController {

    @Autowired
    TaxService taxService;
    @Autowired
    Response resp;

    @RequestMapping( value = "/tax", method = RequestMethod.POST)
    public ResponseEntity addTax(@RequestParam("tax") Tax tax)  {
        Tax obj = taxService.saveTax(tax);
        String path = "/tax/";
        return resp.createdResponse(obj.getId(), path, obj);
    }

    @RequestMapping( value = "/tax/{id}", method = RequestMethod.GET)
    public ResponseEntity getTax(@PathParam("type") String type)  {
        double value = taxService.getTax(type);
        return resp.response(value);
    }

    @RequestMapping( value = "/tax", method = RequestMethod.PUT)
    public ResponseEntity updateTax(@RequestParam("tax") Tax tax)  {
        Tax obj = taxService.saveTax(tax);
        return resp.response(obj);
    }

    @RequestMapping( value = "/tax", method = RequestMethod.DELETE)
    public ResponseEntity removeTax(@RequestParam("type") String type)   {
        Boolean status = taxService.removeTax(type);
        return resp.response(status);
    }

    @RequestMapping( value = "/taxes", method = RequestMethod.GET)
    public ResponseEntity getTaxes()  {
        return resp.response(taxService.getAllTaxes());
    }

    @RequestMapping( value = "/taxes/annual/{owner}/", method = RequestMethod.GET)
    public double getYearlyTaxes(@PathVariable String owner)  {
        return taxService.getBill(owner);
    }

}