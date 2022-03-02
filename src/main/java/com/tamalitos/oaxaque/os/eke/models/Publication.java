package com.tamalitos.oaxaque.os.eke.models;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Publication {
    private BigInteger id;
    private String title;
    private String description;
    private Date publication_date;
}
