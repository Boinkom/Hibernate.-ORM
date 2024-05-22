package org.example;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ContactDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Добавление нового контакта
    public long saveContact(Contact contact) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var contactId = (Long) session.save(contact);
            transaction.commit();
            return contactId;
        }
    }

    // Получение контакта по идентификатору
    public Contact getContact(long contactId) {
        try (var session = sessionFactory.openSession()) {
            return session.get(Contact.class, contactId);
        }
    }

    // Получение всех контактов
    public List<Contact> getAllContacts() {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("from Contact", Contact.class).list();
        }
    }

    // Обновление контакта
    public void updateContact(Contact contact) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.update(contact);
            transaction.commit();
        }
    }

    // Обновление телефонного номера по идентификатору контакта
    public void updatePhone(long contactId, String newPhone) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var contact = session.get(Contact.class, contactId);
            if (contact != null) {
                contact.setPhone(newPhone);
                session.update(contact);
            }
            transaction.commit();
        }
    }

    // Обновление email по идентификатору контакта
    public void updateEmail(long contactId, String newEmail) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var contact = session.get(Contact.class, contactId);
            if (contact != null) {
                contact.setEmail(newEmail);
                session.update(contact);
            }
            transaction.commit();
        }
    }

    // Удаление контакта по идентификатору
    public void deleteContact(long contactId) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var contact = session.get(Contact.class, contactId);
            if (contact != null) {
                session.delete(contact);
            }
            transaction.commit();
        }
    }
}
