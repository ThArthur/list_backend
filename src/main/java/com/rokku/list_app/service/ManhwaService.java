package com.rokku.list_app.service;

import com.rokku.list_app.models.Category;
import com.rokku.list_app.models.Manhwa;
import com.rokku.list_app.repository.CategoryRepository;
import com.rokku.list_app.repository.ManhwaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ManhwaService {

    @Autowired
    private ManhwaRepository manhwaRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Manhwa> getAllManhwa() {
        return manhwaRepository.findAll();
    }

    public Optional<Manhwa> getManhwaById(Long id) {
        return manhwaRepository.findById(id);
    }

    public Manhwa createManhwa(Manhwa manhwa, Set<Long> categoryIds) {
        if (categoryIds != null && !categoryIds.isEmpty()) {
            List<Category> category = categoryRepository.findAllById(categoryIds);
            manhwa.setCategories(category);
        }

        return manhwaRepository.save(manhwa);
    }

}
