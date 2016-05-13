package View.DefaultUI;

import Model.Entity.Employment;
import com.vaadin.ui.*;

/**
 * Created by Abeer on 4/13/2016.
 * modified by Simon on 2016/04/28 to make it work with Employment (without s) class
 */
public class EmploymentInfo extends Window {


    Button closeButton = new Button("Close");
    HorizontalLayout action = new HorizontalLayout(closeButton);
    VerticalLayout content = new VerticalLayout();

    public EmploymentInfo(Employment member) {
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

        // Label containing information you want to display
        Label companyID = new Label("CompanyID : " + member.getCompanyID());
        Label personID = new Label("PersonID : " + member.getPersonID());
        Label employmentID = new Label("EmploymentID : " + member.getEmploymentID());
        Label rowID = new Label("RowID : " + member.getRowID());
        Label firstName = new Label("FirstName : " + member.getFirstName());
        Label lastName = new Label("LastName : " + member.getLastName());

        // Close the pop-ip window
        closeButton.addClickListener(e -> {
            close();
        });

        // Add the label created before
        content.addComponents(new Label("Personal Information"), companyID, personID,
                employmentID, rowID, firstName, lastName);
        content.setSpacing(true);
        content.addComponent(action);
        setContent(content);
    }
}
