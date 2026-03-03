package com;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Album> albums = new HashSet<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    private Set<Like> likes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends = new HashSet<>();

    public User(String name, Set<Album> albums) {
        this.name = name;
        this.albums = albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void addFriend(User user) {
        user.friends.add(this);
        friends.add(user);
    }

    public void removeFriend(User user) {
        friends.remove(user);
        Dao.create(this);
    }

    public void likePhoto(
            Photo photo
    ) {
//        friends.stream()
//                .filter(friend -> friend.albums.stream()
//                        .anyMatch(album -> album.getPhotos().contains(photo)))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("You can't like photo of a user who is not your friend."));
        Like like = new Like();
        like.setUser(this);
        like.setPhoto(photo);
        likes.add(like);
        Dao.create(like);
    }

    public void unlikePhoto(Photo photo) {
        Like like = Dao.findLikesByUserAndPhoto(id, photo.getId());
        likes.remove(like);
//        photo.getLikes().remove(like);
        like.setUser(null);
//        like.setPhoto(null);
        Dao.create(like);

    }

    public void printFriends() {
        System.out.printf("%s is friend with:\n", name);
        friends.forEach(f -> System.out.printf("\t%s\n", f.getName()));
    }

//    public void printLikes() {
//        System.out.printf("%s likes:\n", name);
//        likes.forEach(like -> {
//            Photo photo = like.getPhoto();
//            System.out.printf("\t%s of user %s\n", photo.getName(), Dao.findUserByPhotoId(photo.getId()).getName());
//        });
//    }

    @PrePersist
    public void prePersist() {
        joinDate = LocalDate.now();
    }
}
