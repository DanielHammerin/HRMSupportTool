package View.DefaultUI;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;

import java.text.Normalizer;

/**
 * Created by Abeer on 5/19/2016.
 * class creates layout contains textFields for employment info
 */
public class EmploymentInfoForm  extends VerticalLayout {
    protected PopupDateField startDate ,endDate;
    protected TextField firstName,lastName, personID , companyID,EmploymentID,rowID;
    protected Validator NameValidator;
    protected HorizontalLayout employmentNameLayout,dateLayout,IDlayout1,IDlayout2,actions;


    public EmploymentInfoForm (){

        firstName = new TextField("First name");
        lastName = new TextField("Last name");;
        startDate = new PopupDateField("Start Date");
        endDate = new PopupDateField("End Date");
        personID = new TextField("Person ID");
        companyID= new TextField("Company ID");
        EmploymentID = new TextField("Employment ID");
        rowID = new TextField("Row ID");


        addValidators();
        employmentNameLayout= new HorizontalLayout(firstName, lastName);
        dateLayout=new HorizontalLayout( startDate ,endDate);
        IDlayout1 = new HorizontalLayout(personID,EmploymentID);
        IDlayout2 = new HorizontalLayout(rowID, companyID);
        actions = new HorizontalLayout();

        dateLayout.setSpacing(true);
        employmentNameLayout.setSpacing(true);
        IDlayout2.setSpacing(true);
        IDlayout1.setSpacing(true);
        setSpacing(true);
        addComponents(employmentNameLayout,dateLayout,IDlayout1,IDlayout2,actions);

    }
    // check the validtion of the emplo parameters
    public boolean validateEmploymentParameter() {
        return (firstName.isValid()&&lastName.isValid()& rowID.isValid()&&
                isValid(startDate.getValue())&&isValid(endDate.getValue())
                &&isValid(companyID.getValue())&&isValid(EmploymentID.getValue())&&isValid(personID.getValue()));
    }

    public boolean isValid(Object value) {
        System.out.println("here");
        if (value==null){
            return false;
        }
        else

        if(value.toString().trim().isEmpty())
        {    return false;}
        else
            return true;


    }
    // adding some error handling
    private void addValidators(){
        NameValidator =new StringLengthValidator(
                "Name must be 3-25 characters", 3, 25, true);

        firstName.addValidator(NameValidator);
        lastName.addValidator(NameValidator);
        rowID.addValidator(new RegexpValidator(
                "^[0-9]+$", "RowID must be integer"));

        firstName.setRequired(true);firstName.setImmediate(true);
        lastName.setRequired(true);lastName.setImmediate(true);
        rowID.setRequired(true);rowID.setRequired(true);
        personID.setRequired(true);personID.setImmediate(true);
        companyID.setRequired(true);companyID.setImmediate(true);
        EmploymentID.setRequired(true);EmploymentID.setImmediate(true);
        startDate.setRequired(true);
        startDate.setImmediate(true);
        endDate.setRequired(true);
        endDate.setImmediate(true);
    }


}
