package com.fusionkoding.citizenshqapi.db.entities;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "settings")
public class Setting {
    @Id
    private String id;
    private String name;
    private String value;
}
