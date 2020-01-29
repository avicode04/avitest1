package com.stackroute.repository;

import com.stackroute.domain.Category;
import com.stackroute.domain.SubCategory;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface CategoriesRepository extends Neo4jRepository<Category,Long> {

}
