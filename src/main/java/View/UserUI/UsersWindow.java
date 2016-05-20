package View.UserUI;

import Model.Entity.User;
import Presenter.UserPresenter;
import View.Buttons.LogoutOption;
import View.DatabaseSelection.DatabaseSelectionWindow;
import View.DefaultUI.DeletingEmploymentsWindow;
import View.LogUI.LogGrid;
import View.LoginUI.LoginWindow;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Abeer on 5/19/2016.
 */@SpringView(name =UsersWindow.VIEW_NAME)
public class UsersWindow extends VerticalLayout implements View {
    public static final String VIEW_NAME = "users";
    private Panel content;
    private LogoutOption logoutHLayout;
    private Button viewEmploymentsButton,viewDatabaseSelection, viewUser;
    private HorizontalLayout buttonsLayout,viewsLayout;

    private UserInfoView newUserView;
    private UserPresenter userPresenter;
    private GeneratedPropertyContainer gpc;

    public  UsersWindow(){
        userPresenter = new UserPresenter(this);
        logoutHLayout = new LogoutOption(String.valueOf(UI.getCurrent().getSession().getAttribute("user")));
        viewDatabaseSelection= new Button("View Database Selection");
        viewEmploymentsButton = new Button("View Current Employments");
        viewUser= new Button("View Users");
        viewsLayout= new HorizontalLayout();
        buttonsLayout= new HorizontalLayout();
        content=new Panel();
    }
    private void addWindowComponents() {

        viewDatabaseSelection.addClickListener(e -> {
            getUI().getNavigator().navigateTo(DatabaseSelectionWindow.VIEW_NAME);
        });

        viewEmploymentsButton.addClickListener(e -> {
            getUI().getNavigator().navigateTo(DeletingEmploymentsWindow.VIEW_NAME);


        });
        viewUser.addClickListener(e -> {
            UI.getCurrent().addWindow(new UserInfoView(userPresenter));
        });

        buttonsLayout.addComponents(viewDatabaseSelection,viewEmploymentsButton,viewUser);
        viewsLayout.setSpacing(true);
        setSpacing(true);
        setMargin(true);

       content.setContent(new UserGrid(userPresenter));
        addComponents( logoutHLayout,buttonsLayout,content);
        setComponentAlignment(logoutHLayout, Alignment.BOTTOM_RIGHT);

    }
    public void updateUserGrid(){
        content.setContent(new UserGrid(userPresenter));
    }
public void showSuccessNotification(String msg){
    Notification.show(msg);
}
    public void showErrorNotification(String msg){
        new Notification(msg, Notification.TYPE_ERROR_MESSAGE)
                .show(getUI().getPage());
        return;

    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        if ((getUI().getSession().getAttribute("user") == null)) {
            System.out.println("Not connected");
            getUI().getNavigator().navigateTo(LoginWindow.VIEW_NAME);
        } else {

                addWindowComponents();
            }
    }}




