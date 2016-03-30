package userInterface.DefaultUI;

import com.vaadin.server.Sizeable;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A table for the log info
 * Created by Hatem on 3/20/2016.
 */

@SpringComponent
@UIScope
public class LogTable extends Table{
    @Autowired
    public LogTable(String height) {
        this.setStyleName(ValoTheme.TABLE_COMPACT);
        this.addContainerProperty("Log Info", TextArea.class, null);
        this.addContainerProperty("Time", String.class, null);
        this.setCaption("Latest Updates");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String timeInfo = dateFormat.format(date);
        TextArea logInfo = new TextArea();

        this.setEnabled(false);
        this.setWidth(10, Sizeable.Unit.CM);
        this.setHeight(height);

        logInfo.setWordwrap(true);
        logInfo.setValue("Staff Member 1 deleted Staff Members 2 and 3");

        this.addItem(new Object[]{logInfo, timeInfo}, 1);
    }
}
