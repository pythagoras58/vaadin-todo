package com.example.application.views.main;

import com.example.application.backend.entities.CompanyEntity;
import com.example.application.backend.entities.ContactEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class ContactForm extends FormLayout {
    TextField firstName = new TextField("First name"); 
    TextField lastName = new TextField("Last name");
    EmailField email = new EmailField("Email");
    ComboBox<ContactEntity.Status> status = new ComboBox<>("Status");
    ComboBox<CompanyEntity> company = new ComboBox<>("Company");
    Button save = new Button("Save"); 
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    public ContactForm() {
        addClassName("contact-form"); 
        add(firstName,
                lastName,
                email,
                company,
                status,
                createButtonsLayout()); 
    }

    private Component createButtonsLayout() {
        // add distinctive features
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        // create shortcuts

        return new HorizontalLayout(save, delete, close);
    }
}
