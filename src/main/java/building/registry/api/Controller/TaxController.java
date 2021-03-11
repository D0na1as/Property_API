package building.registry.api.Controller;

import building.registry.api.Config.Response;
import building.registry.api.Model.Property;
import building.registry.api.Model.Tax;
import building.registry.api.Service.PropertyService;
import building.registry.api.Service.TaxService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/v1/property")
public class TaxController {

    @Autowired
    TaxService taxService;
    @Autowired
    PropertyService propService;
    @Autowired
    Response resp;

    @RequestMapping( value = "/tax", method = RequestMethod.POST)
    public ResponseEntity addTax(@RequestBody Tax tax)  {
        Tax obj = taxService.getTax(tax.getType());
        if (obj==null) {
            obj = taxService.saveTax(tax);
            String path = "/v1/property/tax/{type}";
            return resp.createdResponse(-1, path, obj);
        } else {
            return resp.createdResponse(-1, null, null);
        }
    }

    @RequestMapping( value = "/tax/{type}", method = RequestMethod.GET)
    public ResponseEntity getTax(@PathVariable("type") String type)  {
        Tax tax = taxService.getTax(type);
        if (tax==null) {
            return resp.response(false);
        }
        return resp.response(tax.getValue());
    }

    @RequestMapping( value = "/tax/{type}", method = RequestMethod.PUT)
    public ResponseEntity updateTax(@PathVariable("type") String type,
                                    @RequestParam("value") double value)  {
        Tax obj = taxService.getTax(type);
        obj.setValue(value);
        obj = taxService.saveTax(obj);
        return resp.response(obj);
    }

    @RequestMapping( value = "/tax/{type}", method = RequestMethod.DELETE)
    public ResponseEntity removeTax(@PathVariable("type") String type)  {
        Tax tax = taxService.getTax(type);
        if (tax==null) {
            return resp.response(false);
        }
        taxService.removeTax(type);
        return resp.response(true);
    }

    @RequestMapping( value = "/taxes", method = RequestMethod.GET)
    public ResponseEntity getTaxes()  {
        return resp.response(taxService.getAllTaxes());
    }

    @RequestMapping( value = "/taxes/annual/{owner}", method = RequestMethod.GET)
    public ResponseEntity getYearlyTaxes(@PathVariable("owner") String owner)  {
        List<Property> obj = propService.getPropertyByOwner(owner);
        if (obj.isEmpty()) {
            resp.response(false);
        }
        return resp.response(taxService.getBill(owner));
    }
}