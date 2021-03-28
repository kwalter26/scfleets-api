package com.fusionkoding.scfleetsapi.services;

import com.fusionkoding.scfleetsapi.db.entities.RsiAuth;
import com.fusionkoding.scfleetsapi.dtos.MfaToken;
import com.fusionkoding.scfleetsapi.dtos.RsiAccountCreateDto;
import com.fusionkoding.scfleetsapi.dtos.RsiAccountDto;
import com.fusionkoding.scfleetsapi.utils.NotFoundException;

import java.util.List;

public interface RsiAccountService {
    List<RsiAccountDto> getRsiAccounts();

    RsiAccountDto getRsiAccountById(String id) throws NotFoundException;

    RsiAccountDto getRsiAccountById() throws NotFoundException;

    RsiAccountDto createRsiAccount(RsiAccountCreateDto rsiAccountCreateDto);

    RsiAuth getRsiAccountAuth(String id) throws NotFoundException;

    RsiAuth updateRsiAuth(String id, RsiAuth rsiAuth) throws NotFoundException;

    void refreshRsiAuth() throws NotFoundException;

    void refreshRsiAuth(String id) throws NotFoundException;

    List<MfaToken> getMfaTokens(String id) throws NotFoundException;

    MfaToken useMfaToken(String id) throws NotFoundException;

    MfaToken addMfaToken(String id, MfaToken mfaToken) throws NotFoundException;

    String deleteMfaToken(String id, String mfaToken) throws NotFoundException;
}
