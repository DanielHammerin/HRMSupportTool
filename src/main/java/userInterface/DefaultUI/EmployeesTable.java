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
     *A constructor for the table full of the staff members (could be current or deleted members)
     * @param tabName: the name of the table
     * @param height: Height of table
     * @param viewType: Whether deleted or current members. True for Current, false for Deleted
     */
    @Autowired
    public EmployeesTable(String tabName, String height, boolean viewType) {

        this.setMargin(true);
        this.setSpacing(true);
        // Table containing all the working members
        Table membersTable = new Table();

        membersTable.addContainerProperty("ID", Integer.class, null);
        membersTable.addContainerProperty("First Name", String.class, null);
        membersTable.addContainerProperty("Last Name", String.class, null);

        // Editing table layout
        editTable(membersTable);

        // Search field for member
        TextField searchField = new TextField();
        searchField.setCaption(tabName);
        searchField.setInputPrompt("Search by ID");
        searchField.setWidth(WIDTH, Unit.CM);
        this.setHeight(height);

        //Adding dummy members to the table
        String deleted = "";
        if(!viewType)               //If the viewType is Deleted, then add "Deleted" before every staff member
             deleted = "Deleted";
        Employments specialMember = new Employments(0, deleted + " Mark", deleted + " Knopfler");
        membersTable.addItem(new Object[]{specialMember.getStaffId(), specialMember.getFirstName(), specialMember.getLastName()}, 1);
        for(int i = 1; i < 5; i++) {
            Employments staffMember = new Employments(i, deleted + "firstName "+i, deleted + "lastName "+i);
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
        membersTable.setWidth(WIDTH, Sizeable.Unit.CM);

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
