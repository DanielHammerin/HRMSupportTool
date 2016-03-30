package userInterface.DefaultUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends HorizontalLayout implements View {
    public static final String VIEW_NAME = "";
    private final boolean CURRENT_MEMBERS = true;

    @PostConstruct
    void init() {
        this.setSpacing(true);

        String thisHeight = String.valueOf(this.getHeight());

        LogTable logTable = new LogTable(thisHeight);

        EmployeesTable membersTable = new EmployeesTable("Current Members", thisHeight, CURRENT_MEMBERS);

        EmployeeInfoTab memberInfoTab = new EmployeeInfoTab();

        addComponents(logTable, membersTable, memberInfoTab);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
