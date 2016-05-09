package userInterface;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import userInterface.DatabaseSelection.DatabaseSelection;

import userInterface.DefaultUI.DefaultView;
import userInterface.LogUI.LogGrid;
import userInterface.LogUI.LogMainContainer;
import userInterface.LoginUI.LoginView;

import java.io.IOException;

/**
 * A demo interface for HRM. This is the "main container" where everything is put together
 * Created by Hatem on 3/19/2016.
 */

@Theme("valo")
@SpringUI
public class WebAppUI extends UI{

    @Autowired
    private SpringViewProvider viewProvider;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(viewNewPage("View login", LoginView.VIEW_NAME));
        navigationBar.addComponent(viewNewPage("View Database selection", DatabaseSelection.VIEW_NAME));
        navigationBar.addComponent(viewNewPage("View Log", LogMainContainer.VIEW_NAME));
        navigationBar.addComponent(viewNewPage("View Current members", DefaultView.VIEW_NAME));
        root.addComponent(navigationBar);


        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
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
