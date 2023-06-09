package cinema.Rest;

import cinema.Stats.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class CheckStats {
    private Stats stats;

    @Autowired
    public CheckStats(Stats stats) {
        this.stats = stats;
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(required = false) String password) {
        if (password == null) {
            return ResponseEntity.status(401).body("{\"error\": \"The password is wrong!\"}");
        } else if (password.equals("super_secret")) {
            return ResponseEntity.ok().body(this.stats);
        }
        return ResponseEntity.status(401).body("\"error\": \"The password is wrong!\"");
    }


}
