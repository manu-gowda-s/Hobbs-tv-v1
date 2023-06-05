package com.hobs.tv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "content_details")
public class Content
{
    private Long contentId;
    private long viewerId;
    private String contentName;
    private ContentType contentType;
    private Genres genres;
    private Language language;
    private float ratting;
    private double rentPrice;

}
