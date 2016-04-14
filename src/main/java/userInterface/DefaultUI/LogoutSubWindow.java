package userInterface.DefaultUI;

import java.util.function.Consumer;

import Model.Employments;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import userInterface.LoginUI.LoginView;

/**
 * Created by Abeer on 04.06.16.
 *
 *
 */
public class LogoutSubWindow  extends Window  {


    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");
    HorizontalLayout actions = new HorizontalLayout(yesButton, noButton);
    VerticalLayout content = new VerticalLayout();

    public LogoutSubWindow() {
        super("Logout  person"); // Set window caption
        center();
        actions.setSpacing(true);
        setModal(true);
        setClosable(false);
        setResizable(false);
        content.setMargin(true);
        content.setSpacing(true);

        //
        Label configuration  = new Label("Are you sure you want to log out ");




        yesButton.addClickListener(e -> {
            getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
            close();

        });
        noButton.addClickListener(e -> {
            close();
        });

        content.addComponents(configuration, actions);

        setContent(content);
    }
}
