package userInterface.DefaultUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends HorizontalLayout implements View {
    public static final String VIEW_NAME = "default";

    @PostConstruct
    void init() {
        this.setSpacing(true);

        EmployeesGrid membersTable = new EmployeesGrid();

        EmployeeInfoTab memberInfoTab = new EmployeeInfoTab();

        addComponents(membersTable);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
