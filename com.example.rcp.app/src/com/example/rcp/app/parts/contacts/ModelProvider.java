package com.example.rcp.app.parts.contacts;

import contacts.Contact;
import contacts.ContactsContainer;
import contacts.ContactsFactory;

public class ModelProvider {
	public ContactsContainer createModel() {
		ContactsFactory factory = ContactsFactory.eINSTANCE;
		ContactsContainer contactsContainer = factory.createContactsContainer();
		Contact e;
		e = factory.createContact();
		e.setName("Devesh Gupta");
		e.setMobile("+91 84 332 47 570");
		e.setLocation("Gurgown");
		e.setEmail("deveshgupta192@gmail.com");
		contactsContainer.getContacts().add(e);
		return contactsContainer;
	}
}