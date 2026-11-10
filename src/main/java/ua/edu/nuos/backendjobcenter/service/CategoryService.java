package ua.edu.nuos.backendjobcenter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.nuos.backendjobcenter.data.Category;
import ua.edu.nuos.backendjobcenter.repository.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

}
