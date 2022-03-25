package com.tamalitos.oaxaque.os.eke.models;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PublicationCreation {
    private String title;
    private String description;
    private Date publication_date;
}
