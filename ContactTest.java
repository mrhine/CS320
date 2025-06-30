package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cs320project.Contact;

public class ContactTest {

    private static final String VALID_CONTACT_ID = "ID123";
    private static final String VALID_FIRST_NAME = "Amy";
    private static final String VALID_LAST_NAME = "Adams";
    private static final String VALID_PHONE_NUMBER = "1234567890";
    private static final String VALID_ADDRESS = "1234 First Street";
    private static final String CONTACT_ID_TOO_LONG = "ID1234567890";
    private static final String FIRST_NAME_TOO_LONG = "AmyAmyAmyAmyA";
    private static final String LAST_NAME_TOO_LONG = "AdamsAdamsAda";
    private static final String PHONE_NUMBER_TOO_SHORT = "123456789";
    private static final String PHONE_NUMBER_TOO_LONG = "12345678901";
    private static final String PHONE_NUMBER_NON_DIGIT = "123456789a";
    private static final String ADDRESS_TOO_LONG = "1234567890123456789012345678901";
    private static final String UPDATED_FIRST_NAME = "Betty";
    private static final String UPDATED_LAST_NAME = "White";
    private static final String UPDATED_PHONE_NUMBER = "0987654321";
    private static final String UPDATED_ADDRESS = "5678 Second Avenue";
    private static final String SAME_CONTACT_ID = "SameID";
    private static final String DIFFERENT_CONTACT_ID_ONE = "DiffID1";
    private static final String DIFFERENT_CONTACT_ID_TWO = "DiffID2";

    @Test
    void testContactCreation_ValidData_SetsFieldsCorrectly() {
        Contact contact = new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        assertEquals(VALID_CONTACT_ID, contact.getContactId(), "Contact ID should be set correctly");
        assertEquals(VALID_FIRST_NAME, contact.getFirstName(), "First Name should be set correctly");
        assertEquals(VALID_LAST_NAME, contact.getLastName(), "Last Name should be set correctly");
        assertEquals(VALID_PHONE_NUMBER, contact.getPhoneNumber(), "Phone Number should be set correctly");
        assertEquals(VALID_ADDRESS, contact.getAddress(), "Address should be set correctly");
    }

