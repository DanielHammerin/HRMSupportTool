package View.LoginUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;

/**
 * Created by totte on 04.04.16.
 *
 * A VerticalLayout containing components of a login view.
 * Functionality is missing and needs to be implemented
 */
@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends GridLayout implements View {

    public static final String VIEW_NAME = "";

    //TODO: Fix positioning of components, hide password characters from field

    private Label userLabel;
    private Label passLabel;
    private Button loginButton;
    private TextField userField;
    private TextField passField;
    private HorizontalLayout userHlayout;
    private HorizontalLayout passHlayout;

    @PostConstruct
    void init() {

        userLabel = new Label("Username");
        passLabel = new Label("Password");
        userField = new TextField();
        passField = new TextField();

        //Button
        loginButton = new Button("Login", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                userField.getValue(); //This just show you how to get the data from the input by user
                passField.getValue();
            }
        });

        //Create and set internal layouts for user and password components
        userHlayout = new HorizontalLayout(userLabel, userField);
        passHlayout = new HorizontalLayout(passLabel, passField);

        //Size and position of components
        space(); space(); space(); space(); space(); space(); //a very ugly solution to move down all components on page
        addComponents(userHlayout, passHlayout, loginButton);

        setWidth("100%");
        setComponentAlignment(userHlayout, Alignment.MIDDLE_CENTER);
        setComponentAlignment(passHlayout, Alignment.MIDDLE_CENTER);
        setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);

        setSpacing(true);
        userHlayout.setSpacing(true);
        passHlayout.setSpacing(true);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
