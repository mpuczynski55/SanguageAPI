package jpwp.jpwpproject.service;

import jpwp.jpwpproject.model.ConfirmationToken;
import jpwp.jpwpproject.model.User;
import jpwp.jpwpproject.repo.ConfirmationTokenRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepo confirmationTokenRepo;

    public ConfirmationTokenService(ConfirmationTokenRepo confirmationTokenRepo) {
        this.confirmationTokenRepo = confirmationTokenRepo;
    }

    public Iterable<ConfirmationToken> findAll() {
        return confirmationTokenRepo.findAll();
    }

    public ConfirmationToken save(ConfirmationToken token) {
        return confirmationTokenRepo.save(token);
    }

    public Optional<ConfirmationToken> findByToken(String token) {
        return confirmationTokenRepo.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        Optional<ConfirmationToken> foundToken = findByToken(token);
        if (foundToken.isPresent()) {
            ConfirmationToken confirmationTokenUpdate = foundToken.get();
            confirmationTokenUpdate.setConfirmedAt(LocalDateTime.now());
            save(confirmationTokenUpdate);
        }
    }

    public String generateToken(User user) {
        String token = UUID.randomUUID().toString();
        System.out.println(token);
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        save(confirmationToken);
        return token;
    }

}
