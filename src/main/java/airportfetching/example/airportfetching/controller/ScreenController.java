package airportfetching.example.airportfetching.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class ScreenController {

    @GetMapping("/admin")
    public ResponseEntity<?> addScreen(){
        return ResponseEntity.ok("hello admin");
    }

    @GetMapping("/user")
    public ResponseEntity<?> addScreenUser(){
        return ResponseEntity.ok("hello admin");
    }
}
