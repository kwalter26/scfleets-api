package com.fusionkoding.scfleetsapi.web.controllers;

import com.fusionkoding.scfleetsapi.db.entities.RsiAuth;
import com.fusionkoding.scfleetsapi.dtos.MfaToken;
import com.fusionkoding.scfleetsapi.dtos.RsiAccountCreateDto;
import com.fusionkoding.scfleetsapi.dtos.RsiAccountDto;
import com.fusionkoding.scfleetsapi.services.RsiAccountService;
import com.fusionkoding.scfleetsapi.services.SettingsService;
import com.fusionkoding.scfleetsapi.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class RsiAccountController {

    private final RsiAccountService rsiAccountService;
    private final SettingsService settingsService;

    @GetMapping("/")
    public ResponseEntity<List<RsiAccountDto>> getRsiAccounts() {
        log.debug("Getting all RSI Accounts");
        return ResponseEntity.ok(rsiAccountService.getRsiAccounts());
    }

    @PostMapping("/")
    public ResponseEntity<RsiAccountDto> createAccount(@RequestBody RsiAccountCreateDto rsiAccountCreateDto) {
        log.debug("Creating new RSI Account");
        return ResponseEntity.ok(rsiAccountService.createRsiAccount(rsiAccountCreateDto));
    }

    @GetMapping("/{id}/")
    public ResponseEntity<RsiAccountDto> getRsiAccount(@PathVariable String id) throws NotFoundException {
        log.debug("Getting RSI Account" + id);
        return ResponseEntity.ok(rsiAccountService.getRsiAccountById(id));
    }

    @GetMapping("/default/")
    public ResponseEntity<RsiAccountDto> getDefaultRsiAccount() throws NotFoundException {
        log.debug("Getting Default RSI Account");
        return ResponseEntity.ok(rsiAccountService.getRsiAccountById());
    }

    @GetMapping("/{id}/auth/")
    public ResponseEntity<RsiAuth> getRsiAccountAuth(@PathVariable String id) throws NotFoundException {
        log.debug("Getting RSI Account auth" + id);
        return ResponseEntity.ok(rsiAccountService.getRsiAccountAuth(id));
    }

    @GetMapping("/default/auth/refresh/")
    public ResponseEntity<Object> refreshDefaultRsiAccountAuth() throws NotFoundException {
        log.debug("Getting RSI Account auth");
        rsiAccountService.refreshRsiAuth();
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}/auth/refresh/")
    public ResponseEntity<Object> refreshRsiAccountAuth(@PathVariable String id) throws NotFoundException {
        log.debug("Getting RSI Account auth" + id);
        rsiAccountService.refreshRsiAuth(id);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/{id}/auth/")
    public ResponseEntity<RsiAuth> updateRsiAccountAuth(@PathVariable String id, @RequestBody RsiAuth rsiAuth) throws NotFoundException {
        log.debug("Updating RSI Account auth" + id);
        return ResponseEntity.ok(rsiAccountService.updateRsiAuth(id, rsiAuth));
    }

    @GetMapping("/{id}/tokens/")
    public ResponseEntity<List<MfaToken>> getRsiAccountTokens(@PathVariable String id) throws NotFoundException {
        log.debug("Updating RSI Account tokens" + id);
        return ResponseEntity.ok(rsiAccountService.getMfaTokens(id));
    }

    @PostMapping("/{id}/tokens/")
    public ResponseEntity<MfaToken> createRsiAccountToken(@PathVariable String id, @RequestBody MfaToken mfaToken) throws NotFoundException {
        log.debug("Adding RSI Account token" + id);
        return ResponseEntity.ok(rsiAccountService.addMfaToken(id, mfaToken));
    }

    @GetMapping("/{id}/tokens/use/")
    public ResponseEntity<MfaToken> useRsiAccountToken(@PathVariable String id) throws NotFoundException {
        log.debug("Using RSI Account token" + id);
        return ResponseEntity.ok(rsiAccountService.useMfaToken(id));
    }

}