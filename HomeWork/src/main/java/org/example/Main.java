package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            var contactDao = context.getBean(ContactDao.class);

            // Создание новых контактов
            Contact newContact1 = new Contact("John", "Doe", "john@example.com", "+123456789");
            Contact newContact2 = new Contact("JoJo", "Part1", "asda@gmail.com", "+123455213");
            Contact newContact3 = new Contact("VOVA", "IVaniv", "@example.com", "+1289");
            Contact newContact4 = new Contact("Ivan", "DMITRIEV", "example.com", "+456789");

            // Сохранение контактов
            long contactId1 = contactDao.saveContact(newContact1);
            long contactId2 = contactDao.saveContact(newContact2);
            long contactId3 = contactDao.saveContact(newContact3);
            long contactId4 = contactDao.saveContact(newContact4);

            // Получение контакта по ID
            Contact retrievedContact = contactDao.getContact(contactId2);
            System.out.println("Получен контакт: " + retrievedContact);

            // Обновление телефонного номера
            contactDao.updatePhone(contactId2, "+987654321");
            System.out.println("Телефонный номер обновлен");

            // Обновление email
            contactDao.updateEmail(contactId2, "updated@example.com");
            System.out.println("Email обновлен");

            // Получение всех контактов
            List<Contact> allContacts = contactDao.getAllContacts();
            System.out.println("Все контакты:");
            allContacts.forEach(System.out::println);

            // Удаление контакта
            contactDao.deleteContact(contactId1);
            System.out.println("Контакт удален");

            // Получение всех контактов после удаления
            List<Contact> allContactsAfterDeletion = contactDao.getAllContacts();
            System.out.println("Все контакты после удаления:");
            allContactsAfterDeletion.forEach(System.out::println);
        }
    }
}
