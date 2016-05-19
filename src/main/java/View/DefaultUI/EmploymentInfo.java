package View.DefaultUI;

import Model.Entity.Employment;
import Presenter.EmploymentsPresenter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

/**
 * Created by Abeer on 4/13/2016.
 * sub window shows  employments information, altering employment info is enabled for admin user
 * modified by Simon on 2016/04/28 to make it work with Employment (without s) class
 */
public class EmploymentInfo extends Window {


    Button save = new Button("save");
    Button closeButton = new Button("Close");
    HorizontalLayout action = new HorizontalLayout(closeButton,save);
    VerticalLayout content = new VerticalLayout();
    private EmploymentInfoForm employmentInfoForm;
    private EmploymentsPresenter presenter ;
    private Button saveButton , colseButton;
    boolean enableEditorMode;


    public EmploymentInfo(Employment member, EmploymentsPresenter presenter) {
        super("Employment Information"); // Set window caption
        this.presenter=presenter;
        enableEditorMode= presenter.isUserAdmin();
        center();
        action.setSpacing(true);
        setModal(true);
        setClosable(true);
        content.setMargin(true);
        content.setSizeFull();
        // create employment info form
        employmentInfoForm= new EmploymentInfoForm();
        // bind employment data to text field
        employmentInfoForm.firstName.setValue(member.getFirstName());
        employmentInfoForm.lastName.setValue(member.getLastName());
        employmentInfoForm.companyID.setValue(member.getCompanyID());
        employmentInfoForm.rowID.setValue(Integer.toString(member.getRowID()));
        employmentInfoForm.personID.setValue(member.getPersonID());
        employmentInfoForm.EmploymentID.setValue(member.getEmploymentID());
        // enable updating employment info if the user is admin
        employmentInfoForm.firstName.setEnabled(enableEditorMode);
        employmentInfoForm.lastName.setEnabled(enableEditorMode);
        employmentInfoForm.startDate.setEnabled(enableEditorMode);
        employmentInfoForm.endDate.setEnabled(enableEditorMode);
        employmentInfoForm.companyID.setEnabled(enableEditorMode);
        employmentInfoForm.rowID.setEnabled(enableEditorMode);
        employmentInfoForm.EmploymentID.setEnabled(enableEditorMode);
        employmentInfoForm.personID.setEnabled(enableEditorMode);

        saveButton = new Button("Save", FontAwesome.EDIT);
        saveButton.setVisible(presenter.isUserAdmin());
        closeButton= new Button("Close");
        saveButton.addClickListener(e -> {
           if( employmentInfoForm.validateEmploymentParameter()){
              // updating Employment method
               presenter.updateEmployment(member,employmentInfoForm.companyID.getValue(),
                       employmentInfoForm.personID.getValue(),
                       employmentInfoForm.EmploymentID.getValue(), Integer.parseInt(employmentInfoForm.rowID.getValue()),
                       employmentInfoForm.firstName.getValue(),
                       employmentInfoForm.lastName.getValue() );
           }
            close();

                });
        closeButton.addClickListener(e -> {
         close();
        });
        employmentInfoForm.actions.addComponents(saveButton,closeButton);
        content.addComponents(employmentInfoForm);
        content.setSpacing(true);


        setContent(content);
        setResizable(true);
        setWidth("35%");
        setHeight("70%");

    }
}
