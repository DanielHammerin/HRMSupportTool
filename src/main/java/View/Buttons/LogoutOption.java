package View.Buttons;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import View.DefaultUI.LogoutConfirmationWindow;

/**
 * Created by Abeer on 4/13/2016.
 */
public class LogoutOption extends HorizontalLayout {

    /**
     * Constructor of the layout showing who's connected and the logout button
     * @param currentUser the current user
     */
    @Autowired
    public LogoutOption(String currentUser){

        Label CurrentUserLabel = new Label("You are logged in as " + currentUser);
        Button logoutButton = new Button("Logout", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().addWindow(new LogoutConfirmationWindow());
            }
        });
        addComponents(CurrentUserLabel, logoutButton);
        setSpacing(true);
    }
}
