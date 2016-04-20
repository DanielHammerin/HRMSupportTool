package userInterface.DefaultUI;

import Model.DeletionLog;
import Model.Employments;
import Model.FileRepo.logFileRepository;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ClickableRenderer;
import userInterface.LogUI.LogGrid;
import userInterface.LogUI.LogMainContainer;
import userInterface.LogUI.LogTable;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Abeer on 4/13/2016.
 */
public class EmployeesDeletionSubWindow extends Window {

    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");
    HorizontalLayout actions = new HorizontalLayout(yesButton, noButton);
    VerticalLayout content = new VerticalLayout();
    private logFileRepository logRepo ;
    private DeletionLog deletionLog ;
    private Employments selectedEmployee;

    public EmployeesDeletionSubWindow(logFileRepository logRepo, Grid grid) {
        super("Delete Employmees"); // Set window caption
        this.logRepo = logRepo;
        init(grid);

        yesButton.addClickListener(e -> {

            Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();

            for (Object itemId: selection.getSelectedRows()) {
                selectedEmployee = (Employments)itemId;
                deletionLog = new DeletionLog("Abeer Alkhars",
                        selectedEmployee.getFirstName()+" "+
                        selectedEmployee.getLastName(), new Date());
                grid.getContainerDataSource().removeItem(itemId);

               if(! logRepo.createDeletionLog(deletionLog)){
                   Notification.show("deletion log is not saved");
                    return;
               }

            }


            // Otherwise out of sync with container
            grid.getSelectionModel().reset();
            close();
            Notification.show("Detetion is done successfully");

        });


        content.addComponents(new Label("Are you sure you want to delete the selected employees?"), actions);

        setContent(content);
    }


    public EmployeesDeletionSubWindow(logFileRepository logRepo ,Grid grid , ClickableRenderer.RendererClickEvent e) {

        super("Delete Employee"); // Set window caption
        this.logRepo = logRepo;
        init(grid);
        Employments selectedEmployee = (Employments)e.getItemId();

        yesButton.addClickListener(event -> {
            deletionLog = new DeletionLog("Abeer Alkhars", selectedEmployee.getFirstName()+" "+
                    selectedEmployee.getLastName(), new Date());
            grid.getContainerDataSource()
                    .removeItem(e.getItemId());
            if(! logRepo.createDeletionLog(deletionLog)){
                Notification.show("deletion log is not saved");
            }
            close();
            Notification.show("Detetion is done successfully");


        });

        content.addComponents(new Label("Are you sure you want to delete '"+selectedEmployee.getFirstName()+
                " "+selectedEmployee.getLastName()+"'?"), actions);

        setContent(content);
    }

    private void init(Grid grid) {
        center();
        actions.setSpacing(true);
        setModal(true);
        setClosable(false);
        setResizable(false);
        content.setMargin(true);
        content.setSpacing(true);
        noButton.addClickListener(e -> {
            close();
            grid.getSelectionModel().reset();
        });
    }

}


