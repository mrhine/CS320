package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs320project.Contact;
import cs320project.ContactService;

import java.util.NoSuchElementException;

public class ContactServiceTest {

    private static final String VALID_CONTACT_ID = "C12345";
    private static final String VALID_FIRST_NAME = "John";
    private static final String VALID_LAST_NAME = "Doe";
    private static final String VALID_PHONE_NUMBER = "1234567890";
    private static final String VALID_ADDRESS = "123 Main St";
    private static final String CONTACT_ID_TOO_LONG = "C12345678901";
    private static final String FIRST_NAME_TOO_LONG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LAST_NAME_TOO_LONG = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String PHONE_NUMBER_TOO_SHORT = "12345";
    private static final String PHONE_NUMBER_TOO_LONG = "12345678901";
    private static final String PHONE_NUMBER_NON_DIGITS = "abc-def-gh";
    private static final String ADDRESS_TOO_LONG = "This is an excessively long address that goes way beyond thirty characters limit."; // >30 chars
    private static final String NON_EXISTENT_ID = "NonExistId";
    private static final String UPDATED_FIRST_NAME = "Jane";
    private static final String UPDATED_LAST_NAME = "Smith";
    private static final String UPDATED_PHONE_NUMBER = "0987654321";
    private static final String UPDATED_ADDRESS = "456 Oak Ave";


    private ContactService contactService;

    @BeforeEach
    void setUp() {
        contactService = new ContactService();
    }


