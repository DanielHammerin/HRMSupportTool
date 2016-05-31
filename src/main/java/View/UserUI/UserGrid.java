package View.UserUI;

import Model.Entity.User;
import Presenter.UserPresenter;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.Reindeer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Abeer on 20/5/2016.
 * Edited by Hatem on 31/5/2016
 * class that init HRM users in a grid
 */
public class UserGrid extends VerticalLayout {

    /**
     * Constructor of the user grid
     * @param userPresenter the presenter of the view that called this grid (UserPresenter)
     */
    public UserGrid (UserPresenter userPresenter){
        // read  users objects
        Collection collection = userPresenter.readUsers();

        BeanItemContainer container = new BeanItemContainer<User>(User.class,collection);
        GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(container);
        gpc.addGeneratedProperty("Edit",
                new PropertyValueGenerator<String>() {

                    @Override
                    public Class<String> getType() {
                        return String.class;
                    }

                    @Override
                    public String getValue(Item item, Object itemId,
                                           Object propertyId) {
                        return "Edit";
                    }
                });

        Grid grid = new Grid(gpc);

        grid.removeColumn("password");
        grid.setColumnOrder("id","firstName","lastName","username","email");
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.getColumn("Edit")
                .setRenderer(new ButtonRenderer(e ->{
                    User user = (User)e.getItemId();
                    UI.getCurrent().addWindow(new UserInfoView(user,userPresenter));
                }
                ));
        grid. setHeightMode(HeightMode.ROW);
        addComponent(grid);
    }
}
