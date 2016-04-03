package userInterface.DefaultUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import userInterface.LogUI.LogMainContainer;

import javax.annotation.PostConstruct;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends HorizontalLayout implements View {
    public static final String VIEW_NAME = "";

    @PostConstruct
    void init() {
        this.setSpacing(true);

        EmployeesTable membersTable = new EmployeesTable();

        EmployeeInfoTab memberInfoTab = new EmployeeInfoTab();

        addComponents(membersTable, memberInfoTab);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
