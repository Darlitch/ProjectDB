package com.db.project.api.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
    private Integer id;
    private String serialNum;
    private Integer typeId;
    private Integer workshopId;
    private List<Integer> attributeValueIds;
    private List<Integer> productionProcessIds;
    private List<Integer> testIds;
} 