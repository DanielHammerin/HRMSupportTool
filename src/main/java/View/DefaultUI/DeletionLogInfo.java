package View.DefaultUI;

import Model.Entity.DeletionLog;
import com.vaadin.ui.*;

/**
 * Created by Abeer on 5/13/2016.
 * sub window to show deletion log information
 */
public class DeletionLogInfo  extends Window {
    Button closeButton = new Button("Close");
    HorizontalLayout action = new HorizontalLayout(closeButton);
    VerticalLayout content = new VerticalLayout();
    public DeletionLogInfo(DeletionLog log){
         // Set window caption
        super("log Information");
        center();
        action.setSpacing(true);
        setModal(true);
        setClosable(false);
        content.setMargin(true);
        content.setSpacing(true);
        content.setSizeFull();
        setWidth("30%");
        setHeight("70%");

        // Label containing information you want to display
        Label whoDelete = new Label("Who delete : " + log.getWhoDelete());
        Label deletedPersonID = new Label("Deleted Person ID : " + log.getDeletedId());
        Label deletedFirstName = new Label("Deleted Last Name: " + log.getDeletedFirstName());
        Label deletedLastName = new Label("Deleted last Name : " + log.getDeletedLastName());
       Label deletionDate = new Label ("Deletion Date: "+log.getDate());
        // Close the pop-ip window
        closeButton.addClickListener(e -> {
            close();
        });

        // Add the label created before
        content.addComponents(new Label("log Information"), whoDelete, deletedPersonID,
                deletedFirstName, deletedLastName,deletionDate);
        content.setSpacing(true);
        content.addComponent(action);
        setContent(content);
    }

}
