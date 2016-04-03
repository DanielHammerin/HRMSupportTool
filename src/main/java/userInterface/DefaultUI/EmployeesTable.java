package userInterface.DefaultUI;

import com.vaadin.data.Property;
import com.vaadin.server.Sizeable;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import Model.Employments;

/**
 * A tab for the current staff members
 * Created by Hatem on 3/20/2016.
 */
@SpringComponent
@UIScope
public class EmployeesTable extends VerticalLayout {
    final private int WIDTH = 11;

    /**
     *A constructor for the table tree full of the staff members (could be current or deleted members)
     */
    @Autowired
    public EmployeesTable() {

        this.setMargin(true);
        this.setSpacing(true);

        // Table containing all the working members
        TreeTable membersTable = new TreeTable();

        membersTable.addContainerProperty("Employment ID", Integer.class, null);
        membersTable.addContainerProperty("First Name", String.class, null);
        membersTable.addContainerProperty("Last Name", String.class, null);
        membersTable.addContainerProperty("Start date", String.class, null);
        membersTable.addContainerProperty("End date", String.class, null);

        // Editing table layout
        editTable(membersTable);

        // Search field for member
        TextField searchField = new TextField();
        searchField.setInputPrompt("Search by ID");
        searchField.setWidth(WIDTH, Unit.CM);

        //Adding dummy members to the table
        for(int i = 1; i < 5; i++) {
            Employments staffMember = new Employments(i, "firstName "+i, "lastName "+i);
            membersTable.addItem(new Object[]{staffMember.getStaffId(), staffMember.getFirstName(), staffMember.getLastName()}, i + 1);
        }
        this.addComponents(searchField, membersTable);
    }

    /**
     * A method to edit the table layout
     * @param membersTable: table to be edited
     */
    private void editTable(Table membersTable){
        membersTable.setEnabled(false);
        membersTable.setWidth("100%");

        membersTable.setSelectable(true);
        membersTable.setImmediate(true);

        // Shows feedback from selection.
        final Label current = new Label("Selected: -");

        // Handle selection change.
        membersTable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                current.setValue("Selected: " + membersTable.getValue());
            }
        });
    }
}
