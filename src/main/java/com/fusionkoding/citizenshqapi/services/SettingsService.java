package com.fusionkoding.citizenshqapi.services;

import com.fusionkoding.citizenshqapi.db.entities.Setting;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import java.util.Map;


public interface SettingsService {
    Map<String,Setting> getSettings();
    Setting createSetting(Setting setting);
    Setting getSettingByName(String name) throws NotFoundException;
    Setting updateSettingByName(String name, String value) throws NotFoundException;
}
