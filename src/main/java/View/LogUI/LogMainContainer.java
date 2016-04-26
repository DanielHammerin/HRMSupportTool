package View.LogUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import View.Buttons.LogoutHLayout;
import View.DefaultUI.DefaultView;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * The page for the log info table
 * Created by Hatem on 3/20/2016.
 */

@SpringView(name = LogMainContainer.VIEW_NAME)
public class LogMainContainer extends VerticalLayout implements View {

    public static final String VIEW_NAME = "log";
    private LogoutHLayout logoutHLayout;
    private LogGrid logGrid;

    @PostConstruct
    void init() throws IOException {

        logoutHLayout = new LogoutHLayout("Abeer Alkhars");
        logGrid = new LogGrid();
        setSpacing(true);
        setMargin(true);

        //Show the navigation button to show the Default deleting employments view
        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(viewNewPage("View Current members", DefaultView.VIEW_NAME));

        addComponents(navigationBar, logoutHLayout, logGrid);

        setComponentAlignment(logoutHLayout, Alignment.TOP_RIGHT);
        setComponentAlignment(logGrid, Alignment.MIDDLE_CENTER);
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
