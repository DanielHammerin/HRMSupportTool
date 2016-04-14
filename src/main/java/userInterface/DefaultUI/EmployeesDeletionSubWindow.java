package userInterface.DefaultUI;

import com.vaadin.ui.*;

/**
 * Created by Abeer on 4/13/2016.
 */
public class EmployeesDeletionSubWindow extends Window {

    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");
    HorizontalLayout actions = new HorizontalLayout(yesButton, noButton);
    VerticalLayout content = new VerticalLayout();

    public EmployeesDeletionSubWindow(Grid grid) {
        super("Delete Employmees"); // Set window caption
        center();
        actions.setSpacing(true);
        setModal(true);
        setClosable(false);
        setResizable(false);
        content.setMargin(true);
        content.setSpacing(true);


        Label configMSG = new Label("Are you sure you want to delete the selected employees?");


        //Configuration

        yesButton.addClickListener(e -> {

            Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();

            for (Object itemId: selection.getSelectedRows())
                grid.getContainerDataSource().removeItem(itemId);

            // Otherwise out of sync with container
            grid.getSelectionModel().reset();
            close();
            Notification.show("Deteting is done successfully");


        });
        noButton.addClickListener(e -> {
            close();
            grid.getSelectionModel().reset();
        });

        content.addComponents(configMSG, actions);

        setContent(content);
    }
}
