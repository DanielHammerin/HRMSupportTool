package userInterface.LogUI;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import userInterface.DefaultUI.EmployeesTable;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        setMargin(true);
        setSpacing(true);

        //Searches the log by any info e.x. day, time or employee
        TextField searchField = new TextField();
        searchField.setInputPrompt("Search log");

        //The table for log info
        LogTable logTable = new LogTable();

        addComponents(searchField, logTable);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }

}
