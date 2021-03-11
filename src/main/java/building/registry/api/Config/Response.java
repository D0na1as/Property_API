package building.registry.api.Config;

import building.registry.api.Model.Property;
import building.registry.api.Model.Tax;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class Response {

    public ResponseEntity response(Object obj) {
        if (obj!=null) {
            return ResponseEntity.ok(obj);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity response(Boolean status) {
        if (status) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity createdResponse(int id, String path, Object obj) {
        if (obj==null) {
            if (id==-1) {
                return ResponseEntity.badRequest().body("This tax already exist!");
            }
                return ResponseEntity.badRequest().body("No such tax in database!");
        } else {
            URI uri;
            if (id==-1) {
                Tax tax = (Tax) obj;
                uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path(path).buildAndExpand(tax.getType()).toUri();
            } else {
                uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path(path).buildAndExpand(id).toUri();
            }

            return ResponseEntity.created(uri).body(obj);
        }
    }
}
