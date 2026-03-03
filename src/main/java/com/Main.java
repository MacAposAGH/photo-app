package com;

import java.time.LocalDate;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
//        PhotoTest photoTest1 = new PhotoTest("photo1", LocalDate.now());
//        PhotoTest photoTest2 = new PhotoTest("photo2", LocalDate.now());
//        Dao.create(photoTest1);

//        Album album1 = new Album("album1", "description", Set.of(photoTest1));
//        Album album2 = new Album("album1", "description", Set.of(photoTest2));

        LocalDate today = LocalDate.now();
        Photo photo1 = new Photo("photo1", today.minusDays(1));
        Photo photo2 = new Photo("photo2", today.minusDays(1));
        Photo photo3 = new Photo("photo3", today.minusDays(2));
        Photo photo4 = new Photo("photo4", today.minusDays(2));
        Photo photo5 = new Photo("photo5", today.minusDays(3));
        Photo photo6 = new Photo("photo6", today.minusDays(3));
        Photo photo7 = new Photo("photo7", today.minusDays(4));
        Photo photo8 = new Photo("photo8", today.minusDays(4));
        Photo photo9 = new Photo("photo9", today.minusDays(4));
        Dao.create(photo1);
        Dao.create(photo2);
        Dao.create(photo3);
        Dao.create(photo4);
        Dao.create(photo5);
        Dao.create(photo6);
        Dao.create(photo7);
        Dao.create(photo8);
        Dao.create(photo9);

        Album album1 = new Album("album1", "description", Set.of(photo1, photo2));
        Album album2 = new Album("album2", "description", Set.of(photo3, photo4));
        Album album3 = new Album("album3", "description", Set.of(photo5, photo6));
        Album album4 = new Album("album4", "description", Set.of(photo7, photo8));
        Dao.create(album1);
        Dao.create(album2);
        Dao.create(album3);
        Dao.create(album4);

        User user1 = new User("John Doe", Set.of(album1));
        User user2 = new User("Jane Doe", Set.of(album2, album3));
        User user3 = new User("Jack Doe", Set.of(album4));
        Dao.create(user1);
        Dao.create(user2);
        Dao.create(user3);
        user1.addFriend(user2);

        user1.likePhoto(photo1);
        user2.likePhoto(photo1);
        user1.likePhoto(photo9);
        user1.unlikePhoto(photo1);

        album1.removePhoto(photo1);
        user1.removeAlbum(album1);
        Dao.delete(user1);

//        user1.printFriends();
//        try {
//            System.out.println("\nUser can't like a photo of a user if they aren't friends.");
//            user1.likePhoto(photo7);
//        } catch (IllegalArgumentException exception) {
//            System.out.println("-> " + exception.getMessage());
//        }
//
//        System.out.println("\nUser can only like a photo of a user if they are friends.");
//        user1.likePhoto(photo5);
//        user1.likePhoto(photo6);
//        user1.printLikes();
//
//        user1.unlikePhoto(Dao.findById(Like.class, 1));
//        System.out.println("\nDeleting like won't affect other entities. After deletion:");
//        user1.printLikes();

//        Dao.deleteById(User.class, 1);
//        Photo photo = Dao.findById(Photo.class, 6);
//        album3.removePhoto(photo);

    }
}