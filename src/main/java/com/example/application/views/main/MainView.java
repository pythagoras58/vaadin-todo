package com.example.application.views.main;

import com.example.application.backend.entities.CompanyEntity;
import com.example.application.backend.entities.ContactEntity;
import com.example.application.backend.service.ContactService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Main")
@Route(value = "")
public class MainView extends HorizontalLayout {

    // make a grid to hold contact data
    Grid<ContactEntity> grid = new Grid<>(ContactEntity.class);

    TextField filterText = new TextField(); // for text filtering.

    private ContactService contactService;

    public MainView(ContactService contactService) {
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureFilter();

        add(filterText, grid);

        updateList();
    }

    private void configureFilter() {
    }

    private void updateList() {
        grid.setItems(contactService.findAll());
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("company");
        grid.setColumns("firstName", "lastName", "email", "status");
        grid.addColumn(contact -> {
            CompanyEntity company = contact.getCompany();
            return company == null ? "-" : company.getName();
        }).setHeader("Company");
       // grid.setColumns("firstName", "lastName", "email", "status");
        grid.getColumns().forEach(col->col.setAutoWidth(true));
    }

}
