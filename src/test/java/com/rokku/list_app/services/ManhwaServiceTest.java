package com.rokku.list_app.services;

import com.rokku.list_app.models.Category;
import com.rokku.list_app.models.Manhwa;
import com.rokku.list_app.repository.CategoryRepository;
import com.rokku.list_app.repository.ManhwaRepository;
import com.rokku.list_app.service.ManhwaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ManhwaServiceTest {
    @Mock
    private ManhwaRepository manhwaRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private ManhwaService manhwaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateManhwaWithoutCategories() {
        Manhwa manhwa = Manhwa.builder().title("New Manhwa").chapterCount(10).isOngoing(true).author("Author Name").categories(Collections.emptyList()).build();

        when(manhwaRepository.save(any(Manhwa.class))).thenReturn(manhwa);

        Manhwa savedManhwa = manhwaService.createManhwa(manhwa, null);

        assertNotNull(savedManhwa);
        assertEquals("New Manhwa", savedManhwa.getTitle());
        verify(manhwaRepository, times(1)).save(manhwa);
        assertTrue(savedManhwa.getCategories().isEmpty());
    }

    @Test
    void testCreateManhwaWithCategories() {
        Category category1 = new Category(1L, "Action", Collections.emptyList());
        Category category2 = new Category(2L, "Adventure", Collections.emptyList());

        Manhwa manhwa = Manhwa.builder()
                .title("Another Manhwa")
                .chapterCount(20)
                .isOngoing(false)
                .author("Another Author")
                .categories(Collections.emptyList())
                .build();

        Set<Long> categoryIds = Set.of(1L, 2L);

        when(categoryRepository.findAllById(categoryIds)).thenReturn(List.of(category1, category2));
        when(manhwaRepository.save(any(Manhwa.class))).thenReturn(manhwa);

        Manhwa savedManhwa = manhwaService.createManhwa(manhwa, categoryIds);

        assertNotNull(savedManhwa);
        assertEquals("Another Manhwa", savedManhwa.getTitle());
        assertEquals(2, savedManhwa.getCategories().size());
        verify(manhwaRepository, times(1)).save(manhwa);
        verify(categoryRepository, times(1)).findAllById(categoryIds);
    }
}
