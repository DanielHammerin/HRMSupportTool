package userInterface.DefaultUI;

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
public class EmployeesGrid extends VerticalLayout {
    final private int WIDTH = 11;

    /**
     *A constructor for the table tree full of the staff members (could be current or deleted members)
     */
    @Autowired
    public EmployeesGrid() {

        this.setMargin(true);
        this.setSpacing(true);

        // Table containing all the working members
        Grid membersGrid = new Grid();

        membersGrid.setWidth(23, Unit.CM);
    //    membersGrid.setSizeFull();
        membersGrid.setImmediate(true);

        //select multiple items
        membersGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        membersGrid.addColumn("Company ID", String.class);
        membersGrid.addColumn("Person ID", String.class);
        membersGrid.addColumn("EmploymentID", String.class);
        membersGrid.addColumn("RowID", Integer.class);
        membersGrid.addColumn("First Name", String.class);
        membersGrid.addColumn("Last Name", String.class);
        membersGrid.addColumn("Start date", String.class);
        membersGrid.addColumn("End date", String.class);

        // Search field for member
        TextField searchField = new TextField();
        searchField.focus();
        searchField.setInputPrompt("Search by ID");
        searchField.setWidth(WIDTH, Unit.CM);

        //Adding dummy members to the table
        for(int i = 1; i <= 5; i++) {
            Employments staffMember = new Employments("Company ID" + i, "Person ID" + i, "Employment ID"+ i,"firstName "+i, "lastName "+i);
            membersGrid.addRow(staffMember.getCompanyId(), staffMember.getPersonId(), staffMember.getEmploymentId(), i,
                    staffMember.getFirstName(), staffMember.getLastName(), "2015", "-");
        }


        // Allow deleting the selected items
        Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) membersGrid.getSelectionModel();
        selection.setSelected();

        Button deleteSelected = new Button("Delete Selected", e -> {
            // Delete all selected data items
            for (Object itemId: selection.getSelectedRows())
                membersGrid.getContainerDataSource().removeItem(itemId);

            // Otherwise out of sync with container
            membersGrid.getSelectionModel().reset();

            // Disable after deleting
          //  e.getButton().setEnabled(false);
        });
       // deleteSelected.setEnabled(membersGrid.getSelectedRows().size() > 0);

        this.addComponents(searchField, membersGrid, deleteSelected);
    }
}