    @Test
    void testAddContact_ValidContact_AddsNewContact() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);

        Contact retrievedContact = contactService.getContact(VALID_CONTACT_ID);
        assertNotNull(retrievedContact, "Contact should be retrieved");
        assertEquals(VALID_CONTACT_ID, retrievedContact.getContactId(), "Contact ID should match");
        assertEquals(VALID_FIRST_NAME, retrievedContact.getFirstName(), "First name should match");
        assertEquals(VALID_LAST_NAME, retrievedContact.getLastName(), "Last name should match");
        assertEquals(VALID_PHONE_NUMBER, retrievedContact.getPhoneNumber(), "Phone number should match");
        assertEquals(VALID_ADDRESS, retrievedContact.getAddress(), "Address should match");
    }

    @Test
    void testAddContact_ContactIdAlreadyExists_ThrowsIllegalArgumentException() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(VALID_CONTACT_ID, "Another", "Person", "1112223333", "Another Address");
        }, "Should throw IllegalArgumentException if contact ID already exists");
    }

    @Test
    void testAddContact_InvalidContactId_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(null, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for null contact ID");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact(CONTACT_ID_TOO_LONG, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for too long contact ID");
    }

    @Test
    void testAddContact_InvalidFirstName_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("C001", null, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for null first name");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("C002", FIRST_NAME_TOO_LONG, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for too long first name");
    }

    @Test
    void testAddContact_InvalidLastName_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("C003", VALID_FIRST_NAME, null, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for null last name");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("C004", VALID_FIRST_NAME, LAST_NAME_TOO_LONG, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for too long last name");
    }

    @Test
    void testAddContact_InvalidPhoneNumber_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("C005", VALID_FIRST_NAME, VALID_LAST_NAME, null, VALID_ADDRESS);
        }, "Should throw for null phone number");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("C006", VALID_FIRST_NAME, VALID_LAST_NAME, PHONE_NUMBER_TOO_SHORT, VALID_ADDRESS);
        }, "Should throw for too short phone number");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("C007", VALID_FIRST_NAME, VALID_LAST_NAME, PHONE_NUMBER_TOO_LONG, VALID_ADDRESS);
        }, "Should throw for too long phone number");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("C008", VALID_FIRST_NAME, VALID_LAST_NAME, PHONE_NUMBER_NON_DIGITS, VALID_ADDRESS);
        }, "Should throw for non-digit phone number");
    }

    @Test
    void testAddContact_InvalidAddress_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("C009", VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, null);
        }, "Should throw for null address");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.addContact("C010", VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, ADDRESS_TOO_LONG);
        }, "Should throw for too long address");
    }


    @Test
    void testDeleteContact_ExistingContact_RemovesContact() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        contactService.deleteContact(VALID_CONTACT_ID);
        assertNull(contactService.getContact(VALID_CONTACT_ID), "Contact should be null after deletion");
    }

    @Test
    void testDeleteContact_NonExistingContact_ThrowsNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            contactService.deleteContact(NON_EXISTENT_ID);
        }, "Should throw NoSuchElementException if contact does not exist");
    }


    @Test
    void testUpdateFirstName_ExistingContact_UpdatesFirstName() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        contactService.updateFirstName(VALID_CONTACT_ID, UPDATED_FIRST_NAME);

        Contact updatedContact = contactService.getContact(VALID_CONTACT_ID);
        assertNotNull(updatedContact, "Updated contact should not be null");
        assertEquals(UPDATED_FIRST_NAME, updatedContact.getFirstName(), "First name should be updated");
        // Ensure other fields remain unchanged
        assertEquals(VALID_LAST_NAME, updatedContact.getLastName());
        assertEquals(VALID_PHONE_NUMBER, updatedContact.getPhoneNumber());
        assertEquals(VALID_ADDRESS, updatedContact.getAddress());
    }

    @Test
    void testUpdateFirstName_NonExistingContact_ThrowsNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            contactService.updateFirstName(NON_EXISTENT_ID, UPDATED_FIRST_NAME);
        }, "Should throw NoSuchElementException for non-existent contact on update");
    }

    @Test
    void testUpdateFirstName_InvalidNewFirstName_ThrowsIllegalArgumentException() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateFirstName(VALID_CONTACT_ID, null);
        }, "Should throw for null new first name");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateFirstName(VALID_CONTACT_ID, FIRST_NAME_TOO_LONG);
        }, "Should throw for too long new first name");
    }


    @Test
    void testUpdateLastName_ExistingContact_UpdatesLastName() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        contactService.updateLastName(VALID_CONTACT_ID, UPDATED_LAST_NAME);

        Contact updatedContact = contactService.getContact(VALID_CONTACT_ID);
        assertNotNull(updatedContact, "Updated contact should not be null");
        assertEquals(UPDATED_LAST_NAME, updatedContact.getLastName(), "Last name should be updated");
        // Ensure other fields remain unchanged
        assertEquals(VALID_FIRST_NAME, updatedContact.getFirstName());
        assertEquals(VALID_PHONE_NUMBER, updatedContact.getPhoneNumber());
        assertEquals(VALID_ADDRESS, updatedContact.getAddress());
    }

    @Test
    void testUpdateLastName_NonExistingContact_ThrowsNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            contactService.updateLastName(NON_EXISTENT_ID, UPDATED_LAST_NAME);
        }, "Should throw NoSuchElementException for non-existent contact on update");
    }

    @Test
    void testUpdateLastName_InvalidNewLastName_ThrowsIllegalArgumentException() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateLastName(VALID_CONTACT_ID, null);
        }, "Should throw for null new last name");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateLastName(VALID_CONTACT_ID, LAST_NAME_TOO_LONG);
        }, "Should throw for too long new last name");
    }


    @Test
    void testUpdatePhoneNumber_ExistingContact_UpdatesPhoneNumber() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        contactService.updatePhoneNumber(VALID_CONTACT_ID, UPDATED_PHONE_NUMBER);

        Contact updatedContact = contactService.getContact(VALID_CONTACT_ID);
        assertNotNull(updatedContact, "Updated contact should not be null");
        assertEquals(UPDATED_PHONE_NUMBER, updatedContact.getPhoneNumber(), "Phone number should be updated");
        assertEquals(VALID_FIRST_NAME, updatedContact.getFirstName());
        assertEquals(VALID_LAST_NAME, updatedContact.getLastName());
        assertEquals(VALID_ADDRESS, updatedContact.getAddress());
    }

    @Test
    void testUpdatePhoneNumber_NonExistingContact_ThrowsNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            contactService.updatePhoneNumber(NON_EXISTENT_ID, UPDATED_PHONE_NUMBER);
        }, "Should throw NoSuchElementException for non-existent contact on update");
    }

    @Test
    void testUpdatePhoneNumber_InvalidNewPhoneNumber_ThrowsIllegalArgumentException() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhoneNumber(VALID_CONTACT_ID, null);
        }, "Should throw for null new phone number");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhoneNumber(VALID_CONTACT_ID, PHONE_NUMBER_TOO_SHORT);
        }, "Should throw for too short new phone number");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhoneNumber(VALID_CONTACT_ID, PHONE_NUMBER_TOO_LONG);
        }, "Should throw for too long new phone number");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updatePhoneNumber(VALID_CONTACT_ID, PHONE_NUMBER_NON_DIGITS);
        }, "Should throw for non-digit new phone number");
    }


    @Test
    void testUpdateAddress_ExistingContact_UpdatesAddress() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        contactService.updateAddress(VALID_CONTACT_ID, UPDATED_ADDRESS);

        Contact updatedContact = contactService.getContact(VALID_CONTACT_ID);
        assertNotNull(updatedContact, "Updated contact should not be null");
        assertEquals(UPDATED_ADDRESS, updatedContact.getAddress(), "Address should be updated");
        // Ensure other fields remain unchanged
        assertEquals(VALID_FIRST_NAME, updatedContact.getFirstName());
        assertEquals(VALID_LAST_NAME, updatedContact.getLastName());
        assertEquals(VALID_PHONE_NUMBER, updatedContact.getPhoneNumber());
    }

    @Test
    void testUpdateAddress_NonExistingContact_ThrowsNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            contactService.updateAddress(NON_EXISTENT_ID, UPDATED_ADDRESS);
        }, "Should throw NoSuchElementException for non-existent contact on update");
    }

    @Test
    void testUpdateAddress_InvalidNewAddress_ThrowsIllegalArgumentException() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateAddress(VALID_CONTACT_ID, null);
        }, "Should throw for null new address");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateAddress(VALID_CONTACT_ID, ADDRESS_TOO_LONG);
        }, "Should throw for too long new address");
    }


    @Test
    void testGetContact_ExistingContact_ReturnsContact() {
        contactService.addContact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        Contact retrievedContact = contactService.getContact(VALID_CONTACT_ID);
        assertNotNull(retrievedContact, "Should return a contact if it exists");
        assertEquals(VALID_CONTACT_ID, retrievedContact.getContactId());
    }

    @Test
    void testGetContact_NonExistingContact_ReturnsNull() {
        assertNull(contactService.getContact(NON_EXISTENT_ID), "Should return null if contact does not exist");
    }
}