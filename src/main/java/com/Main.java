package com;

import java.time.LocalDate;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        Photo photo1 = new Photo("photo1", today.minusDays(1));
        Photo photo2 = new Photo("photo2", today.minusDays(1));
        Photo photo3 = new Photo("photo3", today.minusDays(2));
        Photo photo4 = new Photo("photo4", today.minusDays(2));
        Photo photo5 = new Photo("photo5", today.minusDays(3));
        Photo photo6 = new Photo("photo6", today.minusDays(3));
        Dao.create(photo1);
        Dao.create(photo2);
        Dao.create(photo3);
        Dao.create(photo4);
        Dao.create(photo5);
        Dao.create(photo6);

        Album album1 = new Album("album1", "description", Set.of(photo1, photo2));
        Album album2 = new Album("album2", "description", Set.of(photo3));
        Album album3 = new Album("album3", "description", Set.of(photo4, photo5));
        Album album4 = new Album("album4", "description", Set.of(photo6));
        Dao.create(album1);
        Dao.create(album2);
        Dao.create(album3);
        Dao.create(album4);

        User user1 = new User("John Doe", Set.of(album1, album2));
        User user2 = new User("Jane Doe", Set.of(album3));
        User user3 = new User("Jack Doe", Set.of(album4));
        Dao.create(user1);
        Dao.create(user2);
        Dao.create(user3);
        user1.addFriend(user2);

        user1.printFriends();
        try {
            System.out.println("\nUser can't like a photo of a user if they aren't friends.");
            user1.likePhoto(photo6);
        } catch (IllegalArgumentException exception) {
            System.out.println("-> " + exception.getMessage());
        }

        System.out.println("\nUser can only like a photo of a user if they are friends.");
        user1.likePhoto(photo4);
        user1.likePhoto(photo5);
        user1.printLikes();

        user1.unlikePhoto(photo4);
        System.out.println("\nDeleting like won't affect other entities. After deletion:");
        user1.printLikes();

        album3.removePhoto(photo5);
        System.out.printf("""
                \nDeleting photo will delete related likes. After deletion:
                -> photo5: %s
                """,  Dao.findById(Photo.class, 5));
        user1.printLikes();

        user1.removeAlbum(album1);
        System.out.printf("""
                \nDeleting album will also delete its photos.
                -> album1: %s
                -> photo1: %s
                -> photo2: %s
                """, Dao.findById(Album.class, 1), Dao.findById(Photo.class, 1), Dao.findById(Photo.class, 2));

        Dao.delete(user1);
        System.out.printf("""
                \nDeleting user will also delete its albums and photos.
                -> user1: %s
                -> album1: %s
                """, Dao.findById(User.class, 1), Dao.findById(Album.class, 2));
    }
}