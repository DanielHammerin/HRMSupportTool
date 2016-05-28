package View.DatabaseSelection;


import Presenter.DatabaseSelectionPresenter;
import View.LoginUI.LoginWindow;
import com.vaadin.ui.*;
import View.DefaultUI.DeletingEmploymentsWindow;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import View.Buttons.LogoutOption;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Abeer on 04.06.16.
 * Edited by Hatem Houssein on 04/05/2016 and 11/05/2016
 */

@SpringView(name = DatabaseSelectionWindow.VIEW_NAME)
public class DatabaseSelectionWindow extends VerticalLayout implements View{

    public static final String VIEW_NAME = "databaseSelection";

    private Label databaseLabel;
    private HorizontalLayout databaseHlayout;
    private ComboBox databaseCombobox;
    private Button OKButton;
    private FormLayout content;
    private LogoutOption logoutHLayout;
    private DatabaseSelectionPresenter DBselectionPresenter;
    private Panel panel ;

    public DatabaseSelectionWindow(){
        databaseCombobox = new ComboBox();
        DBselectionPresenter = new DatabaseSelectionPresenter(this);
        panel = new Panel("HRM Databases");
        panel.setSizeUndefined(); // Shrink to fit content
        logoutHLayout = new LogoutOption(String.valueOf(UI.getCurrent().getSession().getAttribute("user")));
        databaseLabel = new Label("HRM Databases: ");

        databaseCombobox.setInputPrompt("select one ");
        databaseCombobox.setInvalidAllowed(false);
        databaseCombobox.setNullSelectionAllowed(false);

        //Button
        OKButton = new Button("OK");
        OKButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                DBselectionPresenter.handleSelectedDB(databaseCombobox.getValue());
            }

        });
        databaseHlayout = new HorizontalLayout(databaseLabel, databaseCombobox);
        databaseHlayout.setSpacing(true);
        content = new FormLayout();

        content.addComponent(databaseHlayout);
        content.addComponent(OKButton);
        content.setSizeUndefined(); // Shrink to fit
        content.setMargin(true);
        panel.setContent(content);

        setSpacing(true);
        setWidth("100%");
        setMargin(true);
    }

    /**
     * Method called when we enter in the view, add the components to this window
     */
    private void addWindowComponents(){
        addComponents(logoutHLayout,panel);
        setComponentAlignment(logoutHLayout, Alignment.TOP_RIGHT);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    /**
     * Method called when we naviguate to this view
     * @param event
     */
    @Override
    public void enter(ViewChangeEvent event) {
        if (getUI().getSession().getAttribute("user") != null) {
            addWindowComponents();
        } else {
            // No user connected, return to the LoginWindow
            getUI().getNavigator().navigateTo(LoginWindow.VIEW_NAME);
        }
    }

    /**
     * Method a show a notification when an error occured
     */
    public void ShowErrorNotification(String msg){
        new Notification(msg, Notification.TYPE_ERROR_MESSAGE).show(getUI().getPage());
    }

    /**
     * Method called when the DB is selected and tha tthe connection is possible
     * @param databaseName the database name registred in a session variable
     * @param connectionString  the connection string registred in a session variable
     */
    public void showDeletingEmploymentsWindow(String databaseName, String connectionString){
        // Create a Session Variable containing the connection string to create the DAO from it
        getUI().getSession().setAttribute("databaseName", databaseName);
        getUI().getSession().setAttribute("connectionString", connectionString);
        getUI().getNavigator().navigateTo(DeletingEmploymentsWindow.VIEW_NAME);
    }

    /**
     * Method called bu the presenter to add a database to the selection
     * @param databaseName the name of the database
     */
    public void addDatabaseToSelection(String databaseName) {
        databaseCombobox.addItem(databaseName);
    }

}
