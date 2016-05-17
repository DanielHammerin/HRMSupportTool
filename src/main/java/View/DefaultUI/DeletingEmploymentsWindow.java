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
    private DeletingEmploymentsPresenter deletingEmploymentsPresenter;

    public DeletingEmploymentsWindow() throws IOException {
        System.out.println("TEST");
        ButtonsLayout = new HorizontalLayout();
        panel = new Panel();
        viewlogButton = new Button("View Log");
        viewEmploymentsButton = new Button("view Current Employments");
        viewDatabaseSeletionButton = new Button("view Database Seletion");
        logoutHLayout = new LogoutOption(String.valueOf(UI.getCurrent().getSession().getAttribute("user")));
        deletingEmploymentsPresenter = new DeletingEmploymentsPresenter(this);
    }
    private void addWindowComponents (){

        panel.setContent( new EmploymentsView(deletingEmploymentsPresenter));
        viewEmploymentsButton.setVisible(false);
        viewDatabaseSeletionButton.setVisible(true);

        viewDatabaseSeletionButton.addClickListener(e -> {
            getUI().getNavigator().navigateTo(DatabaseSelectionWindow.VIEW_NAME);
        });
        viewlogButton.addClickListener(e -> {
            try {
                panel.setContent( new LogGrid(deletingEmploymentsPresenter));
                viewlogButton.setVisible(false);
                viewEmploymentsButton.setVisible(true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        viewEmploymentsButton.addClickListener(e -> {
            viewlogButton.setVisible(true);
            panel.setContent( new EmploymentsView(deletingEmploymentsPresenter));
            viewlogButton.setVisible(true);
            viewEmploymentsButton.setVisible(false);

        });

        ButtonsLayout.addComponents(viewDatabaseSeletionButton,viewlogButton,viewEmploymentsButton);
        setSpacing(true);
        setMargin(true);
        addComponents( logoutHLayout,ButtonsLayout, panel);
        setComponentAlignment(logoutHLayout, Alignment.BOTTOM_RIGHT);
    }
    public void ShowErrorNotification(String msg){
        new Notification(msg, Notification.TYPE_ERROR_MESSAGE)
                .show(getUI().getPage());
        return;
    }

    public void showSuccessNotification(String msg){
        Notification.show(msg);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("TEST 2");
        // checking if the user is logged in and the database is selected before showing the window
         if((getUI().getSession().getAttribute("user")==null)){
             System.out.println("Not connected");
             getUI().getNavigator().navigateTo(LoginWindow.VIEW_NAME);
         } else
         {
             System.out.println("Connected");
             addWindowComponents();
             /*
             if (event.getParameters() == null || event.getParameters().isEmpty()) {
                 getUI().getNavigator().navigateTo(DatabaseSelectionWindow.VIEW_NAME);
             } else if (getUI().getSession().getAttribute("user") != null && event.getParameters() != null &&
                     !event.getParameters().isEmpty()) {
                 addWindowComponents();
             }*/
         }
    }


}
