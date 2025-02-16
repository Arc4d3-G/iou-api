package com.example.iou_api.controller;

import com.example.iou_api.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.iou_api.model.Currency;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/iou/util")
public class UtilityController {

    @GetMapping("/currencies")
    public ResponseEntity<List<String>> getAllCurrencies() {
        return ResponseEntity.ok(Arrays.stream(Currency.values())
                .map(Enum::name)
                .toList());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<String>> getAllRoles() {
        return ResponseEntity.ok(Arrays.stream(Role.values())
                .map(Enum::name)
                .toList());
    }

}
