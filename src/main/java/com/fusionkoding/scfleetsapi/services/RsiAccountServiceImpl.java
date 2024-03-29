package com.fusionkoding.scfleetsapi.services;

import com.fusionkoding.scfleetsapi.bindings.AuthVerificationBinding;
import com.fusionkoding.scfleetsapi.db.entities.RsiAccount;
import com.fusionkoding.scfleetsapi.db.entities.RsiAuth;
import com.fusionkoding.scfleetsapi.db.entities.Setting;
import com.fusionkoding.scfleetsapi.db.repositories.RsiAccountRepository;
import com.fusionkoding.scfleetsapi.dtos.MfaToken;
import com.fusionkoding.scfleetsapi.dtos.RsiAccountCreateDto;
import com.fusionkoding.scfleetsapi.dtos.RsiAccountDto;
import com.fusionkoding.scfleetsapi.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RsiAccountServiceImpl implements RsiAccountService {

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("RsiAccount Not Found");

    private final RsiAccountRepository rsiAccountRepository;
    private final AuthVerificationBinding authVerificationBinding;
    private final SettingsService settingsService;

    @Override
    public List<RsiAccountDto> getRsiAccounts() {
        List<RsiAccount> rsiAccounts = rsiAccountRepository.findAll();
        return rsiAccounts.stream().map(rsiAccount ->
                RsiAccountDto.builder().id(rsiAccount.getId()).email(rsiAccount.getEmail()).build()).collect(Collectors.toList());
    }

    @Override
    public RsiAccountDto getRsiAccountById(String id) throws NotFoundException {
        RsiAccount rsiAccount = getRsiAccount(id);
        return RsiAccountDto.builder().id(rsiAccount.getId()).email(rsiAccount.getEmail()).build();
    }

    @Override
    public RsiAccountDto getRsiAccountById() throws NotFoundException {
        Setting defaultAccount = settingsService.getSettingByName("defaultAccount");
        if (defaultAccount == null || defaultAccount.getValue() == null) {
            ResponseEntity.badRequest().build();
        }
        return getRsiAccountById(defaultAccount.getValue());
    }

    @Override
    public RsiAccountDto createRsiAccount(RsiAccountCreateDto rsiAccountCreateDto) {
        RsiAccount rsiAccount = rsiAccountRepository.save(RsiAccount.builder()
                .email(rsiAccountCreateDto.getEmail())
                .password(rsiAccountCreateDto.getPassword())
                .build());
        return RsiAccountDto.builder().id(rsiAccount.getId()).email(rsiAccount.getEmail()).build();
    }

    @Override
    public RsiAuth getRsiAccountAuth(String id) throws NotFoundException {
        RsiAccount rsiAccount = getRsiAccount(id);
        return rsiAccount.getRsiAuth();
    }

    @Override
    public RsiAuth updateRsiAuth(String id, RsiAuth rsiAuth) throws NotFoundException {
        RsiAccount rsiAccount = getRsiAccount(id);
        rsiAccount.setRsiAuth(rsiAuth);
        rsiAccount = rsiAccountRepository.save(rsiAccount);
        return rsiAccount.getRsiAuth();
    }

    @Override
    public void refreshRsiAuth() throws NotFoundException {
        refreshRsiAuth(getRsiAccountById().getId());
    }

    @Override
    public void refreshRsiAuth(String id) throws NotFoundException {
        RsiAccount rsiAccount = getRsiAccount(id);
        log.debug("Sending auth refresh: " + id);
        authVerificationBinding.refreshRsiAuth(id, rsiAccount.getEmail(), rsiAccount.getPassword(), rsiAccount.getRsiAuth().getDeviceId());
        log.info("Sent auth refresh: " + id);
    }

    @Override
    public List<MfaToken> getMfaTokens(String id) throws NotFoundException {
        return this.getRsiAccount(id).getMfaTokens();
    }

    @Override
    public MfaToken useMfaToken(String id) throws NotFoundException {
        RsiAccount rsiAccount = getRsiAccount(id);
        List<MfaToken> mfaTokens = rsiAccount.getMfaTokens();
        if (mfaTokens == null || mfaTokens.isEmpty()) {
            throw NOT_FOUND_EXCEPTION;
        }
        MfaToken token = mfaTokens.remove(0);
        rsiAccount.setMfaTokens(mfaTokens);
        rsiAccountRepository.save(rsiAccount);
        return token;
    }

    @Override
    public MfaToken addMfaToken(String id, MfaToken mfaToken) throws NotFoundException {
        RsiAccount rsiAccount = RsiAccountServiceImpl.this.getRsiAccount(id);
        rsiAccount.getMfaTokens().add(mfaToken);
        rsiAccountRepository.save(rsiAccount);
        return mfaToken;
    }


    @Override
    public String deleteMfaToken(String id, String mfaToken) throws NotFoundException {
        RsiAccount rsiAccount = RsiAccountServiceImpl.this.getRsiAccount(id);
        rsiAccount.setMfaTokens(rsiAccount.getMfaTokens().stream().filter(token -> mfaToken.equals(token)).collect(Collectors.toList()));
        rsiAccountRepository.save(rsiAccount);
        return mfaToken;
    }

    private RsiAccount getRsiAccount(String id) throws NotFoundException {
        return rsiAccountRepository.findById(id).orElseThrow(() -> NOT_FOUND_EXCEPTION);
    }
}
