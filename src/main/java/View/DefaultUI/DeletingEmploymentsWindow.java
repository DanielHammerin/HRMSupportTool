package View.DefaultUI;
import Presenter.DeletingEmploymentsPresenter;
import View.DatabaseSelection.DatabaseSelectionWindow;
import View.LogUI.LogGrid;
import View.LoginUI.LoginWindow;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import View.Buttons.LogoutOption;
import View.LogUI.LogWindow;
import javax.annotation.PostConstruct;
import java.io.IOException;
/**
 * Created by Abeer on 5/10/2016.
 */
@SpringView(name = DeletingEmploymentsWindow.VIEW_NAME)
public class DeletingEmploymentsWindow extends VerticalLayout implements View {
    public static final String VIEW_NAME = "default";
    private HorizontalLayout ButtonsLayout;
    private Panel panel;
    private LogoutOption logoutHLayout;
    private Button viewlogButton;
    private Button viewEmploymentsButton;
    private Button viewDatabaseSeletionButton;
    private DeletingEmploymentsPresenter deletingEmploymentsPresenter ;

    public DeletingEmploymentsWindow( ) {
        ButtonsLayout = new HorizontalLayout();
        panel = new Panel();
        viewlogButton = new Button("View Log");
        viewEmploymentsButton = new Button("view Current Employments");
        viewDatabaseSeletionButton = new Button("view Database Seletion");
        logoutHLayout = new LogoutOption(String.valueOf(UI.getCurrent().getSession().getAttribute("user")));
        deletingEmploymentsPresenter = new DeletingEmploymentsPresenter(this);

    }
    private void addWindowComponents (String DB){
        // we should add the selected db as a field in the EmploymentView constructor
        panel.setContent( new EmploymentsView());
        viewEmploymentsButton.setVisible(false);
        viewDatabaseSeletionButton.setVisible(true);

        viewDatabaseSeletionButton.addClickListener(e -> {
            getUI().getNavigator().navigateTo(DatabaseSelectionWindow.VIEW_NAME);
        });
        viewlogButton.addClickListener(e -> {
            try {
                panel.setContent( new LogGrid());
                viewlogButton.setVisible(false);
                viewEmploymentsButton.setVisible(true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        viewEmploymentsButton.addClickListener(e -> {
            viewlogButton.setVisible(true);
            panel.setContent( new EmploymentsView());
            viewlogButton.setVisible(true);
            viewEmploymentsButton.setVisible(false);

        });

        ButtonsLayout.addComponents(viewDatabaseSeletionButton,viewlogButton,viewEmploymentsButton);
        setSpacing(true);
        setMargin(true);
        addComponents( logoutHLayout,ButtonsLayout, panel);
        setComponentAlignment(logoutHLayout, Alignment.BOTTOM_RIGHT);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // checking if the user is logged in and the database is selected before showing the window
         if((getUI().getSession().getAttribute("user")==null)){
             getUI().getNavigator().navigateTo(LoginWindow.VIEW_NAME);}
            else
             if(event.getParameters()==null||event.getParameters().isEmpty()){
                 getUI().getNavigator().navigateTo(DatabaseSelectionWindow.VIEW_NAME);
             }else
                 if(getUI().getSession().getAttribute("user") != null&&event.getParameters()!=null&&
                        ! event.getParameters().isEmpty()){
                      addWindowComponents(event.getParameters().toString());
                 }
    }


}
