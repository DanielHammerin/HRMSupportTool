package userInterface.DefaultUI;

import Model.FileRepo.logFileRepository;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.Renderer;
import org.springframework.beans.factory.annotation.Autowired;
import Model.Employments;
import userInterface.LogoutHLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A tab for the current staff members
 * Created by Hatem  on 3/20/2016.
 * modified by Abeer
 */
@SpringComponent
@UIScope
public class EmployeesGrid extends VerticalLayout {
    final private int WIDTH = 11;
    private logFileRepository logRepo;
    private TextField searchField ;
    private BeanItemContainer<Employments> container;
    private Collection<Employments> member;
    private GeneratedPropertyContainer gpc;
    private Grid membersGrid;
    private Button deleteSelected;
    private Employments selectedEmployee;
    /**
     *A constructor for the table tree full of the staff members (could be current or deleted members)
     */
    @Autowired
    public EmployeesGrid() {

        this.setMargin(true);
        this.setSpacing(true);
        try {
            logRepo = new logFileRepository();
        } catch (IOException e) {
            Notification.show(e.getMessage());

        }
        // Search field for member
        searchField = new TextField();
        searchField.focus();
        searchField.setInputPrompt("Search by ID");
        searchField.setWidth(WIDTH, Unit.CM);
        member = new ArrayList<>();


        //Adding dummy members to the table
        for(int i = 1; i <= 5; i++) {
          Employments staffMember = new Employments("Company ID" + i,
                  "Person ID" + i, "Employment ID"+ i,"firstName "+i, "lastName "+i);
            member.add(staffMember);
        }
         container =new BeanItemContainer<Employments>(Employments.class, member);
         gpc = new GeneratedPropertyContainer(container);



        gpc.addGeneratedProperty("Delete",
                new PropertyValueGenerator<String>() {

                    @Override
                    public Class<String> getType() {
                        return String.class;
                    }

                    @Override
                    public String getValue(Item item, Object itemId,
                                           Object propertyId) {
                        // TODO Auto-generated method stub
                        return "Delete";
                    }
                });

        gpc.addGeneratedProperty("Show Information",
                new PropertyValueGenerator<String>() {


                    @Override
                    public Class<String> getType() {
                        return String.class;
                    }

                    @Override
                    public String getValue(Item item, Object itemId,
                                           Object propertyId) {
                        // TODO Auto-generated method stub
                        return "Show Info";
                    }
                });




        initGird();
        this.addComponents(searchField, membersGrid, deleteSelected);
    }

    private void initGird() {
        membersGrid = new Grid(gpc);
        membersGrid.setColumnOrder("companyId","personId",
                "employmentId", "firstName","lastName");

        membersGrid.setHeight(300, Unit.PIXELS);
        membersGrid.setWidth(28, Unit.CM);
        //   membersGrid.setSizeFull();
        membersGrid.setImmediate(true);

        // Render a button that deletes the data row (item)
        membersGrid.getColumn("Delete")
                .setRenderer(new ButtonRenderer(e -> {
                    UI.getCurrent().addWindow(  new EmployeesDeletionSubWindow(logRepo, membersGrid , e) );
                }));


        membersGrid.getColumn("Show Information")
                .setRenderer(new ButtonRenderer(e ->{ // Java 8
                    Employments emp = (Employments)e.getItemId();
                    UI.getCurrent().addWindow(new EmployeeInfo(emp));
                }
                ));

        //select multiple items
        membersGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        deleteSelected = new Button("Delete Selected", e -> {
            if(membersGrid.getSelectedRows().size() > 0){

                UI.getCurrent().addWindow(  new EmployeesDeletionSubWindow(logRepo,membersGrid)  );
            }
            else

                Notification.show("Nothing selected");
        });
    }
}
