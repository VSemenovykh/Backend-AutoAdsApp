package ru.ncedu.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ncedu.repository.CompareAutoRepository;
import ru.ncedu.service.DeleteCompareAutoService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeleteCompareAutoServiceImp implements DeleteCompareAutoService {

    private final CompareAutoRepository compareAutoRepository;

    @Override
    public void deleteCompareAuto(Long idAuto) {
        log.info("DeleteCompareAutoServiceImp -> deleteCompareAuto()");
        log.info("DeleteCompareAutoServiceImp -> idAuto: " + idAuto);
        compareAutoRepository.deleteByIdAuto(idAuto);
    }
}
