package View.DefaultUI;


import Presenter.EmploymentsPresenter;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;



/**
 * Created by Abeer on 5/18/2016.
 * AddingEmployment class for adding new employment
 * adding employment only visible for admin user
 */
@SpringComponent
@UIScope
public class AddingEmployment extends VerticalLayout {
    private FormLayout content;
    private Button addEmploymentButton, cancelButton;
    private EmploymentInfoForm employmentInfoForm;
    private EmploymentsPresenter employmentsPresenter;


    public AddingEmployment(EmploymentsPresenter employmentsPresenter) {
        this.employmentsPresenter = employmentsPresenter;
        this.employmentInfoForm = new EmploymentInfoForm();
        content = new FormLayout();
        addEmploymentButton = new Button("Add", FontAwesome.EDIT);
        cancelButton = new Button("Cancel");


        addEmploymentButton.addClickListener(e -> {
            // validate emplo parameters
            if (employmentInfoForm.validateEmploymentParameter()) {
                employmentsPresenter.addNewEmployment(
                        employmentInfoForm.companyID.getValue().toString(),
                        employmentInfoForm.personID.getValue().toString(),
                        employmentInfoForm.EmploymentID.getValue().toString(),
                        Integer.parseInt(employmentInfoForm.rowID.getValue()),
                        employmentInfoForm.firstName.getValue().toString()
                        , employmentInfoForm.lastName.getValue().toString(),
                        employmentInfoForm.startDate.getValue().toString(),
                        employmentInfoForm.endDate.getValue().toString());
                clearTextField();

            } else {
                new Notification("Enter required parameters", Notification.TYPE_ERROR_MESSAGE)
                        .show(getUI().getPage());
            }

        });
        cancelButton.addClickListener(e -> {
            clearTextField();
        });

        employmentInfoForm.actions.addComponents(addEmploymentButton, cancelButton);
        content.addComponents(employmentInfoForm);
        addComponent(content);


    }

    private void clearTextField() {
        employmentInfoForm.firstName.clear();
        employmentInfoForm.lastName.clear();
        employmentInfoForm.companyID.clear();
        employmentInfoForm.personID.clear();
        employmentInfoForm.EmploymentID.clear();
        employmentInfoForm.rowID.clear();
        employmentInfoForm.startDate.clear();
        employmentInfoForm.endDate.clear();
    }




}
