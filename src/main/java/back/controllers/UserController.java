package back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    public ResponseEntity<Long> addUser() {

        return ResponseEntity.ok(1L);
    }

}
