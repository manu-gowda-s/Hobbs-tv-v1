package com.hobbs.tv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbSequence
{
    @Id
    private String id;

    private long seq;
}