    @Test
    void testContactCreation_InvalidContactId_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for null Contact ID");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(CONTACT_ID_TOO_LONG, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for too long Contact ID");
    }

    @Test
    void testContactCreation_InvalidFirstName_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_CONTACT_ID, null, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for null First Name");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_CONTACT_ID, FIRST_NAME_TOO_LONG, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for too long First Name");
    }

    @Test
    void testContactCreation_InvalidLastName_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, null, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for null Last Name");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, LAST_NAME_TOO_LONG, VALID_PHONE_NUMBER, VALID_ADDRESS);
        }, "Should throw for too long Last Name");
    }

    @Test
    void testContactCreation_InvalidPhoneNumber_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, null, VALID_ADDRESS);
        }, "Should throw for null Phone Number");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, PHONE_NUMBER_TOO_SHORT, VALID_ADDRESS);
        }, "Should throw for too short Phone Number");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, PHONE_NUMBER_TOO_LONG, VALID_ADDRESS);
        }, "Should throw for too long Phone Number");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, PHONE_NUMBER_NON_DIGIT, VALID_ADDRESS);
        }, "Should throw for non-digit Phone Number");
    }

    @Test
    void testContactCreation_InvalidAddress_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, null);
        }, "Should throw for null Address");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, ADDRESS_TOO_LONG);
        }, "Should throw for too long Address");
    }

    @Test
    void testSetFirstName_ValidData_UpdatesFirstName() {
        Contact contact = new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        contact.setFirstName(UPDATED_FIRST_NAME);
        assertEquals(UPDATED_FIRST_NAME, contact.getFirstName(), "First name should be updated");
    }

    @Test
    void testSetFirstName_InvalidData_ThrowsIllegalArgumentException() {
        Contact contact = new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName(null);
        }, "Should throw for null First Name on setter");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName(FIRST_NAME_TOO_LONG);
        }, "Should throw for too long First Name on setter");
    }

    @Test
    void testSetLastName_ValidData_UpdatesLastName() {
        Contact contact = new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        contact.setLastName(UPDATED_LAST_NAME);
        assertEquals(UPDATED_LAST_NAME, contact.getLastName(), "Last name should be updated");
    }

    @Test
    void testSetLastName_InvalidData_ThrowsIllegalArgumentException() {
        Contact contact = new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName(null);
        }, "Should throw for null Last Name on setter");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName(LAST_NAME_TOO_LONG);
        }, "Should throw for too long Last Name on setter");
    }

    @Test
    void testSetPhoneNumber_ValidData_UpdatesPhoneNumber() {
        Contact contact = new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        contact.setPhoneNumber(UPDATED_PHONE_NUMBER);
        assertEquals(UPDATED_PHONE_NUMBER, contact.getPhoneNumber(), "Phone number should be updated");
    }

    @Test
    void testSetPhoneNumber_InvalidData_ThrowsIllegalArgumentException() {
        Contact contact = new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhoneNumber(null);
        }, "Should throw for null Phone Number on setter");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhoneNumber(PHONE_NUMBER_TOO_SHORT);
        }, "Should throw for too short Phone Number on setter");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhoneNumber(PHONE_NUMBER_TOO_LONG);
        }, "Should throw for too long Phone Number on setter");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhoneNumber(PHONE_NUMBER_NON_DIGIT);
        }, "Should throw for non-digit Phone Number on setter");
    }

    @Test
    void testSetAddress_ValidData_UpdatesAddress() {
        Contact contact = new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        contact.setAddress(UPDATED_ADDRESS);
        assertEquals(UPDATED_ADDRESS, contact.getAddress(), "Address should be updated");
    }

    @Test
    void testSetAddress_InvalidData_ThrowsIllegalArgumentException() {
        Contact contact = new Contact(VALID_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(null);
        }, "Should throw for null Address on setter");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(ADDRESS_TOO_LONG);
        }, "Should throw for too long Address on setter");
    }

    @Test
    void testEquals_SameContactId_ReturnsTrue() {
        Contact contact1 = new Contact(SAME_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        Contact contact3 = new Contact(SAME_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        assertEquals(contact1, contact3, "Contacts with same ID and all fields should be equal");
    }

    @Test
    void testEquals_DifferentContactId_ReturnsFalse() {
        Contact contact1 = new Contact(DIFFERENT_CONTACT_ID_ONE, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        Contact contact2 = new Contact(DIFFERENT_CONTACT_ID_TWO, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        assertNotEquals(contact1, contact2, "Contacts with different IDs should not be equal");
    }

    @Test
    void testEquals_DifferentFieldsSameId_ReturnsFalse() {
        Contact contact1 = new Contact(SAME_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        Contact contact2 = new Contact(SAME_CONTACT_ID, UPDATED_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS); // Only first name differs

        assertNotEquals(contact1, contact2, "Contacts with same ID but different fields should not be equal");
    }

    @Test
    void testHashCode_SameContactIdAndFields_ReturnsSameHashCode() {
        Contact contact1 = new Contact(SAME_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        Contact contact2 = new Contact(SAME_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        assertEquals(contact1.hashCode(), contact2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    void testHashCode_DifferentContactId_ReturnsDifferentHashCode() {
        Contact contact1 = new Contact(DIFFERENT_CONTACT_ID_ONE, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        Contact contact2 = new Contact(DIFFERENT_CONTACT_ID_TWO, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        assertNotEquals(contact1.hashCode(), contact2.hashCode(), "Hash codes should be different for unequal objects");
    }

    @Test
    void testHashCode_DifferentFieldsSameId_ReturnsDifferentHashCode() {
        Contact contact1 = new Contact(SAME_CONTACT_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);
        Contact contact2 = new Contact(SAME_CONTACT_ID, UPDATED_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE_NUMBER, VALID_ADDRESS);

        assertNotEquals(contact1.hashCode(), contact2.hashCode(), "Hash codes should be different for objects with same ID but different fields");
    }
}