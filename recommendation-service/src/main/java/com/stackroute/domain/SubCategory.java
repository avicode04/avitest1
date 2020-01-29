package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class SubCategory {

    @GraphId
    private Long id;
    private String subCategoryNameOrGenre;
    private String parentNode;
    private String nodeType;
    private String parentRelation;
}
