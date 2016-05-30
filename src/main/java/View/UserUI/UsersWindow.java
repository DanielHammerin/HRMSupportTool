package View.UserUI;

import Presenter.UserPresenter;
import View.Buttons.LogoutOption;
import View.DatabaseSelection.DatabaseSelectionWindow;
import View.DefaultUI.DeletingEmploymentsWindow;
import View.LoginUI.LoginWindow;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Abeer on 5/19/2016.
 */
@SpringView(name =UsersWindow.VIEW_NAME)
public class UsersWindow extends VerticalLayout implements View {

    public static final String VIEW_NAME = "users";
    private Panel content;
    private LogoutOption logoutHLayout;

    private Button viewEmploymentsButton;
    private Button viewDatabaseSelection;
    private Button addUser;

    private HorizontalLayout buttonsLayout,viewsLayout;

    private UserPresenter userPresenter;

    /**
     * Constructor of the user window
     */
    public  UsersWindow(){
        userPresenter = new UserPresenter(this);
        // logout layout
        logoutHLayout = new LogoutOption(String.valueOf(UI.getCurrent().getSession().getAttribute("user")));
        // navigation buttons for the window
        viewDatabaseSelection= new Button("View Database Selection");
        viewEmploymentsButton = new Button("View Current Employments");
        addUser = new Button("New user");
        viewsLayout= new HorizontalLayout();
        buttonsLayout= new HorizontalLayout();
        // for the window content
        content=new Panel();
    }

    /**
     * Method to add components to window
     */
    private void addWindowComponents() {
        // set actions to the view buttons
        viewDatabaseSelection.addClickListener(e -> {
            getUI().getNavigator().navigateTo(DatabaseSelectionWindow.VIEW_NAME);
        });

        viewEmploymentsButton.addClickListener(e -> {
            getUI().getNavigator().navigateTo(DeletingEmploymentsWindow.VIEW_NAME);
        });

        addUser.addClickListener(e -> {
            UI.getCurrent().addWindow(new UserInfoView(userPresenter));
        });

        buttonsLayout.addComponents(viewDatabaseSelection,viewEmploymentsButton, addUser);
        buttonsLayout.setSpacing(true);
        viewsLayout.setSpacing(true);
        setSpacing(true);
        setMargin(true);

        content.setContent(new UserGrid(userPresenter));
        addComponents( logoutHLayout,buttonsLayout,content);
        setComponentAlignment(logoutHLayout, Alignment.BOTTOM_RIGHT);
    }

    /**
     * Method to update the grid of users
     */
    public void updateUsersGrid(){
        content.setContent(new UserGrid(userPresenter));
    }

    /**
     * Method to show a success notification
     * @param msg the notification to display
     */
    public void showSuccessNotification(String msg){
        Notification.show(msg);
    }

    /**
     * Method to show a error notification
     * @param msg the notification to display
     */
    public void showErrorNotification(String msg){
        new Notification(msg, Notification.TYPE_ERROR_MESSAGE)
                .show(getUI().getPage());
    }

    /**
     * Method called when the window is created
     * @param viewChangeEvent
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        if ((getUI().getSession().getAttribute("user") == null)||getUI().getSession().getAttribute("isAdmin")==null) {
            System.out.println("Not connected");
            getUI().getNavigator().navigateTo(LoginWindow.VIEW_NAME);
        } else { boolean isAdmin =(boolean)getUI().getSession().getAttribute("isAdmin");
             if(isAdmin){
            addWindowComponents();}
            else
                 getUI().getNavigator().navigateTo(DatabaseSelectionWindow.VIEW_NAME);
        }
    }}





