package jpwp.jpwpproject.service;

import jpwp.jpwpproject.model.KnownTranslations;
import jpwp.jpwpproject.repo.KnownTranslationsRepo;
import org.springframework.stereotype.Service;

@Service
public class KnownTranslationsService {
    private final KnownTranslationsRepo KnownTranslationsRepo;

    public KnownTranslationsService(KnownTranslationsRepo KnownTranslationsRepo) {
        this.KnownTranslationsRepo = KnownTranslationsRepo;
    }

    public void save(KnownTranslations knownWord) {
        KnownTranslationsRepo.save(knownWord);
    }

}
