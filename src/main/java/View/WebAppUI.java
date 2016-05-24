package View;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * A demo interface for HRM. This is the "main container" where everything is put together
 * Created by Hatem on 3/19/2016.
 */


@SpringUI
public class WebAppUI extends UI{

    @Autowired
    private SpringViewProvider viewProvider;

    @Override
    protected void init(VaadinRequest request) {
         VerticalLayout root = new VerticalLayout();

        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.setStyleName(Reindeer.LAYOUT_BLUE);
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 2.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);

    }

    public void showWindow(String viewName){

    }
    }



