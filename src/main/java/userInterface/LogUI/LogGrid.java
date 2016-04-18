package userInterface.LogUI;

import Model.DeletionLog;
import Model.FileRepo.logFileRepository;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Abeer on 4/13/2016.
 */
@SpringComponent
@UIScope
public class LogGrid extends VerticalLayout {

    private  logFileRepository logRepo;
    private  Collection collection =  new ArrayList<>();
    private  Grid grid;
    private  BeanItemContainer<DeletionLog> container;



    @Autowired
    public LogGrid () throws IOException {
        logRepo = new logFileRepository();

        //for(int i = 1; i <= 5; i++) {

     //      DeletionLog log = new DeletionLog ("whoDeleted"+i , "whoWasDeleted"+i, new Date());
         //   collection.add(log);
      //      logRepo.createDeletionLog(log);
       // }

        collection = logRepo.readDeletionLogs();
        container = new BeanItemContainer<DeletionLog>(DeletionLog.class,collection);
        grid = new Grid(container);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setWidth("500px");
        grid.setWidth(28, Unit.CM);
        grid.focus();
        // Create a header row to hold column filters
        Grid.HeaderRow filterRow = grid.appendHeaderRow();

        // Set up a filter for all columns
        for (Object pid: grid.getContainerDataSource()
                .getContainerPropertyIds()) {
            Grid.HeaderCell cell = filterRow.getCell(pid);

            // Have an input field to use for filter
            TextField filterField = new TextField();
            filterField.setColumns(15);
            filterField.setInputPrompt("Filter");
            filterField.addStyleName(ValoTheme.TEXTFIELD_TINY);

            // Update filter When the filter input is changed
            filterField.addTextChangeListener(change -> {
                // Can't modify filters so need to replace
                container.removeContainerFilters(pid);

                // (Re)create the filter if necessary
                if (! change.getText().isEmpty())
                    container.addContainerFilter(
                            new SimpleStringFilter(pid,
                                    change.getText(), true, false));
            });
            cell.setComponent(filterField);
            grid.setWidth("100%");
        }

        setSpacing(true);
        this.addComponent(grid);


    }




  //  public static void update(){
  //  grid.setContainerDataSource( new BeanItemContainer<DeletionLog>(DeletionLog.class,
               // logRepo.readDeletionLogs()));
   // }
}
