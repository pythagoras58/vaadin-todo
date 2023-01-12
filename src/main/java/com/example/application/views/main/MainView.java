package com.example.application.views.main;

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

@PageTitle("Main")
@Route(value = "")
public class MainView extends HorizontalLayout {

    // make a grid to hold contact data
    Grid<ContactEntity> grid = new Grid<>(ContactEntity.class);

    private ContactService contactService;

    public MainView(ContactService contactService) {
        addClassName("list-view");
        setSizeFull();

        configureGrid();

        add(grid);

        updateList();
    }

    private void updateList() {

    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email", "status");
    }

}
