package userInterface.DefaultUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import userInterface.LogoutHLayout;

import javax.annotation.PostConstruct;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "default";

    @PostConstruct
    void init() {


        EmployeesGrid membersTable = new EmployeesGrid();

        LogoutHLayout logoutHLayout = new LogoutHLayout("Abeer Alkhars");
        addComponent(logoutHLayout);
        addComponent(membersTable);
        setSpacing(true);
        setMargin(true);
        setComponentAlignment(logoutHLayout, Alignment.BOTTOM_RIGHT);
        setComponentAlignment(membersTable, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
