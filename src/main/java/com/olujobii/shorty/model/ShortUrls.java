package com.olujobii.shorty.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortCode;

    private String url;

    private Boolean status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "shortUrls", cascade = CascadeType.ALL)
    private OriginalUrls originalUrls;

    //must tell both sides about the change
    public ShortUrls setOriginalUrls(OriginalUrls originalUrls){
        this.originalUrls = originalUrls;

        if(this.originalUrls != null){
            this.originalUrls.setShortUrls(this);
        }
        return this;
    }

    public void removeOriginalUrls(){
        if(this.originalUrls != null){
            this.originalUrls.setShortUrls(null);
        }

        this.originalUrls = null;
    }
}