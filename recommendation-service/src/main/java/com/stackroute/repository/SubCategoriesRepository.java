package com.stackroute.repository;

import com.stackroute.domain.SubCategory;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubCategoriesRepository extends Neo4jRepository<SubCategory,Long> {
    @Query("Match (s:SubCategory) where s.parentNode={parentNode} return s")
    public List<SubCategory> getAll(@Param("parentNode") String parentNode);
}
