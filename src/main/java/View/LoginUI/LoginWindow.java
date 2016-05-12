package View.LoginUI;

import Presenter.LoginPresenter;
import View.DatabaseSelection.DatabaseSelectionWindow;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

/**
 * Created by totte on 04.04.16.
 * Edited by Hatem Houssein on 04/05/2016
 * Edited by Abeer Alkhars on 5/9/2016, changed the passField from TextField to passwordField
 *
 * A VerticalLayout containing components of a login view.
 * Functionality is missing and needs to be implemented
 */

@SpringView(name = LoginWindow.VIEW_NAME)
public class LoginWindow extends GridLayout implements View {

    public static final String VIEW_NAME = "";

    private LoginPresenter presenter;

    private Label userLabel;
    private Label passLabel;
    private Button loginButton;
    private TextField userField;
    private PasswordField  passField;
    private HorizontalLayout userHlayout;
    private HorizontalLayout passHlayout;

    //@PostConstruct
    //void init() {
    public LoginWindow(){

        presenter = new LoginPresenter(this);

        userLabel = new Label("Username");
        passLabel = new Label("Password");
        userField = new TextField();
        passField = new PasswordField ();

        //Button
        loginButton = new Button("Login", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                presenter.loginEmployee(userField.getValue(), passField.getValue()); //This just show you how to get the data from the input by user

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

    /**
     * A method to open the Database Selection window when the login credentials are correct
     */
    public void showDatabaseSelectionWindow(){
        getUI().getSession().setAttribute("user", userField.getValue());
        getUI().getNavigator().navigateTo(DatabaseSelectionWindow.VIEW_NAME);
    }

    /**
     * A method to show an error message when the login credentials are incorrect
     */
    public void showLoginErrorMessage(){
        passField.clear();
        new Notification("Incorrect username/password", Notification.TYPE_ERROR_MESSAGE)
                .show(getUI().getPage());

    }
}
