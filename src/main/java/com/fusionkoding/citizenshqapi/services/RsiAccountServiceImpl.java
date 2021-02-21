package com.fusionkoding.citizenshqapi.services;

import com.fusionkoding.citizenshqapi.dtos.MfaToken;
import com.fusionkoding.citizenshqapi.dtos.RsiAccountCreateDto;
import com.fusionkoding.citizenshqapi.dtos.RsiAccountDto;
import com.fusionkoding.citizenshqapi.entities.RsiAccount;
import com.fusionkoding.citizenshqapi.entities.RsiAuth;
import com.fusionkoding.citizenshqapi.repositories.RsiAccountRepository;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RsiAccountServiceImpl implements RsiAccountService {

    private static final NotFoundException NOT_FOUND_EXCEPTION = new NotFoundException("RsiAccount Not Found");

    private final RsiAccountRepository rsiAccountRepository;

    @Override
    public List<RsiAccountDto> getRsiAccounts() {
        List<RsiAccount> rsiAccounts = rsiAccountRepository.findAll();
        return rsiAccounts.stream().map(rsiAccount->
                RsiAccountDto.builder().id(rsiAccount.getId()).email(rsiAccount.getEmail()).build()).collect(Collectors.toList());
    }

    @Override
    public RsiAccountDto getRsiAccountById(String id) throws NotFoundException {
        RsiAccount rsiAccount = getRsiAccount(id);
        return RsiAccountDto.builder().id(rsiAccount.getId()).email(rsiAccount.getEmail()).build();
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
