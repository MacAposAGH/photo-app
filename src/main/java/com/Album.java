package com;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Photo> photos;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Album(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Album(String name, String description, Set<Photo> photos) {
        this.name = name;
        this.description = description;
        this.photos = photos;
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

}