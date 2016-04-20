package View.DefaultUI;

import Model.Employments;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ClickableRenderer;

/**
 * Created by Abeer on 4/13/2016.
 */
public class EmployeeDeletionSubWindow extends Window {

    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");
    HorizontalLayout actions = new HorizontalLayout(yesButton, noButton);
    VerticalLayout content = new VerticalLayout();


    public EmployeeDeletionSubWindow(Grid grid , ClickableRenderer.RendererClickEvent e) {

        super("Delete Employee"); // Set window caption
        Employments emp = (Employments)e.getItemId();

        center();
        actions.setSpacing(true);
        setModal(true);
        setClosable(false);
        setResizable(false);
        content.setMargin(true);
        content.setSpacing(true);


        content.addComponent(new Label("Are you sure you want to delete '"+emp.getFirstName()+" "+emp.getLastName()+"'?"));



        yesButton.addClickListener(event -> {

            grid.getContainerDataSource()
                    .removeItem(e.getItemId());
            Notification.show("Detetion is done successfully");
            close();

        });
        noButton.addClickListener(event -> {
            close();
        });

        content.addComponent( actions);

        setContent(content);
    }
}
