package userInterface.DeletedUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import userInterface.DefaultUI.EmployeesTable;

import javax.annotation.PostConstruct;

@UIScope
@SpringView(name = DeletedEmployments.VIEW_NAME)
public class DeletedEmployments extends VerticalLayout implements View {
    public static final String VIEW_NAME = "deleted";
    private final boolean DELETED_MEMBERS = false;

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);

        EmployeesTable deletedMembersTab = new EmployeesTable("Deleted Staff Members", String.valueOf(this.getHeight()), DELETED_MEMBERS);

        addComponent(deletedMembersTab);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }

}
