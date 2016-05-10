package View.LogUI;

import View.LoginUI.LoginWindow;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import View.Buttons.LogoutOption;
import View.DefaultUI.DeletingEmploymentsWindow;
import java.io.IOException;

/**
 * The page for the log info table
 * Created by Hatem on 3/20/2016.
 * modified by Abeer on 5/10/2016
 */

@SpringView(name = LogWindow.VIEW_NAME)
public class LogWindow extends VerticalLayout implements View {

    public static final String VIEW_NAME = "log";
    private LogoutOption logoutHLayout;
    private LogGrid logGrid;

    public LogWindow () throws IOException {

    }

    private void init() throws IOException {
        //TODO: Change the session of the user to thread
        logoutHLayout = new LogoutOption(String.valueOf(UI.getCurrent().getSession().getAttribute("user")));
        logGrid = new LogGrid();
        setSpacing(true);
        setMargin(true);

        //Show the navigation button to show the Default deleting employments view
        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(viewNewPage("View Current members", DeletingEmploymentsWindow.VIEW_NAME));

        addComponents(navigationBar, logoutHLayout, logGrid);

        setComponentAlignment(logoutHLayout, Alignment.TOP_RIGHT);
        setComponentAlignment(logGrid, Alignment.MIDDLE_CENTER);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        if(getUI().getSession().getAttribute("user")!=null){
            try {
                init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            getUI().getNavigator().navigateTo(LoginWindow.VIEW_NAME);


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
