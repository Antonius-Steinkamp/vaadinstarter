package com.example.application.views.addressform;

import com.example.application.data.entity.SampleAddress;
import com.example.application.data.service.SampleAddressService;
import com.example.application.views.MainLayout;
import com.example.application.views.ViewUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Address Form")
@Route(value = "address-form", layout = MainLayout.class)
public class AddressFormView extends Div {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1287169389916766933L;
	
	private TextField street = new TextField(getTranslation("Street address"));
    private TextField postalCode = new TextField(getTranslation("Postal code"));
    private TextField city = new TextField(getTranslation("City"));
    private ComboBox<String> state = new ComboBox<>(getTranslation("State"));
    private ComboBox<String> country = new ComboBox<>(getTranslation("Country"));

    private Button cancel = new Button(getTranslation("Cancel"));
    private Button save = new Button(getTranslation("Save"));

    private Binder<SampleAddress> binder = new Binder<>(SampleAddress.class);

    public AddressFormView(SampleAddressService addressService) {
        addClassName("address-form-view");

        add(createTitle());
        add(createFormLayout());
        add(ViewUtils.createButtonLayout(save, cancel));

        binder.bindInstanceFields(this);

        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            addressService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " stored.");
            clearForm();
        });
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

    }

    private Component createTitle() {
        return new H3(getTranslation("Address"));
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(street, 2);
        postalCode.setAllowedCharPattern("\\d");
        country.setItems("Country 1", "Country 2", "Country 3");
        state.setItems("State A", "State B", "State C", "State D");
        formLayout.add(postalCode, city, state, country);
        return formLayout;
    }

    private void clearForm() {
        this.binder.setBean(new SampleAddress());
    }

}
