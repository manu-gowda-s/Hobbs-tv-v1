package com.hobs.tv.controller;

import com.hobs.tv.dto.ContentDTO;
import com.hobs.tv.entity.Content;
import com.hobs.tv.entity.ContentType;
import com.hobs.tv.entity.Language;
import com.hobs.tv.service.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ContentController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private ContentService contentService;

    // save Content
    @PostMapping("/content")
    public ResponseEntity<ContentDTO> savedContent(@RequestBody ContentDTO contentDTO)
    {
        ContentDTO dto = contentService.addContent(contentDTO);
        //print the log
        LOGGER.info(String.format("Content saved --> %s", contentDTO.toString()));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/content/{viewerId}")
    public ResponseEntity<List<ContentDTO>> getContentById(@PathVariable Long viewerId)
    {
       List<ContentDTO> dto = contentService.findByViewerId(viewerId);
       LOGGER.info(String.format("Displaying all content form this viewer Id --> %s", dto.toString()));
       return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }

    @GetMapping("/content/type/{contentType}")
    public ResponseEntity<List<ContentDTO>> getContentById(@PathVariable ContentType contentType)
    {
        List<ContentDTO> dto = contentService.findWatchByContentType(contentType);
        LOGGER.info(String.format("Displaying all content based on type %s", dto.toString()));
        return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }

    //Watch History by Viewer and Content Type
    @GetMapping("/content/watch/{viewerId}/{contentType}")
    public ResponseEntity<List<ContentDTO>> watchHistoryByViewerAndContentType(@PathVariable Long viewerId,
                                                                               @PathVariable ContentType contentType)
    {
        List<ContentDTO> contentByViewerId = contentService.findAllContentByViewerId(viewerId, contentType);
        LOGGER.info(String.format("Content of this Viewer are --> %s", contentByViewerId.toString()));
        return new ResponseEntity<>(contentByViewerId, HttpStatus.FOUND);

    }


    // show content by language
    @GetMapping("/content/language/all")
    public ResponseEntity<Map<Language, List<Content>>> showAllContentByLanguage()
    {
        Map<Language, List<Content>> languageListMap = contentService.showContentByLanguage();
        LOGGER.info(String.format("The Specific content by language %s", languageListMap.toString()));
        return new ResponseEntity<>(languageListMap, HttpStatus.OK);
    }

}
