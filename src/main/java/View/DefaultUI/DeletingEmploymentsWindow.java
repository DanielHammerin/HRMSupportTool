package View.DefaultUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import View.Buttons.LogoutOption;
import View.LogUI.LogWindow;
import javax.annotation.PostConstruct;

@SpringView(name = DeletingEmploymentsWindow.VIEW_NAME)
public class DeletingEmploymentsWindow extends VerticalLayout implements View {
    public static final String VIEW_NAME = "default";

    private EmploymentsView membersTable;
    private LogoutOption logoutHLayout;

    @PostConstruct
    void init() {

        membersTable = new EmploymentsView();

        logoutHLayout = new LogoutOption("Abeer Alkhars");

        //Show the navigation button to show the Log view
        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(viewNewPage("View Log", LogWindow.VIEW_NAME));

        addComponents(navigationBar, logoutHLayout, membersTable);
        setSpacing(true);
        // setSizeFull();
        setMargin(true);
        setComponentAlignment(membersTable, Alignment.MIDDLE_CENTER);
        setComponentAlignment(logoutHLayout, Alignment.BOTTOM_RIGHT);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }

    private Button viewNewPage(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(event -> {
            getUI().getNavigator().navigateTo(viewName);
        });
        return button;
    }

}
