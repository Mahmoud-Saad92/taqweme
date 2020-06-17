package eg.bazinga.taqweme.services.contact;

import eg.bazinga.taqweme.domains.Contact;
import eg.bazinga.taqweme.exceptions.ResourceCannotCreatedException;
import eg.bazinga.taqweme.exceptions.ResourceNotFoundException;
import eg.bazinga.taqweme.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository repository;

    @Autowired
    public ContactServiceImpl(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contact createContact(Contact contact) throws ResourceCannotCreatedException {
        Contact savedContact;

        try {
            savedContact = repository.save(contact);
        } catch (Exception e) {
            throw new ResourceCannotCreatedException("Contact Cannot be Created!!");
        }

        return savedContact;
    }

    @Override
    public Set<Contact> getAllContacts() {
        return new HashSet<>(repository.findAll(Sort.by(Sort.Order.asc("id"))));
    }

    @Override
    public Contact getContactById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not found for this id :: " + id));
    }

    @Override
    public Contact updateContact(Long id, Contact contact) throws ResourceNotFoundException {
        Contact oldContact = getContactById(id);

        oldContact.setName(contact.getName());

        return repository.save(oldContact);
    }

    @Override
    public void deleteContact(Long id) throws ResourceNotFoundException {
        repository.delete(getContactById(id));
    }
}
