package View.DefaultUI;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import Model.Employments;

/**
 * Created by Hatem on 3/21/2016.
 */
@SpringComponent
@UIScope
public class EmployeeInfoTab extends VerticalLayout {
    private Employments staffMember = new Employments("0", "0", "0", "Mark", "Knopfler");

    Grid memberInfoGrid = new Grid();

    /* Fields to edit properties in Customer entity */
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField id = new TextField("ID");

    @Autowired
    public EmployeeInfoTab(){
        setMargin(true);
        setSpacing(true);
        setCaption("Member's Info and Deleting");

        firstName.setValue(staffMember.getFirstName());
        lastName.setValue(staffMember.getLastName());
        id.setValue(String.valueOf(staffMember.getCompanyId()));

        firstName.setEnabled(false);
        lastName.setEnabled(false);
        id.setEnabled(false);

        memberInfoGrid.setWidth(7, Unit.CM);

        memberInfoGrid.addColumn("", String.class);
        memberInfoGrid.addColumn("Info", String.class);

        memberInfoGrid.addRow("First name", staffMember.getFirstName());
        memberInfoGrid.addRow("Last name", staffMember.getLastName());
        memberInfoGrid.addRow("ID", String.valueOf(staffMember.getCompanyId()));
        memberInfoGrid.addRow("Birthday", "1949/8/12");

        addComponents(firstName, lastName, id, memberInfoGrid);
    }
}
