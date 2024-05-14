package com.example.hotrotinhthue.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "anh_minhchung")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnhMinhChung {
    public static final int MAXIMUM_IMAGES_PER_PRODUCT = 6;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tokhai_id")
    @JsonIgnore
    private ToKhaiThue tokhaithue;

    @Column(name = "image_url", length = 300)
    private String imageUrl;
}
