package com.stackroute.service;

import com.stackroute.domain.SubCategory;
import com.stackroute.repository.SubCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoriesService {
    @Autowired
    SubCategoriesRepository subCategoriesRepository;

    public List<SubCategory> findAll() {
        return (List<SubCategory>) subCategoriesRepository.findAll();
    }
    public List<SubCategory> getAll(String categoryName) {
        return (List<SubCategory>) subCategoriesRepository.getAll(categoryName);
    }

    public SubCategory saveSubCategory(SubCategory categories){
        SubCategory savedSubCategory=subCategoriesRepository.save(categories);
        return savedSubCategory;
    }

}
