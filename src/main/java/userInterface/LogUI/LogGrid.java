package userInterface.LogUI;

import Model.DeletionLog;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Abeer on 4/13/2016.
 */
public class LogGrid extends VerticalLayout {
    @Autowired
    public LogGrid (){

        Collection collection =  new ArrayList<>();
        for(int i = 1; i <= 5; i++) {

           DeletionLog log = new DeletionLog ("whoDeleted"+i , "whoWasDeleted"+i, new Date());
            collection.add(log);
        }
        BeanItemContainer<DeletionLog> container = new BeanItemContainer<DeletionLog>(DeletionLog.class,collection);
        Grid grid = new Grid(container);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setWidth("500px");
        grid.setWidth(28, Unit.CM);
        IndexedContainer indexedcontainer = new IndexedContainer();

// Create a header row to hold column filters
        Grid.HeaderRow filterRow = grid.appendHeaderRow();

// Set up a filter for all columns
        for (Object pid: grid.getContainerDataSource()
                .getContainerPropertyIds()) {
            Grid.HeaderCell cell = filterRow.getCell(pid);

            // Have an input field to use for filter
            TextField filterField = new TextField();
            filterField.setColumns(8);
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
        }
        addComponent(grid);
    }
}
