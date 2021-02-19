package com.fusionkoding.citizenshqapi.services;

import com.fusionkoding.citizenshqapi.dtos.RsiAccountCreateDto;
import com.fusionkoding.citizenshqapi.dtos.RsiAccountDto;
import com.fusionkoding.citizenshqapi.entities.RsiAuth;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;

import java.util.List;

public interface RsiAccountService {
    RsiAccountDto getRsiAccountById(String id) throws NotFoundException;

    RsiAccountDto createRsiAccount(RsiAccountCreateDto rsiAccountCreateDto);

    RsiAuth getRsiAccountAuth(String id) throws NotFoundException;

    RsiAuth rsiAuth(String id, RsiAuth rsiAuth)  throws NotFoundException;

    List<String> getMfaTokens(String id) throws NotFoundException;

    String useMfaToken(String id)  throws NotFoundException;

    String addMfaToken(String id, String mfaToken) throws NotFoundException;

    String deleteMfaToken(String id, String mfaToken) throws NotFoundException;
}
