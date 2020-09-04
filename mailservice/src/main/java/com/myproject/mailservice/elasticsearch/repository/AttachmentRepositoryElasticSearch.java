package com.myproject.mailservice.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.myproject.mailservice.entity.Attachment;

public interface AttachmentRepositoryElasticSearch extends ElasticsearchRepository<Attachment, Integer> {

}
