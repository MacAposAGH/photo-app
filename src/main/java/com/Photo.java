package com;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private LocalDate date;

    @OneToMany(mappedBy = "photo",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private Set<Like> likes = new HashSet<>();

    public Photo(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

//    public void addLike(Like like) {
//        Like like = new Like();
//        like.setPhoto(this);
//        like.setUser(user);
//        likes.add(like);
//        Dao.create(like);
//    }

//    public void addLike(User user) {
//        Like like = new Like();
//        like.setPhoto(this);
//        like.setUser(user);
//        likes.add(like);
//        Dao.create(like);
//    }

}
