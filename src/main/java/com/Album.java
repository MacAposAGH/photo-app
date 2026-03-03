package com;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "album_id")
    private Set<Photo> photos = new HashSet<>();

    public Album(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Album(String name, String description, Set<Photo> photos) {
        this.name = name;
        this.description = description;
        this.photos = new HashSet<>(photos);
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    public void removePhoto(Photo photo) {
        photos.remove(photo);
        Dao.create(photo);
//        Dao.findLikesByPhotoId(photo.getId()).forEach(
//                like -> {
////                    photo.removeLike(like);
//                    like.getUser().unlikePhoto(like);
//                }
//        );
    }

}