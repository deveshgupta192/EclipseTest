package com.example.rcp.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.example.rcp.app.parts.contacts.AddContactDialog;
import com.example.rcp.app.parts.contacts.BaseTableLabelProvider;
import com.example.rcp.app.parts.contacts.ModelProvider;

import contacts.Contact;
import contacts.ContactsContainer;
import contacts.ContactsFactory;

public class ContactViewer {

	private TableViewer tableViewer;
	private ContactsContainer model;

	private void createColumns(Composite parent) {
		String[] colNames = new String[] { "Name", "Email", "Mobile No", "Location" };
		TableViewerColumn col;
		for (int i = 0; i < colNames.length; i++) {
			col = new TableViewerColumn(tableViewer, SWT.NONE);
			col.getColumn().setWidth(100);
			col.getColumn().setText(colNames[i]);
		}
	}

	@PostConstruct
	public void createComposite(Composite parent) {
		// create an example model which will be visualized
		model = new ModelProvider().createModel();
		parent.setLayout(new GridLayout(2, false));
		createTableViewer(parent);
		createRightPanel(parent);
	}

	private ITableLabelProvider createLableProvider() {
		return new BaseTableLabelProvider() {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				Contact c = (Contact) element;
				switch (columnIndex) {
				case 0:
					return c.getName();
				case 1:
					return c.getEmail();
				case 2:
					return c.getMobile();
				case 3:
					return c.getLocation();
				default:
					return null;
				}
			}
		};
	}

	private void createRightPanel(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new FillLayout(SWT.VERTICAL));
		Button addButton = new Button(c, SWT.NONE);
		addButton.setText("Add");
		Button removeButton = new Button(c, SWT.NONE);
		removeButton.setText("Remove");
		registerListeners(addButton, removeButton);
	}

	private void createTableViewer(Composite parent) {
		int style = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER;
		tableViewer = new TableViewer(parent, style);
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		createColumns(parent);
		final Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		tableViewer.setLabelProvider(createLableProvider());
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(model.getContacts());
	}

	private void registerListeners(Button addButton, Button removeButton) {
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Contact c = newContact();
				Shell shell = tableViewer.getTable().getShell();
				AddContactDialog dialog = new AddContactDialog(shell, c);
				if (dialog.open() == Window.OK) {
					model.getContacts().add(c);
					tableViewer.refresh();
				}
			}
		});
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ISelection selection = tableViewer.getSelection();
				if (selection instanceof IStructuredSelection) {
					Object object = ((IStructuredSelection) selection).getFirstElement();
					model.getContacts().remove(object);
					tableViewer.refresh();
				}
			}
		});
	}

	private Contact newContact() {
		Contact c = ContactsFactory.eINSTANCE.createContact();
		c.setName("");
		c.setMobile("");
		c.setLocation("");
		c.setEmail("");
		return c;
	}
	
	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}
}
