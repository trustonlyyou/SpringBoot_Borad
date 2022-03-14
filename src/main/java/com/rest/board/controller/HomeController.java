package com.rest.board.controller;

import com.rest.board.entity.Home;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping("/get")
    public ResponseEntity getHome(@RequestParam String name,
                                  @RequestParam String age) {

        Home home = new Home();
        home.setAge(Integer.parseInt(age));
        home.setName(name);

        return ResponseEntity.ok(home);
    }
}
