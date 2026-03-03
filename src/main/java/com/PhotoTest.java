//package com;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "photoTests")
//public class PhotoTest {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column
//    private String name;
//
//    @Column
//    private LocalDate date;
//
//    @OneToMany(mappedBy = "photoTest",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    @ToString.Exclude
//    private Set<Like> likes = new HashSet<>();
//
//    public PhotoTest(String name, LocalDate date) {
//        this.name = name;
//        this.date = date;
//    }
//
//    public void removeLike(Like like) {
//        likes.remove(like);
//        like.setPhotoTest(null);
//        Dao.create(like);
//    }
//
//    public void addLike(User user) {
//        Like like = new Like();
//        like.setPhotoTest(this);
//        like.setUser(user);
//        likes.add(like);
//        Dao.create(like);
//    }
//}
