package com.db.project.core.service;

import com.db.project.core.model.AttributeValue;
import com.db.project.api.dto.attributevalue.AttributeValueCreateDTO;
import com.db.project.api.dto.attributevalue.AttributeValueDTO;
import com.db.project.api.dto.attributevalue.AttributeValueUpdateDTO;

import java.util.List;

public interface AttributeValueService {

    AttributeValueDTO create(AttributeValueCreateDTO attributeValueDTO);

    List<AttributeValueDTO> getAll();

    AttributeValueDTO getById(Integer id);

    AttributeValue getEntityById(Integer id);

    AttributeValueDTO update(Integer id, AttributeValueUpdateDTO attributeValueDTO);

    void delete(Integer id);
} 