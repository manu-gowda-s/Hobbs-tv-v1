package com.hobs.tv.service;

import com.hobs.tv.dto.ContentDTO;
import com.hobs.tv.entity.Content;
import com.hobs.tv.entity.ContentType;
import com.hobs.tv.entity.Language;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ContentService
{
    ContentDTO addContent(ContentDTO contentDTO);

    List<ContentDTO> findByViewerId(Long viewerId);

    List<ContentDTO> findWatchByContentType(ContentType contentType);

    List<ContentDTO> findAllContentByViewerId(Long viewerId, ContentType contentType);

    Map<Language, List<Content>> showContentByLanguage();
}
