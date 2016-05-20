package View.DefaultUI;

import Model.Entity.Employment;
import Presenter.EmploymentsPresenter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

/**
 * Created by Abeer on 4/13/2016.
 * sub window shows  employments information
 * modified by Simon on 2016/04/28 to make it work with Employment (without s) class
 */
public class EmploymentInfo extends Window {


    Button save = new Button("save");
    Button closeButton = new Button("Close");
    VerticalLayout content = new VerticalLayout();

    protected TextField firstName,lastName, personID , companyID,EmploymentID,rowID, startDate ,endDate;
    protected HorizontalLayout employmentNameLayout,dateLayout,IDlayout1,IDlayout2,action;



    public EmploymentInfo(Employment member) {
        super("Employment Information"); // Set window caption
        center();
        setModal(true);
        setClosable(true);
        content.setMargin(true);
        content.setSizeFull();

        firstName = new TextField("FirstName",member.getFirstName());
        lastName= new TextField("LastName",member.getLastName());
        companyID = new TextField("Company ID",member.getCompanyID());
        rowID = new TextField("RowID",Integer.toString(member.getRowID()));
        personID =new TextField("personID",member.getPersonID());
        EmploymentID= new TextField("EmploymentID",member.getEmploymentID());
        startDate = new TextField("Start Date ",member.getStartDate());
        endDate= new TextField("End Date",member.getEndDate());

       firstName.setEnabled(false);lastName.setEnabled(false);
       startDate.setEnabled(false);endDate.setEnabled(false);
        companyID.setEnabled(false);rowID.setEnabled(false);
        EmploymentID.setEnabled(false);personID.setEnabled(false);

        employmentNameLayout= new HorizontalLayout(firstName, lastName);
        dateLayout=new HorizontalLayout( startDate ,endDate);
        IDlayout1 = new HorizontalLayout(personID,EmploymentID);
        IDlayout2 = new HorizontalLayout(rowID, companyID);
        employmentNameLayout.setSpacing(true);
        dateLayout.setSpacing(true);
        IDlayout1.setSpacing(true);
        IDlayout2.setSpacing(true);

        action = new HorizontalLayout(closeButton);

        closeButton.addClickListener(e -> {
         close();
        });

        content.addComponents(employmentNameLayout,dateLayout,IDlayout1,IDlayout2,action);
        content.setSpacing(true);


        setContent(content);
        setResizable(true);
        setWidth("50%");
        setHeight("70%");

    }
}
