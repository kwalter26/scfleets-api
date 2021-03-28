package com.fusionkoding.citizenshqapi.web.controllers;

import com.fusionkoding.citizenshqapi.db.entities.Setting;
import com.fusionkoding.citizenshqapi.services.SettingsService;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsService settingsService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Setting>> getSettings() {
        return ResponseEntity.ok(settingsService.getSettings());
    }

    @PostMapping("/")
    public ResponseEntity<Setting> createSetting(@RequestBody Setting setting) {
        return ResponseEntity.ok(settingsService.createSetting(setting));
    }

    @GetMapping("/{name}/")
    public ResponseEntity<Setting> getSettingByName(@PathVariable String name) throws NotFoundException {
        return ResponseEntity.ok(settingsService.getSettingByName(name));
    }

    @PutMapping("/{name}/")
    public ResponseEntity<Setting> updateSetting(@PathVariable String name,@RequestBody String value) throws NotFoundException {
        return ResponseEntity.ok(settingsService.updateSettingByName(name,value));
    }
}
