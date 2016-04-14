package userInterface.LogUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import userInterface.LogoutHLayout;

import javax.annotation.PostConstruct;

/**
 * The page for the log info table
 * Created by Hatem on 3/20/2016.
 */
@UIScope
@SpringView(name = LogMainContainer.VIEW_NAME)
public class LogMainContainer extends VerticalLayout implements View {

    public static final String VIEW_NAME = "log";

    @PostConstruct
    void init() {

      //  setSizeFull();

        LogoutHLayout logoutHLayout = new LogoutHLayout("Abeer Alkhars");
        LogGrid logGrid = new LogGrid();

        addComponent(logoutHLayout);
        addComponent(logGrid);
        setSpacing(true);
        setMargin(true);
        setComponentAlignment(logoutHLayout, Alignment.TOP_RIGHT);
        setComponentAlignment(logGrid, Alignment.MIDDLE_CENTER);


    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }

}
