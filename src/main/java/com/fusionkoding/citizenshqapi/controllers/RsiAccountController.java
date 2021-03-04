package com.fusionkoding.citizenshqapi.controllers;

import com.fusionkoding.citizenshqapi.dtos.MfaToken;
import com.fusionkoding.citizenshqapi.dtos.RsiAccountCreateDto;
import com.fusionkoding.citizenshqapi.dtos.RsiAccountDto;
import com.fusionkoding.citizenshqapi.entities.RsiAuth;
import com.fusionkoding.citizenshqapi.services.RsiAccountService;
import com.fusionkoding.citizenshqapi.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class RsiAccountController {

    private final RsiAccountService rsiAccountService;

    @GetMapping("/")
    public ResponseEntity<List<RsiAccountDto>> getRsiAccounts() {
        return ResponseEntity.ok(rsiAccountService.getRsiAccounts());
    }

    @PostMapping("/")
    public ResponseEntity<RsiAccountDto> createAccount(@RequestBody RsiAccountCreateDto rsiAccountCreateDto) {
        return ResponseEntity.ok(rsiAccountService.createRsiAccount(rsiAccountCreateDto));
    }

    @GetMapping("/{id}/")
    public ResponseEntity<RsiAccountDto> getRsiAccount(@PathVariable String id) throws NotFoundException {
        return ResponseEntity.ok(rsiAccountService.getRsiAccountById(id));
    }

    @GetMapping("/{id}/auth/")
    public ResponseEntity<RsiAuth> getRsiAccountAuth(@PathVariable String id) throws NotFoundException {
        return ResponseEntity.ok(rsiAccountService.getRsiAccountAuth(id));
    }

    @PostMapping("/{id}/auth/")
    public ResponseEntity<RsiAuth> updateRsiAccountAuth(@PathVariable String id,@RequestBody RsiAuth rsiAuth) throws NotFoundException {
        return ResponseEntity.ok(rsiAccountService.updateRsiAuth(id,rsiAuth));
    }

    @GetMapping("/{id}/tokens/")
    public ResponseEntity<List<MfaToken>> getRsiAccountTokens(@PathVariable String id) throws NotFoundException {
        return ResponseEntity.ok(rsiAccountService.getMfaTokens(id));
    }

    @PostMapping("/{id}/tokens/")
    public ResponseEntity<MfaToken> createRsiAccountToken(@PathVariable String id,@RequestBody MfaToken mfaToken) throws NotFoundException {
        return ResponseEntity.ok(rsiAccountService.addMfaToken(id,mfaToken));
    }

    @GetMapping("/{id}/tokens/use/")
    public ResponseEntity<MfaToken> useRsiAccountToken(@PathVariable String id) throws NotFoundException {
        return ResponseEntity.ok(rsiAccountService.useMfaToken(id));
    }

}
