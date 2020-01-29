package com.stackroute.service;

import com.stackroute.domain.Category;
import com.stackroute.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    CategoriesRepository categoriesRepository;

    public List<Category> findAll() {
        return (List<Category>) categoriesRepository.findAll();
    }

    public Category saveCategory(Category category){
        Category savedCategory=categoriesRepository.save(category);
        return savedCategory;
    }
}
