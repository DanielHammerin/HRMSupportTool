package userInterface.DefaultUI;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import Model.Employments;

/**
 * Created by Hatem on 3/21/2016.
 */
@SpringComponent
@UIScope
public class EmployeeInfoTab extends VerticalLayout {
    final private int WIDTH = 8;
    private Employments staffMember = new Employments(0, "Mark", "Knopfler");

    Grid memberInfoGrid = new Grid();

    /* Fields to edit properties in Customer entity */
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField id = new TextField("ID");

    /* Action buttons */
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(delete, cancel);

    @Autowired
    public EmployeeInfoTab(){
        setMargin(true);
        setSpacing(true);
        setCaption("Member's Info and Deleting");

        firstName.setValue(staffMember.getFirstName());
        lastName.setValue(staffMember.getLastName());
        id.setValue(String.valueOf(staffMember.getStaffId()));

        firstName.setEnabled(false);
        lastName.setEnabled(false);
        id.setEnabled(false);

        memberInfoGrid.setWidth(WIDTH, Unit.CM);

        memberInfoGrid.addColumn("", String.class);
        memberInfoGrid.addColumn("Info", String.class);

        memberInfoGrid.addRow("First name", staffMember.getFirstName());
        memberInfoGrid.addRow("Last name", staffMember.getLastName());
        memberInfoGrid.addRow("ID", String.valueOf(staffMember.getStaffId()));
        memberInfoGrid.addRow("Birthday", "1949/8/12");

        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        delete.setClickShortcut(ShortcutAction.KeyCode.DELETE);

        addComponents(actions, firstName, lastName, id, memberInfoGrid);
    }
}
