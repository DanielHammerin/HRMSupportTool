package View.DatabaseSelection;


import Presenter.DatabaseSelectionPresenter;
import View.LoginUI.LoginWindow;
import com.vaadin.ui.*;
import View.DefaultUI.DeletingEmploymentsWindow;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import View.Buttons.LogoutOption;

/**
 * Created by Abeer on 04.06.16.
 * Edited by Hatem Houssein on 04/05/2016
 *
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

    public DatabaseSelectionWindow(){

        //init();
    }

    private void init() {
        databaseCombobox = new ComboBox();
        DBselectionPresenter = new DatabaseSelectionPresenter(this);
        Panel panel = new Panel("HRM Databases");
        panel.setSizeUndefined(); // Shrink to fit content
        addComponent(panel);
        logoutHLayout = new LogoutOption(String.valueOf(UI.getCurrent().getSession().getAttribute("user")));
        databaseLabel = new Label("HRM Databases: ");

        databaseCombobox.setInputPrompt("select one ");
        databaseCombobox.setInvalidAllowed(false);
        databaseCombobox.setNullSelectionAllowed(false);
        // databaseCombobox.addItems("database 1", "database 2", "database 3", "database 4");

        //Button
        OKButton = new Button("OK");
        OKButton.addClickListener(new Button.ClickListener() {
            //TODO: Do not proceed if no databases are chosen
            public void buttonClick(Button.ClickEvent event) {
                databaseCombobox.getValue();
                DBselectionPresenter.handleSelectedDB( databaseCombobox.getValue());

            //    getUI().getNavigator().navigateTo(DeletingEmploymentsWindow.VIEW_NAME+"/"+  databaseCombobox.getValue());

            }
        });

        databaseHlayout = new HorizontalLayout(databaseLabel, databaseCombobox);
        databaseHlayout.setSpacing(true);


        content = new FormLayout();

        addComponent(logoutHLayout);
        content.addComponent(databaseHlayout);
        content.addComponent(OKButton);
        content.setSizeUndefined(); // Shrink to fit
        content.setMargin(true);
        panel.setContent(content);


        setSpacing(true);
        addComponent(panel);


        setWidth("100%");
        setComponentAlignment(logoutHLayout, Alignment.TOP_RIGHT);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        setMargin(true);
    }


    @Override
    public void enter(ViewChangeEvent event) {

    if(getUI().getSession().getAttribute("user")!=null){
       init();
    }
        else
        getUI().getNavigator().navigateTo(LoginWindow.VIEW_NAME);

}
    public void showDBSelectionErrorMessage() {
        new Notification("No Database selected", Notification.TYPE_ERROR_MESSAGE)
                .show(getUI().getPage());
    }

    public void addDatabaseToSelection(String databaseName) {
        databaseCombobox.addItem(databaseName);
    }

}
