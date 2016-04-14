package userInterface.DefaultUI;

import Model.Employments;
import com.vaadin.ui.*;

/**
 * Created by Abeer on 4/13/2016.
 */
public class EmployeeInfo extends Window {


    Button    CloseButton = new Button("Close");
    HorizontalLayout action = new HorizontalLayout( CloseButton);
    VerticalLayout content = new VerticalLayout();

    public EmployeeInfo(Employments member) {
        super("Employment Information"); // Set window caption
        center();
        action.setSpacing(true);
        setModal(true);
        setClosable(false);
        content.setMargin(true);
        content.setSpacing(true);
        content.setSizeFull();
        setWidth("30%");
        setHeight("70%");



        Label Id  = new Label("CompanyID :  "+ member.getCompanyId());
        Label firstName  = new Label("First Name:  "+ member.getFirstName());
        Label lastName = new Label("Last Name:  "+ member.getLastName());





        CloseButton.addClickListener(e -> {

            close();

        });



        content.addComponents(new Label("Personal Information  ") , Id,
                firstName,lastName);

        content.setSpacing(true);

        content.addComponent(new Label ("Other Information"));
        content.setSpacing(true);
        content.addComponent(action);


        setContent(content);
    }
}
