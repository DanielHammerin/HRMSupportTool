package View.LogUI;

import Model.Entity.DeletionLog;
import Presenter.DeletingEmploymentsPresenter;
import View.DefaultUI.DeletionLogInfo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Abeer on 4/13/2016.
 * logGrid class creates grid contains deletion log
 */
@SpringComponent
@UIScope
public class LogGrid extends VerticalLayout {

    private  Collection collection =  new ArrayList<>();
    private  Grid grid;
    private  BeanItemContainer<DeletionLog> container;
    private GeneratedPropertyContainer gpc;

    /**
     * The contructor for the log grid
     * @param employemtsPresenter the presenter of the window that use this grid
     * @throws IOException
     */
    @Autowired
    public LogGrid (DeletingEmploymentsPresenter employemtsPresenter) throws IOException {
        collection = employemtsPresenter.readDeletionLog();
        // BeanItemContainer contains deletionlog collection
          container = new BeanItemContainer<DeletionLog>(DeletionLog.class,collection);
        //  container is wrapped into GeneratedPropertyContainer
          gpc = new GeneratedPropertyContainer(container);
          gpc.addGeneratedProperty("Show Information",
                new PropertyValueGenerator<String>() {

                    @Override
                    public Class<String> getType() {
                        return String.class;
                    }

                    @Override
                    public String getValue(Item item, Object itemId,
                                           Object propertyId) {
                        return "Show Info";
                    }
                });
        initGrid();
        initFilters();
        setSpacing(true);
        this.addComponent(grid);
    }

    /**
     * Method to init the grid
     */
    private void initGrid(){
       grid = new Grid(gpc);
       grid.setSelectionMode(Grid.SelectionMode.NONE);
       grid.focus();

       // buttons to show log info
       grid.getColumn("Show Information")
               .setRenderer(new ButtonRenderer(e ->{
                   DeletionLog log = (DeletionLog)e.getItemId();
                   UI.getCurrent().addWindow(new DeletionLogInfo(log));
               }
               ));
   }

    /**
     * Method to init the filters
     */
    private void initFilters(){
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
             grid.setWidth("100%");
       }
    }
}
