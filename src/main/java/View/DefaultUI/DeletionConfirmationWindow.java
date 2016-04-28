package View.DefaultUI;

import Model.DeletionLog;
import Model.DeletionLogModel;
import Model.Employments;
import Model.FileRepo.logFileRepository;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ClickableRenderer;

import java.util.Date;

/**
 * Created by Abeer on 4/13/2016.
 */
public class DeletionConfirmationWindow extends Window {

    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");
    HorizontalLayout actions = new HorizontalLayout(yesButton, noButton);
    VerticalLayout content = new VerticalLayout();
    private DeletionLogModel logModel ;
    private Employments selectedEmployee;

    public DeletionConfirmationWindow(DeletionLogModel logModel, Grid grid) {
        super("Delete Employmees"); // Set window caption
        this.logModel =logModel;
        init(grid);

        yesButton.addClickListener(e -> {

            Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();

            for (Object itemId: selection.getSelectedRows()) {
                selectedEmployee = (Employments)itemId;
                grid.getContainerDataSource().removeItem(itemId);

               if(! logModel.createLog("Abeer Alkhars",
                       selectedEmployee.getFirstName()+" "+
                               selectedEmployee.getLastName(), new Date())){

                   new Notification("Deletion log is not saved", Notification.TYPE_ERROR_MESSAGE)
                           .show(getUI().getPage());
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


    public DeletionConfirmationWindow(DeletionLogModel logModel, Grid grid , ClickableRenderer.RendererClickEvent e) {

        super("Delete Employee"); // Set window caption
        this.logModel = logModel;
        init(grid);
        Employments selectedEmployee = (Employments)e.getItemId();

        yesButton.addClickListener(event -> {
            grid.getContainerDataSource()
                    .removeItem(e.getItemId());
            if(! logModel.createLog("Abeer Alkhars", selectedEmployee.getFirstName()+" "+
                    selectedEmployee.getLastName(), new Date())){
                new Notification("Deletion log is not saved", Notification.TYPE_ERROR_MESSAGE)
                        .show(getUI().getPage());
                return;
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


