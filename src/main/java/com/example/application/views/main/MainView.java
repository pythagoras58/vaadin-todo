package com.example.application.views.main;

import com.example.application.backend.entities.CompanyEntity;
import com.example.application.backend.entities.ContactEntity;
import com.example.application.backend.service.ContactService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Main")
@Route(value = "")
@CssImport("./themes/mytodo/styles.css")
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

        ContactForm form = new ContactForm();
        //place the contact form in a div for responsiveness
        Div content = new Div(grid, form);
        content.addClassName("content"); // nice
        content.setSizeFull();

        add(filterText, content);


        updateList();
    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter By Name");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateList());
    }

    private void updateList() {
        grid.setItems(contactService.findAll(filterText.getValue()));
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
