package View.DefaultUI;

import Model.*;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A tab for the current staff members
 * Created by Hatem on 3/20/2016.
 * modified by Abeer
 */
@SpringComponent
@UIScope
public class EmploymentsView extends VerticalLayout {
    final private int WIDTH = 11;
    private DeletionLogModel logModel ;
    private TextField searchField ;
    private BeanItemContainer<Employment> container;
    private Collection<Employment> member;
    private GeneratedPropertyContainer gpc;
    private Grid membersGrid;
    private Button deleteSelected;
    /**
     *A constructor for the table tree full of the staff members (could be current or deleted members)
     */
    @Autowired
    public EmploymentsView() {

        this.setMargin(true);
        this.setSpacing(true);
        this.setSizeFull();
        try {
            logModel = new DeletionLogModel();
        } catch (IOException e) {
            Notification.show(e.getMessage());

        }
        // Search field for member
        searchField = new TextField();
        searchField.focus();
        searchField.setInputPrompt("Search by ID");
        searchField.setWidth(WIDTH, Unit.CM);
        member = new ArrayList<>();

        Connection connect = SQLServerConnection.getInstance();
        EmploymentDAO daoEmployment = new EmploymentDAO(SQLServerConnection.getInstance());
        List<Employment> listEmployments = daoEmployment.getEmployments();
        member = listEmployments;

        //for (Employment e : listEmployments) {
            //membersGrid.addRow(e.getCompanyID(), e.getPersonID(), e.getEmploymentID(),
             //       e.getRowID(), e.getFirstName(), e.getLastName());
        //}

        container =new BeanItemContainer<Employment>(Employment.class, member);
        gpc = new GeneratedPropertyContainer(container);

        gpc.addGeneratedProperty("Show Information",
                new PropertyValueGenerator<String>() {


                    @Override
                    public Class<String> getType() {
                        return String.class;
                    }

                    @Override
                    public String getValue(Item item, Object itemId,
                                           Object propertyId) {
                        return "Show Info";
                    }
                });




        initGird();
        this.addComponents(searchField, membersGrid, deleteSelected);
    }

    private void initGird() {
        membersGrid = new Grid(gpc);
        // Column should fetch the Employment class attribute names
        membersGrid.setColumnOrder("companyID", "personID", "employmentID", "rowID", "firstName", "lastName");
        membersGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        membersGrid.setHeight(300, Unit.PIXELS);
        membersGrid.setWidth(28, Unit.CM);
        //   membersGrid.setSizeFull();
        membersGrid.setImmediate(true);

        membersGrid.getColumn("Show Information")
                .setRenderer(new ButtonRenderer(e ->{ // Java 8
                    Employment emp = (Employment)e.getItemId();
                    UI.getCurrent().addWindow(new EmploymentInfo(emp));
                }
                ));

        //select multiple items
        membersGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        deleteSelected = new Button("Delete Selected", e -> {
            if(membersGrid.getSelectedRows().size() > 0){

                UI.getCurrent().addWindow(  new DeletionConfirmationWindow(logModel,membersGrid)  );
            }
            else

                Notification.show("Nothing selected");
        });
    }
}
