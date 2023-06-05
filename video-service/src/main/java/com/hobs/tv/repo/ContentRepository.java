package com.hobs.tv.repo;

import com.hobs.tv.dto.ContentDTO;
import com.hobs.tv.entity.Content;
import com.hobs.tv.entity.ContentType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContentRepository extends MongoRepository<Content, String>
{

    List<ContentDTO> findByViewerId(Long viewerId);

    List<ContentDTO> findByContentType(ContentType contentType);

//    @Query("{'author' : ?0, 'category' : ?1}")

    @Query("{'viewerId' : ?0, 'contentType' : ?1}")
    List<ContentDTO> findByViewerIdAndContentType(@Param("viewerId") Long viewerId,
                                                  @Param("contentType") ContentType contentType);

}
