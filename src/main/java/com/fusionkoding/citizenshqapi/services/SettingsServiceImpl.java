package com.fusionkoding.citizenshqapi.services;

import com.fusionkoding.citizenshqapi.entities.Setting;
import com.fusionkoding.citizenshqapi.repositories.SettingsRepository;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SettingsServiceImpl implements SettingsService{

    private final SettingsRepository settingsRepository;

    @Override
    public Map<String, Setting> getSettings() {
        return settingsRepository.findAll().stream().collect(Collectors.toMap(Setting::getValue,setting -> setting));
    }

    @Override
    public Setting getSettingByName(String name) throws NotFoundException {
        return settingsRepository.findByName(name).orElseThrow(() -> new NotFoundException("Setting Not Found"));
    }

    @Override
    public Setting createSetting(Setting setting) {
        return settingsRepository.save(setting);
    }

    @Override
    public Setting updateSettingByName(String name, String value) throws NotFoundException {
        Setting setting = settingsRepository.findByName(name).orElseThrow(() -> new NotFoundException("Setting Not Found"));
        setting.setValue(value);
        return settingsRepository.save(setting);
    }
}
