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

    public void unlikePhoto(User user) {
        Like like = Dao.findLikesByUserAndPhoto(user.getId(), id);
        likes.remove(like);
        like.setPhoto(null);
        Dao.create(like);
    }

    public void likePhoto(User user) {
        Like like = new Like();
        like.setUser(user);
        like.setPhoto(this);
        likes.add(like);
//        user.getLikes().add(like);  // ↑↓ both will throw: deleted object would be re-saved by cascade
        Dao.create(like);
    }

}
