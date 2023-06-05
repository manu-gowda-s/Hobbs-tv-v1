package com.hobbs.tv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO
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

