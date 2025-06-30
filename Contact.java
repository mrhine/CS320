package cs320project;

import java.util.Objects;

public class Contact {

    private final String contactId; // Make contactId final as it's permanent
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phoneNumber, String address) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Contact ID cannot be null or more than 10 characters.");
        }
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("First name cannot be null or more than 10 characters.");
        }
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name cannot be null or more than 10 characters.");
        }
        if (phoneNumber == null || phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Phone number cannot be null and must be exactly 10 digits.");
        }
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address cannot be null or more than 30 characters.");
        }

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getContactId() {
        return contactId; // No setter as contactId is permanent
    }

    /**
     * Accessor for firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Mutator for firstName
     *
     * @throws IllegalArgumentException if the firstName is null or greater than 10 characters
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("First name cannot be null or more than 10 characters.");
        }
        this.firstName = firstName;
    }

    /**
     * Accessor for lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Mutator for lastName
     *
     * @throws IllegalArgumentException if the lastName is null or greater than 10 characters
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Last name cannot be null or more than 10 characters.");
        }
        this.lastName = lastName;
    }

    /**
     * Accessor for phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Mutator for phoneNumber
     *
     * @throws IllegalArgumentException if the phoneNumber is null or not 10 digits
     */
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Phone number cannot be null and must be exactly 10 digits.");
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Accessor for Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Mutator for Address
     *
     * @throws IllegalArgumentException if address is null or more than 30 characters
     */
    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address cannot be null or more than 30 characters.");
        }
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId) &&
               Objects.equals(firstName, contact.firstName) &&
               Objects.equals(lastName, contact.lastName) &&
               Objects.equals(phoneNumber, contact.phoneNumber) &&
               Objects.equals(address, contact.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, firstName, lastName, phoneNumber, address);
    }
}