package ru.ncedu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.entity.Auto;
import ru.ncedu.repository.AutoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AutoServiceImp implements AutoService {

    private final AutoRepository autorepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Auto> getAllAuto() {
        return autorepository.findAll();
    }

    @Override
    public List<Auto> findAll(int pageNumber, int rowPerPage) {

        List<Auto> auto = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        autorepository.findAll(sortedByIdAsc).forEach(auto::add);
        return auto;
    }

    @Override
    public List<Auto> findAll() {

        List<Auto> auto = new ArrayList<>();
        autorepository.findAll().forEach(auto::add);
        return auto;
    }

    @Override
    public Auto save(Auto auto) {

        return autorepository.save(auto);
    }

    @Override
    public Long count() {

        return autorepository.count();
    }

    @Override
    public Auto findById(Long id) {

        Auto auto = autorepository.findById(id).orElse(null);
        return auto;
    }

    @Override
    public void update(Auto auto) {

        autorepository.save(auto);
    }

    @Override
    public void delete(Long id) {

        autorepository.deleteById(id);
    }
}