package eg.bazinga.taqweme.services.contact;

import eg.bazinga.taqweme.domains.Contact;
import eg.bazinga.taqweme.exceptions.ResourceCannotCreatedException;
import eg.bazinga.taqweme.exceptions.ResourceNotFoundException;

import java.util.Set;

public interface ContactService {

    Contact createContact(Contact contact) throws ResourceCannotCreatedException;

    Set<Contact> getAllContacts();

    Contact getContactById(Long id) throws ResourceNotFoundException;

    Contact updateContact(Long id, Contact contact) throws ResourceNotFoundException;

    void deleteContact(Long id) throws ResourceNotFoundException;
}
