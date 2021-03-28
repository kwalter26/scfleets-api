package com.fusionkoding.scfleetsapi.db.entities;

import com.fusionkoding.scfleetsapi.dtos.MfaToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "rsi_accounts")
public class RsiAccount {
    @Id
    private String id;
    private String email;
    private String password;
    @Builder.Default
    private RsiAuth rsiAuth = new RsiAuth();
    @Builder.Default
    private List<MfaToken> mfaTokens = new ArrayList<>();
}

