package org.cb.category.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * This Entity class is maintaining Categories to maintain products and Brands that in which
 * categories are there like Ex: Audio, Electronics and Computer & Appliances
 *
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String alias;

    private int parentCategory;

    /**
     * Image should be uploaded on cloud s3 bucket and url
     * should be store in this image field
     */
    private String image;

    private boolean enabled;

    /**
     *
     *    Audit Information will also be stored like
     *    who has created Category and Who has modified
     *
     */

    private String createdBy;

    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createdOn;

    private String updatedBy;

    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime updatedOn;

}
