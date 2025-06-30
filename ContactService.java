package cs320project;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ContactService {

    private final Map<String, Contact> contacts;

    public ContactService() {
        this.contacts = new HashMap<>();
    }

    /**
     * Adds a new contact to the service.
     *
     * @param contactId The unique identifier for the contact.
     * @param firstName The first name of the contact.
     * @param lastName The last name of the contact.
     * @param phoneNumber The phone number of the contact.
     * @param address The address of the contact.
     * @throws IllegalArgumentException If the contactId is null, too long, or already exists.
     * @throws IllegalArgumentException If any other contact field (first name, last name, phone number, address)
     * is invalid according to the Contact class's rules.
     */
    public void addContact(String contactId, String firstName, String lastName, String phoneNumber, String address) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Contact ID cannot be null or more than 10 characters.");
        }
        if (contacts.containsKey(contactId)) {
            throw new IllegalArgumentException(contactId + " already exists.");
        }

        Contact newContact = new Contact(contactId, firstName, lastName, phoneNumber, address);
        contacts.put(contactId, newContact);
    }

    /**
     * Deletes a contact based on its ID.
     *
     * @param contactId The unique identifier of the contact to be deleted.
     * @throws NoSuchElementException If the contact with the specified contactId does not exist.
     */
    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new NoSuchElementException(contactId + " does not exist.");
        }
        contacts.remove(contactId);
    }

    /**
     * Updates the first name of a contact.
     *
     * @param contactId    The unique identifier of the contact to update.
     * @param newFirstName The new first name for the contact.
     * @throws NoSuchElementException If the contact with the specified contactId does not exist.
     * @throws IllegalArgumentException If the new first name is invalid according to the Contact class's rules.
     */
    public void updateFirstName(String contactId, String newFirstName) {
        Contact contact = getExistingContact(contactId);
        contact.setFirstName(newFirstName);
    }

    /**
     * Updates the last name of a contact.
     *
     * @param contactId   The unique identifier of the contact to update.
     * @param newLastName The new last name for the contact.
     * @throws NoSuchElementException If the contact with the specified contactId does not exist.
     * @throws IllegalArgumentException If the new last name is invalid according to the Contact class's rules.
     */
    public void updateLastName(String contactId, String newLastName) {
        Contact contact = getExistingContact(contactId);
        contact.setLastName(newLastName);
    }

    /**
     * Updates the phone number of a contact.
     *
     * @param contactId     The unique identifier of the contact to update.
     * @param newPhoneNumber The new phone number for the contact.
     * @throws NoSuchElementException If the contact with the specified contactId does not exist.
     * @throws IllegalArgumentException If the new phone number is invalid according to the Contact class's rules.
     */
    public void updatePhoneNumber(String contactId, String newPhoneNumber) {
        Contact contact = getExistingContact(contactId);
        contact.setPhoneNumber(newPhoneNumber);
    }

    /**
     * Updates the address of a contact.
     *
     * @param contactId  The unique identifier of the contact to update.
     * @param newAddress The new address for the contact.
     * @throws NoSuchElementException If the contact with the specified contactId does not exist.
     * @throws IllegalArgumentException If the new address is invalid according to the Contact class's rules.
     */
    public void updateAddress(String contactId, String newAddress) {
        Contact contact = getExistingContact(contactId); // Use private helper method
        contact.setAddress(newAddress);
    }

    /**
     * Retrieves a contact by its unique identifier.
     *
     * @param contactId The unique identifier of the contact to retrieve.
     * @return The Contact object if found, or null if no contact with the given ID exists.
     */
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    /**
     * Retrieves an existing contact by its unique identifier.
     * This is a private helper method used by update operations.
     *
     * @param contactId The unique identifier of the contact to find.
     * @return The Contact object.
     * @throws NoSuchElementException If the contact with the specified contactId does not exist.
     */
    private Contact getExistingContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new NoSuchElementException(contactId + " not found.");
        }
        return contact;
    }
}