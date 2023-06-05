package com.hobs.tv.service;

import com.hobs.tv.dto.ContentDTO;
import com.hobs.tv.entity.Content;
import com.hobs.tv.entity.ContentType;
import com.hobs.tv.entity.Language;
import com.hobs.tv.exception.ContentTypeNotFound;
import com.hobs.tv.exception.NoContentFoundException;
import com.hobs.tv.exception.NoContentFoundForThisViewer;
import com.hobs.tv.repo.ContentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContentServiceImpl implements ContentService
{

    private ContentRepository contentRepository;
    private ModelMapper modelMapper;

    @Override
    public ContentDTO addContent(ContentDTO contentDTO)
    {
        Content content = modelMapper.map(contentDTO, Content.class);
        Content savedContent = contentRepository.save(content);

        return modelMapper.map(savedContent, ContentDTO.class);
    }

    @Override
    public List<ContentDTO> findByViewerId(Long viewerId)
    {
        List<ContentDTO> dtoList = contentRepository.findByViewerId(viewerId);

        if(dtoList.isEmpty()){
            throw new NoContentFoundException("No Content", "ViewerId", viewerId);
        }else{
            return dtoList;
        }
    }

    @Override
    public List<ContentDTO> findWatchByContentType(ContentType contentType)
    {
        List<ContentDTO> contentDTOList = contentRepository.findByContentType(contentType);

        if(contentDTOList.isEmpty()){
            throw new ContentTypeNotFound("CONTENT TYPE NOT FOUND");
        }else {
            return contentDTOList;
        }
    }

    @Override
    public List<ContentDTO> findAllContentByViewerId(Long viewerId, ContentType contentType)
    {
        List<ContentDTO> list = contentRepository.findByViewerIdAndContentType(viewerId, contentType);

        if(list.isEmpty()){
            throw new NoContentFoundForThisViewer("No Content Found For This Viewer, Buy to see!!");
        }else {
            return list;
        }
    }

    @Override
    public Map<Language, List<Content>> showContentByLanguage()
    {
        return contentRepository.findAll()
                .stream()
                .filter(lan -> lan.getLanguage() != null)
                .distinct()
                .collect(Collectors
                        .groupingBy(Content::getLanguage));
    }


}
