package com.example.rcp.app.parts.contacts;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import contacts.Contact;

public class AddContactDialog extends TitleAreaDialog {
	private Text textName;
	private Text textEmail;
	private Text textMobile;
	private Text textLocation;
	private Contact c;

	public AddContactDialog(Shell parentShell, Contact c) {
		super(parentShell);
		this.c = c;
	}

	@Override
	public void create() {
		super.create();
		setTitle("Add Contact");
		setMessage(" ", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);
		createTextWidgets(container);
		registerListeners();
		return area;
	}

	private void createTextWidgets(Composite container) {
		textName = createTextWithLabel(container, "Name");
		textEmail = createTextWithLabel(container, "Email");
		textMobile = createTextWithLabel(container, "Mobile");
		textLocation = createTextWithLabel(container, "Location");
	}

	private Text createTextWithLabel(Composite container, String labelname) {
		Label label = new Label(container, SWT.NONE);
		label.setText(labelname);
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = GridData.FILL;
		Text text = new Text(container, SWT.BORDER);
		text.setLayoutData(gd);
		return text;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	private void registerListeners() {
		textName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				c.setName(textName.getText().trim());
			}
		});
		textEmail.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				c.setEmail(textEmail.getText().trim());
			}
		});
		textMobile.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				c.setMobile(textMobile.getText().trim());
			}
		});
		textLocation.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				c.setLocation(textLocation.getText().trim());
			}
		});
	}

	// save content of the Text fields because they get disposed
	// as soon as the Dialog closes
	private void saveInput() {
		c.setName(textName.getText().trim());
		c.setEmail(textEmail.getText().trim());
		c.setMobile(textMobile.getText().trim());
		c.setLocation(textLocation.getText().trim());
	}
}
