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
@RequestMapping("/v1/property")
public class TaxController {

    @Autowired
    TaxService taxService;
    @Autowired
    PropertyService propService;

    @RequestMapping( value = "/tax", method = RequestMethod.POST)
    public ResponseEntity addTax(@RequestBody Tax tax)  {
        Tax obj = taxService.getTax(tax.getType());
        if (obj==null) {
            obj = taxService.saveTax(tax);
            String path = "/v1/property/tax/{type}";
            URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path(path).buildAndExpand(tax.getType()).toUri();
            return ResponseEntity.created(uri).body(obj);
        } else {
            return ResponseEntity.badRequest().body("This tax already exist!");
        }
    }

    @RequestMapping( value = "/tax/{type}", method = RequestMethod.GET)
    public ResponseEntity getTax(@PathVariable("type") String type)  {
        Tax tax = taxService.getTax(type);
        if (tax==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tax.getValue());
    }

    @RequestMapping( value = "/tax/{type}", method = RequestMethod.PUT)
    public ResponseEntity updateTax(@PathVariable("type") String type,
                                    @RequestParam("value") double value)  {
        Tax obj = taxService.getTax(type);
        if (obj==null) {
            return ResponseEntity.badRequest().body("No such tax in database!");
        } else {
            obj.setValue(value);
            obj = taxService.saveTax(obj);
            return ResponseEntity.ok(obj);
        }
    }

    @RequestMapping( value = "/tax/{type}", method = RequestMethod.DELETE)
    public ResponseEntity removeTax(@PathVariable("type") String type)  {
        Tax tax = taxService.getTax(type);
        if (tax==null) {
            return ResponseEntity.notFound().build();
        }
        taxService.removeTax(type);
        return ResponseEntity.ok().build();
    }

    @RequestMapping( value = "/taxes", method = RequestMethod.GET)
    public ResponseEntity getTaxes()  {
        return ResponseEntity.ok(taxService.getAllTaxes());
    }

    @RequestMapping( value = "/taxes/annual/{owner}", method = RequestMethod.GET)
    public ResponseEntity getYearlyTaxes(@PathVariable("owner") String owner)  {
        List<Property> obj = propService.getPropertyByOwner(owner);
        if (obj.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taxService.getBill(owner));
    }
}