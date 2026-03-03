package com;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private LocalDate joinDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Album> albums = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Like> likes = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends = new HashSet<>();

    public User(String name) {
        this.name = name;
    }

    public User(String name, Set<Album> albums) {
        this.name = name;
        this.albums = albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public void likePhoto(Photo photo) {
//        friends.stream()
//                .filter(friend-> friend.albums.stream()
//                        .anyMatch(album -> album.getPhotos().contains(photo)))
//                .findFirst()
//                .orElseThrow(()->new IllegalArgumentException("You can't like photo of a user who is not your friend."));
        Like like = new Like();
        like.setUser(this);
        like.setPhoto(photo);
        Dao.create(like);
        likes.add(like);
    }

    @PrePersist
    public void prePersist() {
        joinDate = LocalDate.now();
    }
}
