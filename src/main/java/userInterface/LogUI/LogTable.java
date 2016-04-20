package userInterface.LogUI;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The table that has all the log info
 * Created by Hatem on 3/31/2016.
 */
//@SpringComponent
//@UIScope
public class LogTable extends Table{

    @Autowired
    public LogTable() {

        this.setStyleName(ValoTheme.TABLE_COMPACT);
        //Adding columns
        this.addContainerProperty("Day", String.class, null);
        this.addContainerProperty("Time", String.class, null);
        this.addContainerProperty("Who deleted", String.class, null);
        this.addContainerProperty("Who was deleted", String.class, null);

        this.setCaption("Latest Updates");

        //Dummy day and time
        DateFormat dayFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String dayInfo = dayFormat.format(date);
        String timeInfo = timeFormat.format(date);

        //Dummy Employee that deleted
        String whoDeleted = "Employee 1";

        //Dummy Employee that was deleted
        String whoWasDeleted = "Employee 999";

        this.setEnabled(false);
        this.setWidth("100%");

        this.addItem(new Object[]{dayInfo, timeInfo, whoDeleted, whoWasDeleted}, 1);
    }
}
