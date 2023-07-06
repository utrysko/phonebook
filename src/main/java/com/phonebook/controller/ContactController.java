package com.phonebook.controller;

import com.phonebook.domain.Contact;
import com.phonebook.dto.ContactDTO;
import com.phonebook.service.impl.ContactServiceImpl;
import com.phonebook.util.FileUploadUtil;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * Class represents RestController for contacts.
 *
 * @author Vasyl Utrysko.
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController extends BaseController{

    private final ContactServiceImpl contactService;

    /**
     * A method to retrieve user contacts
     *
     * @param userId getting userId from session
     * @param page getting from request if client don't provided will be 0
     * @param size getting from request if client don't provided will be 5
     * @return ResponseEntity with list of contacts
     */
    @GetMapping
    public ResponseEntity<List<ContactDTO>> contacts(@SessionAttribute Integer userId,
                                                     @RequestParam @Nullable Optional<Integer> page,
                                                     @RequestParam @Nullable Optional<Integer> size) {
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(5));
        return ResponseEntity.ok(contactService.getAllByUserId(userId, pageable));
    }

    @GetMapping
    public ResponseEntity<Contact> contacts(@SessionAttribute Integer userId,
                                            @RequestParam Integer contactId) {
        return ResponseEntity.ok(contactService.findByIdAndUserID(contactId, userId));
    }

    /**
     * A method to add new contact
     *
     * @param contact getting from request and validate
     * @param multipartFile getting from request, can be null if client don't provided any
     * @param userId getting userId from session
     * @return ResponseEntity with created contact
     */
    @PutMapping
    public ResponseEntity<Contact> addContact(@RequestBody @Valid Contact contact,
                                              @RequestParam("image") @Nullable MultipartFile multipartFile,
                                              @SessionAttribute Integer userId) throws IOException {
        if (multipartFile != null && multipartFile.getOriginalFilename() != null){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            contact.setPhoto(fileName);
            Contact savedContact = contactService.saveContact(contact, userId);
            String uploadDir = "contact-photos/" + savedContact.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(savedContact);
        }
        return ResponseEntity.ok(contactService.saveContact(contact, userId));
    }

    /**
     * A method to edit existing contact
     *
     * @param contact getting from request and validate
     * @param multipartFile getting from request, can be null if client don't provided any
     * @param userId getting userId from session
     * @return ResponseEntity with updated contact
     */
    @PostMapping
    public ResponseEntity<Contact> editContact(@RequestBody @Valid Contact contact,
                                               @RequestParam Integer contactId,
                                               @RequestParam("image") @Nullable MultipartFile multipartFile,
                                               @SessionAttribute Integer userId) throws IOException {
        contact.setId(contactId);
        return addContact(contact, multipartFile, userId);
    }

    /**
     * A method to delete existing contact
     *
     * @param contactId id contact that will be deleted
     * @return String with message that confirm deleted
     */
    @DeleteMapping
    public ResponseEntity<String> deleteContact(@RequestParam Integer contactId) {
        contactService.deleteById(contactId);
        return ResponseEntity.ok("contact deleted");
    }
}
