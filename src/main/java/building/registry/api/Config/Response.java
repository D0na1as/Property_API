package building.registry.api.Config;

import building.registry.api.Model.Property;
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
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path(path).buildAndExpand(id).toUri();

            return ResponseEntity.created(uri).body(obj);
        }
    }
}
