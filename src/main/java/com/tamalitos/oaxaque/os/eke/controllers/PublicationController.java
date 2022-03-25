package com.tamalitos.oaxaque.os.eke.controllers;

import com.tamalitos.oaxaque.os.eke.exceptions.PublicationCreateException;
import com.tamalitos.oaxaque.os.eke.exceptions.PublicationNotFoundException;
import com.tamalitos.oaxaque.os.eke.models.Publication;
import com.tamalitos.oaxaque.os.eke.models.PublicationCreation;
import com.tamalitos.oaxaque.os.eke.services.PublicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/publication")
public class PublicationController {
    private final PublicationService publicationService;

    PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping
    public ResponseEntity getPublications() {
        List<Publication> publications = null;
        try {
            publications = this.publicationService.getPublications();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(publications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPublication(@PathVariable("id") int id) {
        Publication publication = null;
        try {
            publication = this.publicationService.getPublicationById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } catch (PublicationNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(publication, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createPublication(@RequestBody PublicationCreation publication) {
        publication.getPublication_date().setTime(publication.getPublication_date().getTime() + 100000000);
        try {
            this.publicationService.createPublication(publication);
        } catch (PublicationCreateException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updatePublication(@RequestBody Publication publication) {
        publication.getPublication_date().setTime(publication.getPublication_date().getTime() + 100000000);
        try {
            this.publicationService.updatePublication(publication);
        } catch (PublicationNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePublication(@PathVariable("id") int id) {
        try {
            this.publicationService.deletePublication(id);
        } catch (PublicationNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
