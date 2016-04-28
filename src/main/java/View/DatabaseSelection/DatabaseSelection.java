package View.DatabaseSelection;


import com.vaadin.ui.*;
import View.DefaultUI.DeletingEmploymentsWindow;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import View.Buttons.LogoutOption;

/**
 * Created by Abeer on 04.06.16.
 *
 *
 */


@SpringView(name = DatabaseSelection.VIEW_NAME)
public class DatabaseSelection extends VerticalLayout implements View{
    public static final String VIEW_NAME = "databaseSelection";

    private Label databaseLabel;
    private HorizontalLayout databaseHlayout;
    private ComboBox databaseCombobox;
    private Button OKButton;
    private FormLayout content;
    private LogoutOption logoutHLayout;

    public DatabaseSelection (){
        Panel panel = new Panel("HRM Databases");
        panel.setSizeUndefined(); // Shrink to fit content
        addComponent(panel);
        logoutHLayout = new LogoutOption(String.valueOf(UI.getCurrent().getSession().getAttribute("user")));
        databaseLabel = new Label("HRM Databases: ");
        databaseCombobox = new ComboBox();
        databaseCombobox.setInputPrompt("select one ");
        databaseCombobox.setInvalidAllowed(false);
        databaseCombobox.setNullSelectionAllowed(false);
        databaseCombobox.addItems("database 1", "database 2", "database 3", "database 4");

        //Button
        OKButton = new Button("OK");
        OKButton.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                databaseCombobox.getValue();
                getUI().getNavigator().navigateTo(DeletingEmploymentsWindow.VIEW_NAME+"/"+  databaseCombobox.getValue());

            }
        });

        OKButton.setDisableOnClick(true);
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


        }



}
