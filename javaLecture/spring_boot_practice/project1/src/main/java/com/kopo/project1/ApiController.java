package com.kopo.project1;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ApiController {
    @GetMapping("/create")
    public String createTable() {
        DB db = new DB();
        db.createTable();
        return "success";
    }

}
