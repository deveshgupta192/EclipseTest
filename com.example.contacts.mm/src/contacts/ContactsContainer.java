/**
 */
package contacts;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link contacts.ContactsContainer#getContacts <em>Contacts</em>}</li>
 * </ul>
 *
 * @see contacts.ContactsPackage#getContactsContainer()
 * @model
 * @generated
 */
public interface ContactsContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Contacts</b></em>' containment reference list.
	 * The list contents are of type {@link contacts.Contact}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contacts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contacts</em>' containment reference list.
	 * @see contacts.ContactsPackage#getContactsContainer_Contacts()
	 * @model containment="true"
	 * @generated
	 */
	EList<Contact> getContacts();

} // ContactsContainer
