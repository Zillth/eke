package com.tamalitos.oaxaque.os.eke.services;

import com.tamalitos.oaxaque.os.eke.exceptions.PublicationCreateException;
import com.tamalitos.oaxaque.os.eke.exceptions.PublicationNotFoundException;
import com.tamalitos.oaxaque.os.eke.models.Publication;
import com.tamalitos.oaxaque.os.eke.models.PublicationCreation;
import com.tamalitos.oaxaque.os.eke.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;

    PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public List<Publication> getPublications() throws SQLException {
        return this.publicationRepository.getPublications();
    }

    public Publication getPublicationById(int id) throws SQLException, PublicationNotFoundException {
        return this.publicationRepository.getPublicationById(id);
    }

    public void createPublication(PublicationCreation publication) throws PublicationCreateException {
        this.publicationRepository.createPublication(
                publication.getTitle(), publication.getDescription(), publication.getPublication_date());
    }

    public void updatePublication(Publication publication) throws PublicationNotFoundException {
        this.publicationRepository.updatePublication(
                publication.getId(), publication.getTitle(), publication.getDescription(), publication.getPublication_date());
    }

    public void deletePublication(int id) throws PublicationNotFoundException {
        this.publicationRepository.deletePublication(id);
    }
}
